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
import shared.messages.request.RequestRegister;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeCommunicatorClientREST implements ISnakeRest {
    private static final Logger LOGGER = Logger.getLogger(SnakeCommunicatorClientREST.class.getName());
    private static final String URL = "http://localhost:8082/api";
    private final Gson gson = new Gson();

    public RequestRegister postSignIn(RequestRegister signIn) {
        final String query = URL + "/auth/signin";
        return getAuthentication(signIn, query);
    }

    public RequestRegister postSignUp(RequestRegister signUp) {
        final String query = URL + "/auth/signup";
        return getAuthentication(signUp, query);
    }

    private RequestRegister getAuthentication(RequestRegister signUp, String query) {
        LOGGER.log(Level.INFO,"POST: {0}", query);

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

    private RequestRegister executeQuery(HttpRequestBase requestBase) {
        RequestRegister authentication = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(requestBase)){
            LOGGER.log(Level.INFO, "Status: {0}", response.getStatusLine());

            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);

            authentication = gson.fromJson(entityString, RequestRegister.class);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return authentication;
    }

}
