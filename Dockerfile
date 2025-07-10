FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy only the files needed for dependency resolution first (better caching)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Set permissions and download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copy remaining source files
COPY src ./src

# Build with Lombok fixes
RUN ./mvnw package -DskipTests \
    -Djdk.tls.client.protocols=TLSv1.2 \
    -J--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED

# Build with retry in case of network issues
RUN ./mvnw package -DskipTests || \
    (sleep 10 && ./mvnw package -DskipTests) || \
    (sleep 30 && ./mvnw package -DskipTests)

CMD ["java", "-jar", "target/parasi-0.0.1-SNAPSHOT.jar"]