FROM eclipse-temurin:21
EXPOSE 8084
COPY /target/product-catalog.jar  catalog-service.jar 
ENTRYPOINT [ "java","-jar","catalog-service.jar" ]