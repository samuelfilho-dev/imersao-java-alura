package alura.semana.imersao.java;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // TODO: Connection HTTP for IMdb API and find 250 top Movies

        String url = System.getenv("URL"); // URL

        URI addressApi = URI.create(url); // Convert URL in URI
        HttpClient client = HttpClient.newHttpClient(); // Client

        HttpRequest request = HttpRequest.newBuilder().uri(addressApi).GET().build(); // Request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Response
        String body = response.body(); // Body of Response


        // TODO: Parse title,image,Rating of Imdb API

        JsonParse parse = new JsonParse(); // Create the object of class JsonParse

        List<Map<String, String>> movielist = parse.parse(body); // Create a List just titles, images, and Rating of movies
        System.out.println(ConsoleColors.BLUE_BOLD + "Size of Movie List: " + ConsoleColors.RESET + movielist.size()); // Show Size of List

        // TODO: Show data

        for (Map<String, String> movie : movielist) {

           double stars = Double.parseDouble(movie.get("imDbRating")); // Parse Imdb Rating in Double Number
           int amountOfStars = (int) stars;


            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Titulo: " + ConsoleColors.GREEN_BOLD + (movie.get("title")
                    + ConsoleColors.RESET)); // Show Title

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Imagem: " + ConsoleColors.WHITE_BOLD + movie.get("image")
                    + ConsoleColors.RESET);// Show Image

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Nota De Avaliação: " +
                    ConsoleColors.RESET +  movie.get("imDbRating")); // Show Rating

            for (int i = 0; i < amountOfStars; i++) {
                System.out.print(ConsoleColors.PURPLE_BACKGROUND + "⭐" + ConsoleColors.RESET); // Show Star Of Rating
            }

            System.out.println("\n");
        }

    }
}
