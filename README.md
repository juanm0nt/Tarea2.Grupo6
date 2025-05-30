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
