package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.HttpRequest;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();

        // Interceptor para agregar el token JWT a las solicitudes
        restTemplate.getInterceptors().add((HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
            String token = (String) session.getAttribute("token");
            if (token != null) {
                System.out.println("Añadiendo token JWT al encabezado: " + token);
                request.getHeaders().add("Authorization", "Bearer " + token);
            } else {
                System.out.println("No se encontró token en la sesión.");
            }
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}

