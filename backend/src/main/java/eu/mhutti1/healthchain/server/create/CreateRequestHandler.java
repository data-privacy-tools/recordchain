package eu.mhutti1.healthchain.server.create;

import com.sun.net.httpserver.HttpExchange;
import eu.mhutti1.healthchain.server.RequestUtils;
import eu.mhutti1.healthchain.server.events.NonEventConsumer;
import eu.mhutti1.healthchain.storage.EventNode;
import eu.mhutti1.healthchain.storage.EventStorage;
import eu.mhutti1.healthchain.utils.Crypto;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by jedraz on 31/10/2018.
 */
public abstract class CreateRequestHandler extends NonEventConsumer {

  public abstract String getApproveEndpoint();

  public String getDismissEndpoint() {
    return "dismiss_notification";
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {

    httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    String query = httpExchange.getRequestURI().getQuery();
    Map<String, String> params = RequestUtils.queryToMap(query);

    String password = params.get("password");
    String username = params.get("username");
    String issuerDid = params.getOrDefault("issuer_did", "Th7MpTaRZVRYnPiabds81Y");


    String response = "Request sent";
    int responseCode = RequestUtils.statusOK();


    JSONObject payload = new JSONObject()
            .put("password", password)
            .put("username", username);

    // later on register for email notofication

    EventStorage.store(issuerDid, new EventNode("Registration request", username, payload, getApproveEndpoint(), getDismissEndpoint()));

    httpExchange.sendResponseHeaders(responseCode, response.length());
    OutputStream os = httpExchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
