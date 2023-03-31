package alura.semana.imersao.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    public String findData(String url) {

        try {

            URI addressApi = URI.create(url); // Convert URL in URI
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(addressApi).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body(); // Body of Response

        } catch (IOException | InterruptedException e){
            throw new BusinessException("Can not possible a request api");
        }

    }

}
