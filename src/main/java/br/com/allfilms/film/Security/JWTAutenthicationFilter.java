package br.com.allfilms.film.Security;

import br.com.allfilms.film.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenthicationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRES = 600_00;

    public static final String TOKEN_PASSWORD = "17750f27-1865-4c9a-94ab-fd1d2004f7cb";

    private final AuthenticationManager authenticationManager;

    public JWTAutenthicationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws AuthenticationException {

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(),
                    user.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar o usuário!", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsSecurity userDetailsSecurity = (UserDetailsSecurity) authResult.getPrincipal();

        String token = JWT.create().withSubject(userDetailsSecurity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRES))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
