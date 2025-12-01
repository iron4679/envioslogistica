#  Envios y Logistica - POO 2025 - Grupo 7

Sistema backend para la gestión de envíos y logística, desarrollado en **Spring Boot** con base de datos **MySQL** y desplegado mediante **Docker Compose**.

---

##  Requisitos previos

- [Docker](https://docs.docker.com/get-docker/) y [Docker Compose](https://docs.docker.com/compose/)
- Java 17+
- Maven 3.8+
- MySQL

---

## Configuración

### Variables de entorno
El servicio `envioslogistica` utiliza variables definidas en `devcontainer.env`.
El backend usa placeholders que se reemplazan con las variables de entorno.


### Despliegue con Docker Compose
Una vez copiado el repositorio, ejecutamos:
```
docker compose up -d --build
```

Podemos verificar que los contenedores estén corriendo con:
```
docker ps
```

Para detenerlo, ejecutamos
```
docker compose down -v
```

### UI de prueba
http://localhost:8070/ui/index.html


## Ejemplos de uso
### Cotizar envío
- Endpoint: /shipping-calculator
- Método: POST
- Body:
    {"cp":"2760","peso":1.2,"volumen":0.01,"modalidad":"EXPRESS"}
- Códigos: 200, 422.
- Response:
    {"costo":1200,"etaDias":2}

### Crear envío (post-pago)
- Endpoint: /shipments
- Método: POST
- Body:
    {"ordenId":"o456","direccion":"Rivadavia 123, Areco","modalidad":"EXPRESS"}
- Códigos: 201, 409 orden no pagada, 404.
- Response:
    {"id":"s345","ordenId":"o456","estado":"EN_PREPARACION","tracking":"TRK-0001"}

### Tracking de envío
- Endpoint: /shipments/{id}/traking
- Método: GET
- Códigos: 200, 404.
- Response:
    {"id":"s345","estado":"EN_CAMINO","historial":[{"t":"2025-10-25T21:10:00Z","e":"EN_CAMINO"}]}

### Actualizar estado de envío
- Endpoint: /shipments/{id}/traking
- Método: PATCH
- Body:
    { "estado":"ENTREGADO" } (EN_PREPARACION, EN_CAMINO, DEMORADO, ENTREGADO)
- Códigos: 200, 409 transición inválida, 404.