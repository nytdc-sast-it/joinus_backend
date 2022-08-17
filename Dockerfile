FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY build/libs/joinus_backend-*.jar /app/joinus_backend.jar
EXPOSE 8080
CMD [ "java", "-jar", "joinus_backend.jar" ]
