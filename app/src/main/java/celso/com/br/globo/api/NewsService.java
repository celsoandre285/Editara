package celso.com.br.globo.api;

import java.util.List;

import celso.com.br.globo.models.Contents;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("Infoglobo/desafio-apps/master/capa.json")
    Call<List<Contents>> getAllNews();

}
