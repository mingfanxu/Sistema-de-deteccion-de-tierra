# Introduccion
esp32 DHT11 Módulo de temperatura y humedad del suelo + sensor fotosensible La recopilación, 
la temperatura, la humedad y los datos fotosensibles se almacenan en la base de datos. 
La tabla de la página web muestra los datos históricos recopilados (DHT11 está equipado con una batería)

# Materias
1. ESP32 DHT11 
2. Fotorresistor
3. Sensor de luz
4. Pila 18650

# Diseño
## Diseño de piezas de medición de iluminación.
El fotorresistor Rp se conecta en paralelo con R2. Después de dividirlo por R1, se envía a la MCU para la adquisición de ADC para obtener la información de iluminación. Se puede calcular el voltaje alimentado a la MCU.
![image](https://image.lceda.cn/pullimage/e1eZErYrEARtFCQk6aa7RiUkKmBcZOUJHAjCoU1W.png)

## Sensor DHT11
![image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR874OaZVhtU0_c6Qd2rV08p_QxA-XZJ-YQ99-RrYLVmkp3K0gNEfDj4y0xi7XBMORFDzY&usqp=CAU)
