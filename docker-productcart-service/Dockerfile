FROM eclipse-temurin:21
EXPOSE 8089
COPY /target/product-cart.jar  cart-service.jar 
ENTRYPOINT [ "java","-jar","cart-service.jar" ]