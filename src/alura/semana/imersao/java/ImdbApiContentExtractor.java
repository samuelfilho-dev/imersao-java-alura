package alura.semana.imersao.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ImdbApiContentExtractor implements ContentExtractor {

    JsonParse imdbparse = new JsonParse();
    Properties env = EnvReader.getEnv();

    @Override
    public List<Content> extractContents(String json){

        List<Map<String, String>> attributesList = imdbparse.parse(json);

        System.out.println(ConsoleColors.BLUE_BOLD + "Size of List: " +
                ConsoleColors.RESET + attributesList.size()); // Show Size of List

        List<Content> contents = new ArrayList<>();

        // Popule list of Contents

        for (Map<String, String> attributes : attributesList) {

            String title = attributes.get("title");
            String urlImage = attributes.get("image");

            Content content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }

    public void printMovieList(String json) throws Exception {

        var maker = new StickerMaker();
        List<Map<String, String>> movielist = imdbparse.parse(json);

        for (Map<String, String> movies : movielist) {

            double stars = Double.parseDouble(movies.get("imDbRating")); // Parse Imdb Rating in Double Number
            double classification = Double.parseDouble(movies.get("imDbRating")); // Parse Imdb Rating in Double Number
            int amountOfStars = (int) stars;

            String stickerText;
            InputStream myImage;
            String pathImageTop = env.getProperty("IMAGE_TOP");
            String pathImageHum = env.getProperty("IMAGE_HUM");

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Titulo: " + ConsoleColors.GREEN_BOLD + movies.get("title")
                    + ConsoleColors.RESET); // Show Title

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Imagem: " + ConsoleColors.WHITE_BOLD + movies.get("image")
                    + ConsoleColors.RESET);// Show Image

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Nota De Avaliação: " +
                    ConsoleColors.RESET + movies.get("imDbRating")); // Show Rating

            for (int a = 0; a < amountOfStars; a++) {
                System.out.print(ConsoleColors.PURPLE_BACKGROUND + "⭐" + ConsoleColors.RESET); // Show Star Of Rating
            }

            if (classification >= 9.0){
                stickerText = "TOPZERA";
                myImage = new FileInputStream
                        (new File(pathImageTop));
            }else{
                myImage = new FileInputStream
                        (new File(pathImageHum));
                stickerText = "HUMMMM";
            }

            System.out.println("\n");

            String imageUrl = movies.get("image");
            String nameFile = movies.get("title") + ".png";

            InputStream inputStream = new URL(imageUrl).openStream();
            maker.create(inputStream, nameFile, stickerText, myImage);
        }


    }

}
