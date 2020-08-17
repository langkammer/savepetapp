version : '2'
services : 
    java-api :
        images: maven
        volumes:
            - "./backend/deploy"
        woking_dir: "/deploy"
        ports:
            - "8080:8080"
        command: bash -c "mvn clean install"
        container_name : JavaAPI 