package oauth2example;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class Oauth2ClientExampleApplication {
	private static final Logger logger = LoggerFactory.getLogger(Oauth2ClientExampleApplication.class);
	
	@Autowired
	private OAuth2RestOperations restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2ClientExampleApplication.class, args);
	}
	
	@RequestMapping("/")
	public void home() {
		logger.info("ACCESS_TOKEN : {}", restTemplate.getAccessToken().getValue()); // get access token 
	}
	
	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List<String> scopes = new ArrayList<String>();
        scopes.add("read");
        
        resource.setAccessTokenUri("https://sauth.iieom.com/oauth/token"); // Oauth2 Token URL
        resource.setClientId("YOUR_CLIENT_ID"); // client id here.
        resource.setClientSecret("YOUR_CLIENT_SECRET"); // client secret here.
        resource.setGrantType("password");
        resource.setScope(scopes);

        resource.setUsername("YOUR_USER_NAME"); // user id here.
        resource.setPassword("YOUR_PASSWORD"); // password here.
        
		return resource ;
	}
	
}
