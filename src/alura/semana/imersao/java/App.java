package alura.semana.imersao.java;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Connection HTTP for IMdb ApiReader and find 250 top Movies

        ApiReader api = ApiReader.TOP_TV;
        ContentExtractor extractor = api.getExtractor();

        String url = api.getUrl(); // URL

        ClientHttp http = new ClientHttp(); // Create new Client
        String json = http.findData(url); // Find Data of URL


        // Show data

        var maker = new StickerMaker();

        List<Content> contents = extractor.extractContents(json);

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);
            String nameFile = content.title() + ".png";

            InputStream inputStream = new URL(content.imageUrl()).openStream(); // Read the URL of an image
            maker.create(inputStream, nameFile, "TOPZERA"); // Create a Stiker of this image

            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Titulo: "
                    + ConsoleColors.GREEN_BOLD + (content.title() + ConsoleColors.RESET)); // Show Title

        }


    }

}
