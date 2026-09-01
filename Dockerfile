FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY backend/pom.xml ./backend/pom.xml
COPY backend/src ./backend/src

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN mvn -q -f /app/backend/pom.xml package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar /app/backend/target/enterprisepro-erp-1.0.0-SNAPSHOT.jar --spring.profiles.active=default"]
