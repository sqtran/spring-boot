package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.UnknownHostException;

@RestController
@EnableAutoConfiguration
public class Example {

    private static final Logger logger = LogManager.getLogger(Example.class);

    @RequestMapping("/myerror")
    public String myerror() {
      Exception npe = new NullPointerException("Forcing an exception to be thrown");
      logger.error("Forcing an exception to be thrown and sent to logging framework", npe);
      return "Check console for error message";
    }

    @RequestMapping("/")
    public String home() {
        String msg = "Hello World! ";
        try {
          logger.info("Home endpoint called! " +  InetAddress.getLocalHost() + " : " + InetAddress.getLocalHost().getHostName());
          msg += InetAddress.getLocalHost() + " : " + InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return msg;
    }

    @RequestMapping("/health")
    public String healthz() {
      logger.info("status.:.UP");
    	return "status.:.UP";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
