package celso.com.br.globo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import celso.com.br.globo.base.BaseModel;

public class Contents extends BaseModel {

    @SerializedName("conteudos")
    @Expose
    private List<Content> contents = null;
    @SerializedName("produto")
    @Expose
    private String product;

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
