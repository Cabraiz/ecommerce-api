# Etapa 1 - Build (se quisesse buildar no Docker, mas podemos pular)
# FROM gradle:8.6-jdk17 AS build
# COPY --chown=gradle:gradle . /home/gradle/src
# WORKDIR /home/gradle/src
# RUN gradle build --no-daemon

# Etapa 2 - Runtime
FROM eclipse-temurin:17-jdk-jammy

# Cria a pasta onde vai rodar
WORKDIR /app

# Copia o JAR já pronto
COPY build/libs/*.jar app.jar

# Avisa ao Railway que a porta vem da variável PORT
ENV PORT=8080

# Muda a porta de escuta para a variável
EXPOSE 8080

# Rodar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
