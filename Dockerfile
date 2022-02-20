FROM openjdk:8-jre-alpine


# Copying needed files
COPY ./build/libs/rescueme*all.jar /app/rescueme.jar
COPY ./build/resources/ /app/resources/
WORKDIR /app

# Entrypoint definition
CMD ["sh", "-c", "java -jar rescueme.jar"]
# End Container setup --------