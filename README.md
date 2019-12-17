## Springboot HelloWorld App

Super simple spring-boot app for testing.

You can run it with the following command.

```bash
mvn spring-boot:run
```

Once started, the root context `/` will return a "hello world" message with the hostname of the service.


Visit `/myerror` to view a generated error message.





| Endpoint|Description|
|----------|:-------------:|
| `/` | returns "hello world" |
| `/health` |  "**status.:.UP**" if the service is up and running|
| `/myerror` |  to view a generated error message.   |
| `/headers` | dumps all the headers |
| `/headers2` | dumps all the headers, even for collections |

If TLS is enabled, you have to access everything via https://localhost:8443.  Check `application.properties` to verify if TLS is enabled or not.
