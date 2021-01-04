package communicator.rest;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import shared.rest.Authentication;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeCommunicatorClientREST implements ISnakeRest {
    private static final Logger LOGGER = Logger.getLogger(SnakeCommunicatorClientREST.class.getName());
    private final String url = "http://localhost:8082/api";
    private final Gson gson = new Gson();

    public Authentication postSignIn(Authentication signIn) {
        final String query = url + "/auth/signin";
        LOGGER.log(Level.INFO,"POST: {}", query);

        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(signIn));
            httpPost.setEntity(params);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return executeQuery(httpPost);
    }

    public Authentication postSignUp(Authentication signUp) {
        final String query = url + "/auth/signup";
        LOGGER.log(Level.INFO,"POST: {}", query);

        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(signUp));
            httpPost.setEntity(params);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return executeQuery(httpPost);
    }

    private Authentication executeQuery(HttpRequestBase requestBase) {
        Authentication authentication = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(requestBase)){
            LOGGER.log(Level.INFO, "Status: {}", response.getStatusLine());

            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);

            authentication = gson.fromJson(entityString, Authentication.class);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return authentication;
    }

}
