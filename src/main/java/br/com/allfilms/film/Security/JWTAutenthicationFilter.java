package br.com.allfilms.film.Security;

import br.com.allfilms.film.dto.UserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenthicationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRES = 24;

    public static final String TOKEN_PASSWORD = "17750f27-1865-4c9a-94ab-fd1d2004f7cb";

    private final AuthenticationManager authenticationManager;

    public JWTAutenthicationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserDto user = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);

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
                .withExpiresAt(Date.from(Instant.now().plus(TOKEN_EXPIRES, ChronoUnit.HOURS)))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
