package alura.semana.imersao.java;

public enum ApiReader {
    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
            new ImdbApiContentExtractor()),

    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json",
            new NasaApiContentExtractor()),

    TOP_TV("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
            new ImdbApiContentExtractor());

    private String url;
    private ContentExtractor extractor;

    ApiReader(String url, ContentExtractor extractor){
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}
