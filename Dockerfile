# Imagen base con JDK 17 (Temurin es estable y LTS)
FROM eclipse-temurin:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por Maven/Gradle
COPY target/envioslogistica-0.0.1-SNAPSHOT.jar app.jar

# Definir variable de entorno para el puerto
ENV SERVER_PORT=8070

# Exponer el puerto
EXPOSE 8070

# Comando de inicio
ENTRYPOINT ["java","-jar","/app/app.jar"]