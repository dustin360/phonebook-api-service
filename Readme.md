##PHONE BOOK APP README
- Clone the code from https://github.com/dustin360/phonebook-api-service.git
- run the application using mvn (mvn spring-boot:run)
- The application uses an embedded H2 db. So there will be no need to configure external database connections.
- On successful run, you can navigate to your browser to view and test the API's using swagger - http://localhost:9092
- i've also written a couple of unit tests to back up the implementations.

- To run the appilication on docker
    - build the application: 
        mvn clean package
    - build a docker image: 
        docker build -t phonebook .
    - run the app
        docker run -p 9092:9092 --name phonebook-container --network=host phonebook
    - check your browser on localhost:9092
    
- The application is currently running on docker on a remote linux server. You can check it out on:
    http://143.198.48.197:9092/swagger-ui/