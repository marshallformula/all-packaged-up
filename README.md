# Packaged WebApp

2016 Utah State Park Attendance sample web application.  
Companion application to the blog post at [Volume Labs](https://volumelabs.net)

## Requirements

Development of this application requires the following:

* [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [NodeJS 6.9](https://nodejs.org/en/download/)


## Developing the Application

This application is set up so that you can develop the REST services and JavaScript application independently. 

### Developing REST Services

The REST services are written in Java Utilizing Spring Boot & Spring MVC functionality.  All of that code is located in `src/main/java`.
To develop the services code interactively just run

`gradlew bootRun`

This will start up the embedded webserver (Tomcat by default) and serve your Services.  As you write your code - the server 
should detect code changes and restart as necessary due the inclusion of [Spring Boot DevTools](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html).  

If you are developing/running the REST server interactively while developing the JavaScript web application - you will need to add a system property like so:

`gradlew bootRun -Dcors.origins=http://localhost:3000`

This is because when developing the web application - it will be running on its own development server on port **3000** _(see below)_
which will have a different host and we will need to configure [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS) to allow the web application to consume the REST services.

### Developing the JavaScript Web Application

The JavaScript web application is set up to operate as a standalone web application (when provided some REST services to connect to). 
All of the web application code is under `src/main/app`.  You will need to be in this directory to run the following commands.

The web application is packaged with webpack and utilizes webpack-dev-server to enable interactive development.  

First you must download all of the necessary dependencies from npm.

`npm run setup`

Once that is complete you are ready to start the development server by simply running `npm start`.  However the application can be configured via an environment variable to connect to a REST server at any location.  
This way you can work on the web application and connect to any instance of your API (dev/test environments).
If you would like to connect to your local instance of the REST services that are running using the instructions above - you will just need to 
set the `REST_URL` environment variable to `http://localhost:8080/api`. The easiest way to do that is to just prepend the variable declaration to the start command like this:

`REST_URL=http://localhost:8080/api npm start`

The application is transpiled, packaged and available at [http://localhost:3000](http://localhost:3000).  
This is why the **CORS** property must be configured properly above.  

The development server communicates with your browser via web sockets - so any changes that are made to your code are 
immediately re-packaged and available to your browser without needing to refresh.  Like Magic! :sparkles:

## Building the application

The application is packaged together as a [Spring Boot](https://projects.spring.io/spring-boot/) runnable jar compiled using [Gradle](https://gradle.org/).
Installing Gradle manually is not necessary. The application is configured using the gradle wrapper script.

To compile and package the application just run this:

`./gradlew bootRepackage` on OSX/Linux 

`gradlew.bat bootRepackage` on Windows.

This will download all dependencies, compile, then package the application in a runnable jar file located in _build/libs/packaged-webapp-1.0-SNAPSHOT.jar_. After it is packaged running the application is simple:

`java -jar build/libs/packaged-webapp-1.0-SNAPSHOT.jar`

This will start an embedded webserver (Tomcat) which you can access at [http://localhost:8080](http://localhost:8080).  You can change the port if necessary by adding the `--server.port` argument:

`java -jar build/libs/packaged-webapp-1.0-SNAPSHOT.jar --server.port=8989`