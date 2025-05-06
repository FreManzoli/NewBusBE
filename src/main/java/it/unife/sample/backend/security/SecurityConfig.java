package it.unife.sample.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration //configurazione di spring
@EnableWebSecurity//sicurezza personalizzata di spring security
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {//filtro che verifica la validità del token jwt nelle richieste http
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {}) // Abilita il CORS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/Utente-controller/utenti", "/api/Utente-controller/utenti/authenticate","/api/Viaggio1-controller/**").permitAll()//endpoint accessibili senza autenticazione
                .requestMatchers("/api/carrello/aggiungi","/api/carrello/rimuovi","/api/carrello/totale","/api/carrello","/api/carrello/svuota").authenticated()//tutte le altre richieste devono essere autenticate
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//aggiunta del fitlro che verifica la validità del token jwt

        return http.build();//ricostruzione della catena di filtri di sicurezza personalizzata
    }
}
