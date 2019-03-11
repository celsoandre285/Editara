package celso.com.br.globo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import celso.com.br.globo.base.BaseModel;

public class Imagen extends BaseModel {

    @SerializedName("autor")
    @Expose
    private String author;

    @SerializedName("fonte")
    @Expose
    private String source;

    @SerializedName("legenda")
    @Expose
    private String subtitle;

    @SerializedName("url")
    @Expose
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
