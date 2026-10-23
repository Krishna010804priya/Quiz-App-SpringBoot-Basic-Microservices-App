package com.priya.Service_Registry_QuizApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //by this eureka server is ON...
public class ServiceRegistryQuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryQuizAppApplication.class, args);
	}


//	CONCEPT OF "EUREKA SERVER"....
//	Quiz Service
//	(Eureka client)\
//			/\		\(Register),(Discover)
//			|		 \
//	Connect	|		  >>>Service Registry(Eureka Server)
//			|		 /
//		    \/	    /(Register),(Discover)
// Question Service/
//	(Eureka Client)

}
