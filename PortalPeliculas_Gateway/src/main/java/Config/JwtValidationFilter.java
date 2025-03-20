package Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtValidationFilter extends AbstractGatewayFilterFactory<JwtValidationFilter.Config> {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("LaSuperClaveSecretaSuperSeguraYExtensaParaJWT123".getBytes(StandardCharsets.UTF_8));

    public JwtValidationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String path = request.getURI().getPath();

            // Rutas protegidas por ADMIN
            List<String> adminRoutes = List.of(
                    "/cpeliculas/nuevo",
                    "/cactor/nuevo",
                    "/cpeliculas/editar",
                    "/cactor/editar",
                    "/cpeliculas/guardar",
                    "/cactor/guardar",
                    "/cpeliculas/borrar",
                    "/cactor/borrar",
                    "/cusuarios/listado",
                    "/cusuarios/detalle/**",
                    "/cusuarios/editar/**",
                    "/cusuarios/borrar/**"
            );

            // Rutas protegidas por USER y ADMIN
            List<String> userRoutes = List.of(
                    "/ccriticas/nueva/**",
                    "/ccriticas/guardar"
            );

            // Si la ruta no es protegida, continuar con el request
            if (!adminRoutes.stream().anyMatch(path::startsWith)
                    && !userRoutes.stream().anyMatch(path::startsWith)) {
                return chain.filter(exchange);
            }

            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token == null || !token.startsWith("Bearer ")) {
                System.out.println("Token no presente o no válido: " + token);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            token = token.substring(7);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String role = claims.get("authority", String.class); // Ahora obtenemos la autoridad correctamente

                // Si es una ruta de ADMIN, el usuario debe ser ADMIN
                if (adminRoutes.stream().anyMatch(path::startsWith) && !"ADMIN".equals(role)) {
                    System.out.println("Acceso denegado: No es ADMIN");
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return response.setComplete();
                }

                // Si es una ruta de usuario, permitimos ADMIN y USER
                if (userRoutes.stream().anyMatch(path::startsWith) && !"ADMIN".equals(role) && !"USER".equals(role)) {
                    System.out.println("Acceso denegado: No es USER ni ADMIN");
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return response.setComplete();
                }

                return chain.filter(exchange);
            } catch (Exception e) {
                System.out.println("Error al procesar el token: " + e.getMessage());
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        };
    }

    public static class Config {}
}
