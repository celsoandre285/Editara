package celso.com.br.globo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import celso.com.br.globo.R;

public class PicassoUtil {

    public static void setImagem(Context mContext, String url,ImageView mImageView){
        Picasso.with(mContext)
        .load(url)
        .into(mImageView);
    }

    public static void setImagemNotFound(Context mContext,ImageView mImageView){
        Picasso.with(mContext)
                .load(R.mipmap.not_found)
                .into(mImageView);
    }

}
