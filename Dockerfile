# syntax=docker/dockerfile:1
FROM amazoncorretto:18

# set working directory
WORKDIR /app

# we need to get the Maven wrapper and our pom.xml file into our image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# install dependencies on into image
RUN ./mvnw dependency:go-offline


# copy all of our code into image
#copy src file to app folder inside the image
COPY src ./src

# run the image
CMD ["./mvnw", "spring-boot:run"]