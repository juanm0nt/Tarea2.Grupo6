# README - Proyecto Publisher-Subscriber Simulador (Tarea2)

## Resumen

Este proyecto implementa un simulador basado en el patrón Publisher-Subscriber (Publicador-Suscriptor), dividido en 4 etapas de complejidad ascendente.

---

# Etapas del Desarrollo

## **Stage 1 - Primer Publisher-Subscriber**

**Objetivo:**
- Crear publicadores de videos.
- Crear suscriptores de videos que actualizan el último video recibido en un botón.

**Solución Aplicada:**
- Creamos las clases `Publisher`, `Subscriber`, `Broker`, y `Topic`.
- Implementamos `VideoPublisher` y `VideoFollower`.
- Publicadores permiten ingresar un URL de video.
- Suscriptores muestran el último mensaje como texto en un botón.

## **Stage 2 - Reproductor de Video**

**Objetivo:**
- Al hacer click en el botón del suscriptor, reproducir el video recibido.

**Solución Aplicada:**
- Se incorporó `MediaView` y `MediaPlayer` de JavaFX.
- Agregamos controles de reproducción y volumen.

**Video usado para prueba:**
- [https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4](https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4)
## **Stage 3 - GPS Car Tracker**

**Objetivo:**
- Publicadores de GPS que publican la trayectoria de un auto.
- Suscriptores muestran el auto moviéndose según posiciones.

**Solución Aplicada:**
- Implementamos interpolación de posiciones con `Timeline`.
- Movimiento fluido del punto en un `Pane`.

**Archivo usado para prueba de ruta:**
```
0 100 100
3 300 100
6 300 300
9 100 300
12 100 100
```
(Archivo sugerido: `ruta_prueba.txt`)

