FROM openjdk:8
EXPOSE 8080
ADD target/springboot-rest-ebi-test.jar springboot-rest-ebi-test.jar 
ENTRYPOINT ["java","-jar","/springboot-rest-ebi-test.jar"]