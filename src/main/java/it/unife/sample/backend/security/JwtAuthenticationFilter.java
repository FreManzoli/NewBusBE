package it.unife.sample.backend.security;

import it.unife.sample.backend.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {//filtro che viene eseguito una sola volta per ogni richiesta

    private final JwtUtil jwtUtil;//per gestione dei token jwt
    private final UtenteService utenteService;//per gestione degli utenti

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UtenteService utenteService) {
        this.jwtUtil = jwtUtil;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // recupero l'header di autorizzazione dalla richiesta HTTP che contiene il token jwt
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // se header assente o inizia con Bearer vado al filtro successivo
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);//estrazione del token jwt dall'header di autorizzazione rimuovendo Bearer
        //validazione del token jwt
        try {
            String email = jwtUtil.extractEmail(token);//estraggo email dal token
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {//verifico se utente già autenticato
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(//creazione del token di autenticazione
                        email, null, Collections.emptyList());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//aggiungo dettagli aggiuntivi al token di autenticazione
                SecurityContextHolder.getContext().setAuthentication(auth);//imposto l'utente nel contesto di sicurezza
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//se token invalido o scaduto restituisco errore 401
            return;
        }

        filterChain.doFilter(request, response);// passo al filtro successivo o al controller corrispondente
    }
}
