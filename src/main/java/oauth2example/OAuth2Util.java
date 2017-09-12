package oauth2example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OAuth2Util {
	private ResourceOwnerPasswordResourceDetails getDetail(String username, String password) {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List<String> scopes = new ArrayList<String>();
        scopes.add("read");
        resource.setAccessTokenUri("http://localhost:8082/oauth/token");
        resource.setClientId("app");
        resource.setClientSecret("edusecret");
        resource.setGrantType("password");
        resource.setScope(scopes);

        resource.setUsername(username);
        resource.setPassword(password);
        
        return resource;
	}
	
	public OAuth2AccessToken getAccessToken(String username, String password) {
		ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
		
		OAuth2AccessToken accessToken = provider.obtainAccessToken(getDetail(username, password), new DefaultAccessTokenRequest());
        		
        return accessToken;
	}
}
