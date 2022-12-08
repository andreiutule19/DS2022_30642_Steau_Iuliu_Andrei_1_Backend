FROM openjdk:18.0.2-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} DS2022_30642_Steau_Iuliu_Andrei_1_Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","DS2022_30642_Steau_Iuliu_Andrei_1_Backend-0.0.1-SNAPSHOT.jar"]
