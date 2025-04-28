# Etapa 1 - (opcional) Build no Docker
# FROM gradle:8.6-jdk17 AS build
# COPY --chown=gradle:gradle . /home/gradle/src
# WORKDIR /home/gradle/src
# RUN gradle build --no-daemon

# Etapa 2 - Runtime (produção)
FROM eclipse-temurin:17-jdk-jammy

# Define a pasta do app
WORKDIR /app

# Copia o JAR já construído
COPY build/libs/*.jar app.jar

# Define a porta como variável de ambiente
ENV PORT=8080

# Define opções de memória e GC mais leves
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=50.0 -Xms32m -Xmx128m -XX:+UseSerialGC"

# Expõe a porta (Docker)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
