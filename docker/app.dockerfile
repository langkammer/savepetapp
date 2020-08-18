FROM maven:latest 
COPY ./backend /var/www
WORKDIR /var/www
ENTRYPOINT mvn spring-boot:run -Dspring.profiles.active=dev
EXPOSE 8080