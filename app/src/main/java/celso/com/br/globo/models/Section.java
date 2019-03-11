package celso.com.br.globo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import celso.com.br.globo.base.BaseModel;

public class Section extends BaseModel {

    @SerializedName("nome")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


