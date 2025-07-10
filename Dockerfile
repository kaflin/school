FROM eclipse-temurin:8-jdk

WORKDIR /app

# Copy only the files needed for dependency resolution first (better caching)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Set permissions and download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copy remaining source files
COPY src ./src

# Build (no Lombok flags needed for Java 8)
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/parasi-0.0.1-SNAPSHOT.jar"]