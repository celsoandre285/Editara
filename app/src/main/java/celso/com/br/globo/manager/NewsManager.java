package celso.com.br.globo.manager;


import celso.com.br.globo.business.NewsBusiness;
import celso.com.br.globo.models.Publish;
import celso.com.br.globo.operation.OperationsListener;

public class NewsManager {

    private NewsBusiness mNewsBusiness;

    public NewsManager() {
        this.mNewsBusiness = new NewsBusiness();
    }

    public void getAllNews(final OperationsListener<Publish> listener){
        mNewsBusiness.getAllNews(listener);
    }
}
