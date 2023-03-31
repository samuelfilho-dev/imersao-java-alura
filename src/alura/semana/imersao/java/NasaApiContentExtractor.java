package alura.semana.imersao.java;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NasaApiContentExtractor implements ContentExtractor {

    @Override
    public List<Content> extractContents(String json){

        JsonParse nasaParse = new JsonParse(); // Create the object of class JsonParse

        List<Map<String, String>> attributesList = nasaParse.parse(json);

        System.out.println(ConsoleColors.BLUE_BOLD + "Size of List: " +
                ConsoleColors.RESET + attributesList.size()); // Show Size of List


        // Popule list of Contents

        List<Content> contents = attributesList.stream()
                .flatMap(attributes -> Stream.of(new Content(attributes.get("title"), attributes.get("url"))))
                .collect(Collectors.toList());

        return contents;
    }

}
