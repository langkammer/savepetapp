FROM maven:latest 
COPY ./backend /var/www
WORKDIR /var/www
ENTRYPOINT mvn spring-boot:run -Dspring-boot.run.profiles=docker
EXPOSE 8080