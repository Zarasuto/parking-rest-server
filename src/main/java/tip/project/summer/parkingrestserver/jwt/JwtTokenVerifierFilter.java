package tip.project.summer.parkingrestserver.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tip.project.summer.parkingrestserver.Model.Authorities;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Get the token
        String authorizationHeader= request.getHeader("Authorization");

        //Try if token is null or dont have Bearer tag
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String secretKey="securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
        try{
            String token = authorizationHeader.replace("Bearer ","");

            //Parses the token into Jws<Claims>
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            //Parse the Jws<claims> and get the username and its authorities inside the token
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            Object data = body.get("data");
            ArrayList<LinkedHashMap> dataList = (ArrayList<LinkedHashMap>) data;
            ArrayList<Authorities> authorityList= new ArrayList<>();
            for(LinkedHashMap map:dataList){
                authorityList.add(new ObjectMapper().convertValue(map,Authorities.class));
            }
            //==========================

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorityList
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Pass the request and response to next filter, if there will be
            filterChain.doFilter(request,response);
        }catch(JwtException e){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Token Cannot be Trusted");
        }
    }
}
