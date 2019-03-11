package celso.com.br.globo.business;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;

import java.util.Iterator;
import java.util.List;

import celso.com.br.globo.models.Content;
import celso.com.br.globo.models.Contents;
import celso.com.br.globo.models.Publish;
import celso.com.br.globo.operation.OperationsListener;
import celso.com.br.globo.repository.NewsRepository;
import celso.com.br.globo.utils.ListUtils;
import celso.com.br.globo.utils.StringUtils;

public class NewsBusiness {

    private NewsRepository mNewsRepository;

    public NewsBusiness() {
        this.mNewsRepository = new NewsRepository();
    }

    public void getAllNews(final OperationsListener<Publish> listener) {


        mNewsRepository.getAllNews(new OperationsListener<List<Contents>>() {
            @Override
            public void OnSuccess(List<Contents> contents) {
                if (ListUtils.listEmptyOrNull(contents) == false) {
                    listener.OnSuccess(convertPublisher(contents));
                }
            }

            @Override
            public void OnFailure(int errorCode, String errorMessage) {
                listener.OnFailure(errorCode, errorMessage);
            }
        });

    }


    private Publish convertPublisher(List<Contents> mListResult) {

        Publish publish = new Publish();
        List<Content> contents = mListResult.get(0).getContents();


        Optional<Content> contentOptional = Stream.of(contents)
                .filter(p -> ListUtils.listEmptyOrNull(p.getImagens()) == false)
                .findFirst();

        Iterator<Content> iterator = contents.iterator();

        while (iterator.hasNext()) {
            Content mContent = iterator.next();
            if (mContent.getId().equals(contentOptional.get().getId()) || StringUtils.isNullOrEmpty(mContent.getText()) == true) {
                iterator.remove();
            }
        }

        publish.setFeaturedNews(contentOptional.get());
        publish.setContents(contents);

        return publish;
    }


}
