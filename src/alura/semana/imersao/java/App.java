package alura.semana.imersao.java;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {

        // Connection HTTP for IMdb API and find 250 top Movies

        Properties env = EnvReader.getEnv(); // Create a .env reader
        String url = env.getProperty("URL"); // URL

        ClientHttp http = new ClientHttp(); // Create new Client
        String json = http.findData(url); // Find Data of URL


        // Show data

        var maker = new StickerMaker(); // Create a Sticker Maker
        ContentExtractor extractor = new ImdbApiContentExtractor();



        List<Content> contents = extractor.extractContents(json);

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);
            String nameFile = content.title() + ".png";

            InputStream inputStream = new URL(content.imageUrl()).openStream(); // Read the URL of an image
            maker.create(inputStream, nameFile, "BOM DIA",null); // Create a Stiker of this image

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Titulo: "
                    + ConsoleColors.GREEN_BOLD + (content.title() + ConsoleColors.RESET)); // Show Title


        }


    }

}
