FROM eclipse-temurin:17-jre-alpine
# Este ARG seria valido si solo dejamos en el pom no ponemos <finalName></finalName> por que por default
# maven genera el nombre del ejecutable tomando <atefactId></atefactId> y <version></version>
# ARG JAR_FILE=target/shop-api-core-0.0.1.jar

# En este caso en el pom esta <finalName></finalName> maven toma el nombre dentro para genrar el jar
ARG JAR_FILE=target/shop-api-core.jar
COPY ${JAR_FILE} shop-api-core.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shop-api-core.jar"]