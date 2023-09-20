# location-handler-service

Download an IDE eclipse or IntelliJ IDEA.
Import lombok.jar to your IDE according to this instruction link
https://www.baeldung.com/lombok-ide
 
Be sure you have installed spring tool from marketplace

Clone this https://github.com/mariakrit/location-service.git repository to your local folder.
Import as a Maven project to IDE.
 
Do a maven build with goals clean and install.
After the build should have build success on your console.
 
Open the Run as configuration properties
 
![Picture1](https://github.com/mariakrit/location-handler-service/assets/42673505/6d9ee692-3b6c-45a3-89b2-f8dab9e4ef08)
 
On left side on Spring Boot APP inside of the application name locations-service like the below screenshot
 
![Picture2](https://github.com/mariakrit/location-handler-service/assets/42673505/0681fe92-926b-464d-a328-8bfe42fe65fc)

 
Check the below configuration, should be like the below screenshot.
 
![Picture3](https://github.com/mariakrit/location-handler-service/assets/42673505/bbe4d263-5208-4746-948b-28bde46a60be)
 
Inside of the 'Arguments' tab should have the below variable like the screenshot 
 
![Picture4](https://github.com/mariakrit/location-handler-service/assets/42673505/b6578d00-c023-4271-880d-4e0095d38dfb)
 
Then go to Run as and select spring boot application like the below screenshot

![Picture5](https://github.com/mariakrit/location-handler-service/assets/42673505/950c9e59-9f54-40f2-82df-35266a8e5a06)
 
After the execution should take the below screenshot to your console

![Picture6](https://github.com/mariakrit/location-handler-service/assets/42673505/ff418224-fa76-4a65-95d3-a69d166171c5)

Ignore this error 
''jakarta.validation.NoProviderFoundException: Unable to create a Configuration, because no Jakarta Bean Validation provider could be found. ''
 
 
Run this url http://localhost:8080/h2-console/  and will have the database login page 
 
![Picture7](https://github.com/mariakrit/location-handler-service/assets/42673505/a51724be-d63c-479c-bc69-0679bf705590)
 
Click connect, will present the created LOCATION table.

Click on this table and will present the command  SELECT * FROM LOCATION, click Run button and should return the empty table 

![Picture8](https://github.com/mariakrit/location-handler-service/assets/42673505/5ef4285d-5a31-42b9-bfc0-d8f9c174ae34)
 
Run this url http://localhost:8080/swagger-ui/ 
And will take the swagger page 

![Picture9](https://github.com/mariakrit/location-handler-service/assets/42673505/00870321-e978-41f1-bd5d-8b8cc76ffbc2)
 
Now you may execute the endpoints and check the results on the database.
 
Please be aware that the database is working on runtime and when the session is terminated the database is unavailable. 
