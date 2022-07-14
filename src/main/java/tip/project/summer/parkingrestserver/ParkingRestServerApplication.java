package tip.project.summer.parkingrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ParkingRestServerApplication {

    @GetMapping("/message")
    public String start(){
        return "REST Parking Server";
    }

    public static void main(String[] args) {
        SpringApplication.run(ParkingRestServerApplication.class, args);
    }

}
