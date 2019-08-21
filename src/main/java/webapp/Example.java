package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.beans.factory.annotation.Value;



@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties(MyConfiguration.class)
@VaultPropertySource("secret/gs-vault-config")
public class Example {

    private final MyConfiguration configuration;

    @Value("${username}")
    private String uname;

    @Value("${password}")
    private String pword;

    public Example(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @RequestMapping("/")
    public String home() {
        return String.format("Hello World %s %s %s %s!", configuration.getUsername(), configuration.getPassword(), uname, pword );
    }

    @RequestMapping("/health")
    public String healthz() {
    	return "status.:.UP";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
