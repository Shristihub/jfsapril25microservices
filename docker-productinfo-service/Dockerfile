FROM eclipse-temurin:21
EXPOSE 8082
COPY /target/product-info.jar  info-service.jar 
ENTRYPOINT [ "java","-jar","info-service.jar" ]