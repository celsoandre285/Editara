package celso.com.br.globo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import celso.com.br.globo.base.BaseModel;
import celso.com.br.globo.utils.DateTimeUtils;

public class Content extends BaseModel {

    @SerializedName("autores")
    @Expose
    private List<String> authors = null;

    @SerializedName("informePublicitario")
    @Expose
    private Boolean advertisingReport;

    @SerializedName("subTitulo")
    @Expose
    private String subtitle;

    @SerializedName("texto")
    @Expose
    private String text;

    @SerializedName("videos")
    @Expose
    private List<Object> videos = null;

    @SerializedName("atualizadoEm")
    @Expose
    private String updated;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("publicadoEm")
    @Expose
    private String postedIn;

    @SerializedName("secao")
    @Expose
    private Section section;

    @SerializedName("tipo")
    @Expose
    private String type;

    @SerializedName("titulo")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("urlOriginal")
    @Expose
    private String urlOriginal;

    @SerializedName("imagens")
    @Expose
    private List<Imagen> imagens = null;



    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Boolean getAdvertisingReport() {
        return advertisingReport;
    }

    public void setAdvertisingReport(Boolean advertisingReport) {
        this.advertisingReport = advertisingReport;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostedIn() {
        return postedIn;
    }

    public void setPostedIn(String postedIn) {
        this.postedIn = postedIn;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public List<Imagen> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagen> imagens) {
        this.imagens = imagens;
    }

    public Date getDateTimePostIn(){
        return DateTimeUtils.convertToDate( getPostedIn(), DateTimeUtils.Pattern.DATETIME_FULL);
    }


}
