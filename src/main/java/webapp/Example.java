package webapp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.MDC;


@RestController
@EnableAutoConfiguration
public class Example {

    private static final Logger logger = LogManager.getLogger(Example.class);
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");

    @RequestMapping("/myerror")
    public ResponseEntity<String> myerror() {
      Exception npe = new NullPointerException("Forcing an exception to be thrown");
      logger.error("Forcing an exception to be thrown and sent to logging framework", npe);

      return new ResponseEntity<String>("Check console for error message", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @RequestMapping("/")
    public String home() throws UnknownHostException {
      logger.info("Home endpoint called! " +  InetAddress.getLocalHost() + " : " + InetAddress.getLocalHost().getHostName());

      MDC.put("request_date", formatter.format(new Date()));

      return String.format("%s Hello World! : IP %15s : hostname %20s\n", formatter.format(new Date()), InetAddress.getLocalHost().getHostAddress(), InetAddress.getLocalHost().getHostName());
    }

    @GetMapping("/headers")
    public ResponseEntity<String> listAllHeaders(
      @RequestHeader Map<String, String> headers) {
        String msg = String.format("Listed %d headers\n", headers.size());

        for(String s: headers.keySet()) {
        	String m = String.format("Header '%s' = %s\n</br>", s, headers.get(s));
            msg += m;
            logger.info(m);
        }
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/headers2")
    public ResponseEntity<String> multiValue(@RequestHeader MultiValueMap<String, String> headers) {
      String msg = String.format("Listed %d headers\n", headers.size());

      for(String s: headers.keySet()) {
          String m = String.format("Header '%s' = %s\n</br>", s, headers.get(s).stream().collect(Collectors.joining("|")));
          msg += m;
          logger.info(m);
      }

      return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @RequestMapping("/health")
    public String healthz() {
      logger.info("status.:.UP");
    	return "status.:.UP";
    }

    public static void main(String[] args) throws Exception {
        MDC.put("application", "steve");
      
        SpringApplication.run(Example.class, args);
    }

}
