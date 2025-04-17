package ru.zharko.sberlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SberlibApplication {

    public static void main(String[] args) {
        SpringApplication.run(SberlibApplication.class, args);
    }

}
