package alura.semana.imersao.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaApiContentExtractor implements ContentExtractor {

    @Override
    public List<Content> extractContents(String json){

        JsonParse nasaParse = new JsonParse(); // Create the object of class JsonParse

        List<Map<String, String>> attributesList = nasaParse.parse(json);

        System.out.println(ConsoleColors.BLUE_BOLD + "Size of List: " +
                ConsoleColors.RESET + attributesList.size()); // Show Size of List

        List<Content> contents = new ArrayList<>();

        // Popule list of Contents

        for (Map<String, String> attributes : attributesList) {

            String title = attributes.get("title");
            String urlImage = attributes.get("url");

            Content content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }

}
