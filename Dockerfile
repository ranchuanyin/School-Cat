FROM ranchuan/ubuntu-java:3.0
COPY schoolcat-backend/target/schoolcat-backend-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar