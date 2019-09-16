package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    public String home() {

      String msg = "Hello World! ";
      try {
        System.out.println("Home endpoint called! " +  InetAddress.getLocalHost() + " : " + InetAddress.getLocalHost().getHostName());
        msg += InetAddress.getLocalHost() + " : " + InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
          e.printStackTrace();
      }

        return msg;
    }

    @RequestMapping("/health")
    public String healthz() {
    	return "status.:.UP";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
