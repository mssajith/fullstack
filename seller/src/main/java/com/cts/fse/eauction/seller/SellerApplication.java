package com.cts.fse.eauction.seller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.cts.fse.eauction.seller.cognito.JwtConfiguration;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import static com.nimbusds.jose.JWSAlgorithm.RS256;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Products API", version = "2.0", description = "Products Information"))
public class SellerApplication {// implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SellerApplication.class, args);
	}

	@Autowired
	private JwtConfiguration jwtConfiguration;

	@Bean
	public ConfigurableJWTProcessor configurableJWTProcessor()
			throws MalformedURLException {
		ResourceRetriever resourceRetriever = new DefaultResourceRetriever(
				jwtConfiguration.getConnectionTimeout(),
				jwtConfiguration.getReadTimeout());
		URL jwkSetURL = new URL(jwtConfiguration.getJwkUrl());
		JWKSource keySource = new RemoteJWKSet(jwkSetURL, resourceRetriever);
		ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
		JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256,
				keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}

}
