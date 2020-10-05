# 9pixels Java task


9pixels_task is a Spring MVC application using MySQL database. In order to use
the application you have to download Postman (https://www.postman.com/downloads/).



### Open and Run Project in Intellij IDEA

After Maven install, you can create a shortcut to the .jar file 
(example: C:\PathToApp\ninepixels\target\ninepixels-0.0.1-SNAPSHOT.jar)
Save the shortcut and open the application using it.


As usual you get started by cloning the  project to your local machine:
https://github.com/siderischristos/9pixels_task.git

## How it runs
    1. Open the project up in Intellij.
       
    2. If you use default username and password for MySQL 
       username: root, 
       password: root 
       you skip this step,
       if not go to the application.properties file 
       (C:\PathToApp\ninepixels\src\main\resources\application.properties)
       and at lines 2,3
       spring.datasource.username=root
       spring.datasource.password=root
       replace “root” with your username and/or password.
       
    3. Install Maven. On the upper right side of the app screen click
       on maven and then ninepixels, Lifecycle, install.
![image](https://user-images.githubusercontent.com/54001807/95075466-df2fe680-0718-11eb-97ca-b0820013c324.png)

    4. If you have already a table with the name “9pixels” in your database you
       have to go again in application.properties
       file and at the first line 
       spring.datasource.url=jdbc:mysql://localhost/ 9pixels ?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
       replace 9pixels, as you see above, with a table name you do not have in use.
       
    5. If you have the server port “8080” already in use, in the
       same file (application.properties) at line 7 replace server.port = 8080 with
       a port you do not have in use (example: 8081) and replace it in the next steps.
       
    6. After you finish the Maven installation go to the NinepixelsApplication file
       (C:\PathToApp\ninepixels\src\main\java\com.task.ninepixels\ NinepixelsApplication)
       and run the main class.
       
    7. Open postman.
       
    8. Create a new POST request with Request URL: http://localhost:8080/register
       
    9. Add a header with key “Content-Type” and value “application/json”
![image](https://user-images.githubusercontent.com/54001807/95075617-274f0900-0719-11eb-8a1f-e4f72a6740f8.png)

    10. Go to “Body” choose “raw” and type the following:
        {
            "username":"john",
            "password":"smith",
            "role":"ROLE_ADMIN"
        }
         
    11. Send the request.
       
    12. You created a user with username: “john”, password: “smith” and role of admin.
       
    13. As you see the password is encrypted
![image](https://user-images.githubusercontent.com/54001807/95075626-2f0ead80-0719-11eb-958a-9f4bf685cf97.png)

    14. Now you have to create a new POST request. 
        Open a new tab in Postman and in the Request URL field type
        http://localhost:8080/authenticate
       
    15. Repeat step 9.
       
    16. Go to “Body”, click on “raw” and type the username and password as follows
        {
            "username":"john",
            "password":"smith"
        }
        
    17. Send the request.
       
    18. The response must be a token which expires in five hours
        from the moment it has been created.
![image](https://user-images.githubusercontent.com/54001807/95075637-3766e880-0719-11eb-8789-bab8c3f4fb3f.png)

    19. Copy the token.

    20. In your browser visit https://www.jsonwebtoken.io/

    21. Paste the token. In “Payload” tab you can see the username of 
        the user you created and that his role is an Admin, as follows:
![image](https://user-images.githubusercontent.com/54001807/95075662-42ba1400-0719-11eb-93eb-9f61f167e554.png)

    22. Now go back to Postman and create a new GET request. Type the URL:
        http://localhost:8080/helloadmin

    23. Go to the Type dropdown list and choose “Bearer Token”.
       
    24. Paste the Token in the field “Token”.
       
    25. Send the request.
       
    26. You see the page “Hello Admin”.
       
    27. If you create a user with the role of Admin you have
        access to both admin’s and user’s page.
        Repeat step 22 with the URL: http://localhost:8080/hellouser
        Repeat steps 23, 24, 25.
        You see the page “Hello User”.
         
    28. You can create a user with the role of a user. In step 10, in 
        the 4th line instead of “ROLE_ADMIN” you type
        “ROLE_USER” and change the username and password.
       
    29. If your user is not an admin, he/she will not be able
        to see the admin’s page with the given token.
       
    30. The application uses create-drop schema method, so each time 
        it starts you have to create a new user. In order to save the users 
        in database go to the application.properties file
        (C:\PathToApp\ninepixels\src\main\resources\application.properties) 
        and replace line 4 with the following:
        spring.jpa.hibernate.ddl-auto=none


 ## How it works
    1.  When you try to register a user, the application calls the method "saveUser" in
        AuthenticationController class which calls the method "save"
        in customUserDetailsService class. 
        
        In this method the password is being encrypted (using Bcrypt encoder).
        
        Finally the application saves the user with the given credentials
        as the class DAOUser in model package requires, in the table "users"
        (see the annotation in DAOUser class).
        
    2.  When you try to authenticate a user, the application calls the method 
        createAuthenticationToken in AuthenticationController class.
        
        If the credentials you entered in order to authenticate a user are valid, 
        a token is being generated in JwtUtil class in config package. If the 
        credentials are wrong or the user does not exist, it throws an exception.
        
        In the generated token are encrypted the username (string) and the role (boolean)
        of the chosen user.
