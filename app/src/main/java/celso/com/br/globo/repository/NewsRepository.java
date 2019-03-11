package celso.com.br.globo.repository;

import java.util.List;

import celso.com.br.globo.api.RetrofitInitialize;
import celso.com.br.globo.models.Contents;
import celso.com.br.globo.operation.OperationsListener;
import celso.com.br.globo.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    public void getAllNews(final OperationsListener<List<Contents>> operation) {


        Call<List<Contents>> mConteudos =  new RetrofitInitialize().getNewsService().getAllNews();

        mConteudos.enqueue(new Callback<List<Contents>>() {
            @Override
            public void onResponse(Call<List<Contents>> call, Response<List<Contents>> response) {

                if (response!=null && response.code() == Constants.StatusCodeHTTP.SUCESS){
                    operation.OnSuccess(response.body());
                }else{
                    operation.OnFailure(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Contents>> call, Throwable t) {
                if (t!=null){
                    operation.OnFailure(Constants.StatusCodeHTTP.BAD_REQUEST, t.getMessage());
                }
            }
        });


    }


}
