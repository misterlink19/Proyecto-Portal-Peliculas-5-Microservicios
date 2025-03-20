package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PortalPeliculasEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalPeliculasEurekaServerApplication.class, args);
	}

}
