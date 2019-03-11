package celso.com.br.globo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import celso.com.br.globo.R;
import celso.com.br.globo.models.Content;
import celso.com.br.globo.models.Imagen;
import celso.com.br.globo.utils.ListUtils;
import celso.com.br.globo.utils.PicassoUtil;
import celso.com.br.globo.utils.StringUtils;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {


    private Context mContext;
    private List<Content>contents;
    private INewsAdapter event;

    public void setList(List<Content> contents) {
        this.contents = contents;
        this.notifyDataSetChanged();
    }

    public void setListener(INewsAdapter event) {
        this.event = event;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);

        mContext = parent.getContext();

        ViewHolder viewHolder = new ViewHolder(itemView);
        itemView.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Content mContent = contents.get(position);

        if (mContent != null) {

            viewHolder.mLayoutItem.setTag(mContent);


            viewHolder.mTextViewType.setText(mContent.getSection().getName().toUpperCase());
            viewHolder.mTextViewTitle.setText(mContent.getTitle());

            if (ListUtils.listEmptyOrNull(mContent.getImagens()) == false) {
                Imagen imagen = mContent.getImagens().get(0);

                if (StringUtils.isNullOrEmpty(imagen.getUrl()) == false){
                    PicassoUtil.setImagem(mContext, imagen.getUrl(), viewHolder.imageViewNews);

                }else{
                    PicassoUtil.setImagemNotFound(mContext, viewHolder.imageViewNews);
                }

            }else{
                PicassoUtil.setImagemNotFound(mContext, viewHolder.imageViewNews);
            }

            viewHolder.mLayoutItem.setOnClickListener(view  ->{
                Content content = (Content) view.getTag();
                event.onClickItem(content);
            });
        }

        viewHolder.mLayoutItem.setTag(mContent);

    }

    @Override
    public int getItemCount() {
        return contents== null ? 0 : contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        protected ImageView imageViewNews;
        protected TextView mTextViewType;
        protected TextView mTextViewTitle;
        protected LinearLayout mLayoutItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewNews  = itemView.findViewById(R.id.image_view_news);
            mTextViewType  = itemView.findViewById(R.id.text_view_type);
            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mLayoutItem    = itemView.findViewById(R.id.layout_item);
        }
    }

    public interface INewsAdapter{
        void onClickItem(Content content);
    }
}
