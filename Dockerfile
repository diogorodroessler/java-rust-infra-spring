
FROM eclipse-temurin:21-jre

WORKDIR /app

RUN mkdir -p /app/native

COPY crates/jr_bidir/target/release/libjr_bidir.so /app/native/libjr_bidir.so

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
