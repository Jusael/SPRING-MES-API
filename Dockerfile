FROM eclipse-temurin:21-jre
WORKDIR /app
COPY build/libs/*.jar app.jar
ENV SERVER_PORT=5999
EXPOSE 5999
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
