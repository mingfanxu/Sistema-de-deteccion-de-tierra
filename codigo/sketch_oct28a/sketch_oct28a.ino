#include <Arduino.h>
#include <WiFi.h>
#include <WiFiMulti.h>
#include <Bonezegei_DHT11.h>

#include <HTTPClient.h>

#include <ESP_Mail_Client.h>

#define USE_SERIAL Serial
Bonezegei_DHT11 dht(22);

WiFiMulti wifiMulti;

#define LPOT 33
#define SPOT 32
#define LEDPOT 16

float tempDeg;
int hum;
int beam;
int soil;

// Gmail SMTP配置
#define MAIL_SERVER "smtp.gmail.com"
#define MAIL_PORT 587
#define SENDER_MAIL "mingfanxu@gmail.com"
#define SENDER_PASSWORD "tbqz auzu pmvj sfbc"
#define RECIPIENT_MAIL "mingfanxu6@gmail.com"

SMTPSession smtp; // 定义SMTP会话
bool emailSent = false; // 标记邮件是否已发送


void sendEmail() {
    SMTP_Message message;

    // 设置邮件内容
    message.sender.name = "ESP32 Notification System";
    message.sender.email = SENDER_MAIL;
    message.subject = "ESP32 温湿度检测系统已启动";
    message.addRecipient("User", RECIPIENT_MAIL);

    message.text.content = String("温湿度检测系统已启动。\n") +
                           "当前检测到的数据如下：\n" +
                           "温度: " + String(tempDeg) + "°C\n" +
                           "空气湿度: " + String(hum) + "%\n" +
                           "光照强度: " + String(4095 - beam) + "\n" +
                           "土壤湿度: " + String(4095 - soil) + "\n";

    smtp.debug(1); // 启用调试信息

    smtp.callback([](SMTP_Status status) {
        USE_SERIAL.printf("SMTP Status: %s\n", status.info());
    });

    // 设置 SMTP 会话配置
    Session_Config config;
    config.server.host_name = MAIL_SERVER;
    config.server.port = MAIL_PORT;
    config.login.email = SENDER_MAIL;
    config.login.password = SENDER_PASSWORD;

    // 尝试连接 SMTP 服务器
    if (!smtp.connect(&config)) {
        USE_SERIAL.println("SMTP 登录失败!");
        return;
    }

    // 发送邮件
    if (!MailClient.sendMail(&smtp, &message)) {
        USE_SERIAL.println("邮件发送失败: " + smtp.errorReason());
    } else {
        USE_SERIAL.println("邮件发送成功!");
    }
    smtp.closeSession();
}



void setup() {

    USE_SERIAL.begin(115200);

    USE_SERIAL.println();
    USE_SERIAL.println();
    USE_SERIAL.println();

    pinMode(LEDPOT, OUTPUT);
    for(uint8_t t = 4; t > 0; t--) {
        USE_SERIAL.printf("[SETUP] WAIT %d...\n", t);
        USE_SERIAL.flush();
        digitalWrite(LEDPOT, LOW);
        delay(500);
        digitalWrite(LEDPOT, HIGH);
        delay(500);
    }

    wifiMulti.addAP("INFINITUM1E90", "EhTdJQus3e");
    //wifiMulti.addAP("Ming", "66666666");

    dht.begin();
    pinMode(LPOT, INPUT);
    pinMode(SPOT, INPUT);
    digitalWrite(LEDPOT, LOW);
}

void loop() {
    // 等待WIFI连接
    if((wifiMulti.run() == WL_CONNECTED)) {
        digitalWrite(LEDPOT, HIGH);
        HTTPClient http;

        USE_SERIAL.print("[HTTP] begin...\n");

        //设置请求路径
        String strTemp = String(tempDeg);
        String strHum = String(hum);
        String strBeam = String(4095 - beam);
        String strSoil = String(4095 - soil);
        //构造HTTP GET请求，将读取的环境数据作为查询参数传输给服务器
        http.begin("http://111.229.73.197/yjyw/103/base/addData?temp="+strTemp+"&humi="+strHum+"&light="+strBeam+"&soil="+strSoil);

        USE_SERIAL.print("[HTTP] GET...\n");
        // 发送请求
        int httpCode = http.GET();

        // 判断请求是否成功
        if(httpCode > 0) {
            
            USE_SERIAL.printf("[HTTP] GET... code: %d\n", httpCode);

            // 打印返回结果
            if(httpCode == HTTP_CODE_OK) {
                String payload = http.getString();
                USE_SERIAL.println(payload);
            }
        } else {
            USE_SERIAL.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
        }

        http.end();
        // 发送邮件（仅一次）
        if (!emailSent) {
            sendEmail();
            emailSent = true; // 避免重复发送邮件
        }
    }

    if (dht.getData()) {
      tempDeg = dht.getTemperature(); //获取温度
      hum = dht.getHumidity();        //获取湿度
      Serial.printf("Temperature: %0.1lf°C, Humidity:%d \n", tempDeg, hum);
    }
    beam = analogRead(LPOT); // 光强  使用模拟输入读取电压值
    soil = analogRead(SPOT); // 土壤湿度
    // 打印模拟值在串口屏上
    Serial.printf("Light: %d, Soil: %d \n", beam, soil);
    for(int i = 0; i < 60; i++) {
        delay(5000);
    } 
}
