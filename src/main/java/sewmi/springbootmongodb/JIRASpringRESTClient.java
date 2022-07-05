package sewmi.springbootmongodb;

import java.io.Console;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import java.nio.charset.StandardCharsets;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sewmi.springbootmongodb.model.JiraSprintDTO;
@Service
public class JIRASpringRESTClient {
	
	private static final String username = "seud721@gmail.com";
    private static final String password = "3n54SRyNYkotTND47V595C4E";
    private static final String jiraBaseURL = "https://automation-dashboard.atlassian.net/rest/agile/1.0/board/1/sprint?state=active";

	private WebClient webClient;
	private final RestTemplate restTemplate = new RestTemplate();
	public void generatetokens() throws Exception {
		 CloseableHttpClient httpClient = HttpClients.createDefault();
		 HttpPost httpPost = new HttpPost("https://api.atlassian.com/oauth/token/accessible-resources");
		 List<NameValuePair> nvps = new ArrayList<>();
	        nvps.add(new BasicNameValuePair("username", username));
	        nvps.add(new BasicNameValuePair("password", password));
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
	        	org.apache.http.HttpEntity entity = response.getEntity(); 
	        	System.out.println("API TOKEN SUCCESSFUL!!*****"+response);
	            String token = EntityUtils.toString(entity, StandardCharsets.UTF_8);
	            System.out.println("API TOKEN SUCCESSFUL!!*****"+token+"doneeeee");
	        }
	}
	
	
	
	

	
}
