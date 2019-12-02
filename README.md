## Springboot HelloWorld App

Super simple spring-boot app for testing.

You can run it with the following command.

```bash
mvn spring-boot:run
```

Once started, the root context `/` will return a "hello world" message with the hostname of the service.

Access `/health` to view "**status.:.UP**" if the service is up and running.

Access `/myerror` to view a generated error message.


If TLS is enabled, you have to access everything via https://localhost:8443.  Check `application.properties` to verify if TLS is enabled or not.
