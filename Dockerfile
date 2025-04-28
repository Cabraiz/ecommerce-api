# Base
FROM eclipse-temurin:17-jdk-jammy

# Pasta do app
WORKDIR /app

# Copia o jar
COPY build/libs/*.jar app.jar

# Variáveis de ambiente
ENV PORT=8080
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=50.0 -Xms32m -Xmx128m -XX:+UseSerialGC"

# Expor a porta
EXPOSE 8080

# Rodar
ENTRYPOINT ["java", "-jar", "app.jar"]
