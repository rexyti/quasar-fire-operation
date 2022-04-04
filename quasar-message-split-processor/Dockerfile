FROM amazoncorretto:11-alpine-jdk
MAINTAINER ing.rodriguezjm@gmail.com
COPY target/quasar-message-split-processor-0.0.1-SNAPSHOT.jar quasar-message-split-processor-1.0.0.jar
COPY wait-for-it.sh wait-for-it.sh
ENTRYPOINT ["java","-jar","/quasar-message-split-processor-1.0.0.jar"]