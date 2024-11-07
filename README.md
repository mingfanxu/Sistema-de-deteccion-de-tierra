# Introduccion
esp32 DHT11 Módulo de temperatura y humedad del suelo + sensor fotosensible La recopilación, 
la temperatura, la humedad y los datos fotosensibles se almacenan en la base de datos. 
La tabla de la página web muestra los datos históricos recopilados (DHT11 está equipado con una batería)

# Materiales
1. ESP32 DHT11 
2. Fotorresistor
3. Sensor de luz
4. Pila 18650

# Parámetros y pines de función
**_Voltaje de la fuente de alimentación_** CC 3,6 V ~ 5 V (entrada de puerto MICRO USB de 5 V)  
Soporte de batería 18650 y circuito integrado de carga y descarga de batería de litio.  
El módulo se puede utilizar para cargar la batería mientras se trabaja.  

**_Protocolo WIFI:_** 802.11 B/G/N/D/E/I/K/R (802.11N, velocidad 150 MBPS), agregación de A-MPDU y A-MSDU, admite intervalo de protección de 0,4 US  

**_Estándar WI-FI_** FCC/CE/TELEC/KCC Rango de frecuencia: 2,4-2,5 GHZ  

**_Protocolo Bluetooth:_** compatible con los estándares Bluetooth V4.2 BR/EDR y BLE  

**_Audio Bluetooth:_** audio CVSD y SBC  

Sensor de temperatura y humedad DHT11  
Luces indicadoras rojas, azules y verdes.  
El cuerpo principal del módulo es el módulo ESP-WROOM-32, y el chip periférico USB a puerto serie CP2102 se utiliza para ampliar la interfaz MICROUSB. El USB se puede conectar directamente a la computadora para depurar y la transmisión de datos es rápida. y estable.  

![image](https://img.alicdn.com/imgextra/i4/1796335639/O1CN01N0Fr341rWiOshW1nQ_!!1796335639.jpg)

# Diseño
## Diagrama flujo
![image](https://github.com/mingfanxu/Sistema-de-deteccion-de-tierra/blob/main/image/Diagrama.png)
## Diseño de piezas de medición de iluminación.
El fotorresistor Rp se conecta en paralelo con R2. Después de dividirlo por R1, se envía a la MCU para la adquisición de ADC para obtener la información de iluminación. Se puede calcular el voltaje alimentado a la MCU.
![image](https://github.com/mingfanxu/Sistema-de-deteccion-de-tierra/blob/main/image/light.png)

## Sensor DHT11
Como es un módulo combinado, el pin22 está conectado al sensor DHT11.  
![image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR874OaZVhtU0_c6Qd2rV08p_QxA-XZJ-YQ99-RrYLVmkp3K0gNEfDj4y0xi7XBMORFDzY&usqp=CAU)

# Base de datos
![imagen](https://github.com/mingfanxu/Sistema-de-deteccion-de-tierra/blob/main/image/basedato.png)
