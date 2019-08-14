package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties(MyConfiguration.class)
public class Example {

    private final MyConfiguration configuration;

    public Example(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @RequestMapping("/")
    public String home() {
        return String.format("Hello World %s %s !", configuration.getUsername(), configuration.getPassword());
    }

    @RequestMapping("/health")
    public String healthz() {
    	return "status.:.UP";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
