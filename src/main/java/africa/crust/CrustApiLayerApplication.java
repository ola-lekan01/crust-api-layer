package africa.crust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CrustApiLayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrustApiLayerApplication.class, args);
    }
}