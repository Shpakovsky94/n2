## Instructions to run and test the code

1. Clone the repository using the following command:

```
   git clone https://github.com/Shpakovsky94/n2.git
```

2. Build the project using Maven:

```
   cd n2
   mvn clean package
```

3. Run the program using the following command for successful scenario:

```
   java -jar target/n2-task.jar successful
```

4. You should see the following output with random emojis:

```
   Random Emojis: &#127946; &#127996; &#127932;
```

5. All other scenarios will print just 3 sad emoji

```
   java -jar target/n2-task.jar 
   java -jar target/n2-task.jar s
   java -jar target/n2-task.jar fail
```

6. You should see the following output with 3 sad emoji:

```
   Random Emojis: 😢 😢 😢;
```

7. Test the program by running the JUnit tests using Maven:

```
   mvn test
```

All tests should pass.

## Documentation/reference about the libraries used

1. Java HTTP Client
    - This is a built-in Java library for sending HTTP requests and receiving HTTP responses.
    - Documentation: https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html

2. Jackson JSON Processor
    - Java library for parsing JSON data into Java objects and serializing Java objects into JSON data.
    - Documentation: https://github.com/FasterXML/jackson-docs/wiki

3. JUnit
    - Java unit testing framework for testing Java code.
    - Documentation: https://junit.org/junit4/

4. Mockito
    - This is a popular Java library for creating mock objects for testing Java code that interacts with external dependencies.
    - Documentation: https://site.mockito.org/
