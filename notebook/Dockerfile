FROM adoptopenjdk:11-jre-hotspot
COPY target/notebook-0.0.1-SNAPSHOT.jar notebook.jar
EXPOSE 8081
ENTRYPOINT java -jar notebook.jar notebook