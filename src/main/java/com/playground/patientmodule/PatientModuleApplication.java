package com.playground.patientmodule;

import com.playground.patientmodule.entity.Patient;
import com.playground.patientmodule.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableDiscoveryClient
public class PatientModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientModuleApplication.class, args);
    }

}
