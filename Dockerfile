FROM ranchuan/ubuntu-java:3.0
COPY target/DockerTest-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar