FROM eclipse-temurin:17-jdk-jammy
# OR if needed:
FROM eclipse-temurin:11-jdk-jammy  # For Java 11

WORKDIR /app

# Copy only the files needed for dependency resolution first (better caching)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Set permissions and download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copy remaining source files
COPY src ./src

# Build with retry in case of network issues
RUN ./mvnw package -DskipTests || \
    (sleep 10 && ./mvnw package -DskipTests) || \
    (sleep 30 && ./mvnw package -DskipTests)

CMD ["java", "-jar", "target/parasi-0.0.1-SNAPSHOT.jar"]