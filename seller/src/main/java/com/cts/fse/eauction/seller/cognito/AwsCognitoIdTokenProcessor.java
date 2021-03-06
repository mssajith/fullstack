package com.cts.fse.eauction.seller.cognito;

import com.github.andrewoma.dexx.collection.ArrayList;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

//import static java.util.List.of;

@Component
public class AwsCognitoIdTokenProcessor {


    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private ConfigurableJWTProcessor configurableJWTProcessor;

    
    private void printAllHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
        	 System.out.println(":::::::::::::::::::::::::::");
                while (headerNames.hasMoreElements()) {
                		String header = headerNames.nextElement();
                        System.out.println("Header: " + header);
                        System.out.println("Value: " + request.getHeader(header));
                }
                System.out.println(":::::::::::::::::::::::::::");
        }
    }
    
    public Authentication authenticate(HttpServletRequest request) throws Exception {
    	// printing all the headers to console
    	printAllHeaders(request);
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        if (idToken != null) {
            JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken),null);
            validateIssuer(claims);
            verifyIfIdToken(claims);
            String username = getUserNameFrom(claims);
            if (username != null) {
            	  List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                List<GrantedAuthority> grantedAuthorities = of( new SimpleGrantedAuthority("ROLE_ADMIN"));
//                User user = new User(username, "", of());
                User user = new User(username, "", grantedAuthorities);
                return new JwtAuthentication(user, claims, grantedAuthorities);
            }
        }
        return null;
    }

    private String getUserNameFrom(JWTClaimsSet claims) {
        return claims.getClaims().get(this.jwtConfiguration.getUserNameField()).toString();
    }

    private void verifyIfIdToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception("JWT Token is not an ID Token");
        }
    }

    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }

    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }
}
