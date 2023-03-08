package fun.ciallo.aoi;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFileStorage
@SpringBootApplication
public class AoiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoiServerApplication.class, args);
    }

}
