package celso.com.br.globo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import celso.com.br.globo.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitialize {

    private final Retrofit mRetrofit;

    public RetrofitInitialize() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //configuro o Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.URLS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public  NewsService getNewsService() {
        return mRetrofit.create(NewsService.class);

    }

}
