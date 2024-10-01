# Introducción
Esp32 DHT11 Módulo de temperatura y humedad del suelo + sensor fotosensible La recopilación, 
la temperatura, la humedad y los datos fotosensibles se almacenan en la base de datos. 
La tabla de la página web muestra los datos históricos recopilados (DHT11 está equipado con una batería).

# Materias
1. ESP32 - DHT11 
2. Sensor fotosensible
3. Pila 18650
4. fotorresistor

# Diseño
## Diseño de piezas de medición de iluminación
Como se muestra en la figura, el fotorresistor Rp se conecta en paralelo con R2. Después de dividirlo por R1, se envía a la MCU para la adquisición de ADC para obtener la información de iluminación. El voltaje alimentado a la MCU se puede calcular
![image](https://image.lceda.cn/pullimage/e1eZErYrEARtFCQk6aa7RiUkKmBcZOUJHAjCoU1W.png)

