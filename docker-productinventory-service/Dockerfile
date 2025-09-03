FROM eclipse-temurin:21
EXPOSE 8087
COPY /target/product-inventory.jar  inventory-service.jar 
ENTRYPOINT [ "java","-jar","inventory-service.jar" ]