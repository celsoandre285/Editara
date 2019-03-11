package celso.com.br.globo.models;

import java.util.List;

public class Publish {

    private Content featuredNews;

    private List<Content> contents;

    public Content getFeaturedNews() {
        return featuredNews;
    }

    public void setFeaturedNews(Content featuredNews) {
        this.featuredNews = featuredNews;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

}
