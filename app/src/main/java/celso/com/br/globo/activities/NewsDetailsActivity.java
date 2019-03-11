package celso.com.br.globo.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import celso.com.br.globo.R;
import celso.com.br.globo.base.BaseActivity;
import celso.com.br.globo.interfaces.IActivity;
import celso.com.br.globo.models.Content;
import celso.com.br.globo.models.Imagen;
import celso.com.br.globo.utils.Constants;
import celso.com.br.globo.utils.DateTimeUtils;
import celso.com.br.globo.utils.ListUtils;
import celso.com.br.globo.utils.PicassoUtil;
import celso.com.br.globo.utils.StringUtils;

public class NewsDetailsActivity extends BaseActivity implements IActivity {

    //Properties
    private Content mContent;

    //Components
    private TextView mTextViewTitleDetail;
    private TextView mTextViewTextDetail;
    private TextView mTextViewInfo;
    private ImageView mImageViewDestak;
    private TextView mTextViewImageText;
    private TextView mTextViewNews;
    private TextView mTextViewInfoDateTime;
    private Toolbar mToolbar;
    private TextView mTextViewToolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        this.initialize();
        this.loadData();
        this.loadViews();
        this.events();
    }

    @Override
    public void initialize() {

        mTextViewTitleDetail = findViewById(R.id.text_view_title_detail);
        mTextViewTextDetail = findViewById(R.id.text_view_text_detail);
        mTextViewInfo = findViewById(R.id.text_view_info);
        mTextViewInfoDateTime = findViewById(R.id.text_view_info_date_time);
        mImageViewDestak = findViewById(R.id.image_view_destak);
        mTextViewImageText = findViewById(R.id.text_view_image_text);
        mTextViewNews = findViewById(R.id.text_view_news_detail);
        mToolbar = findViewById(R.id.toolbar);
        mTextViewToolbarTitle = findViewById(R.id.toolbar_title);

        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

    }

    @Override
    public void loadData() {
        Bundle bundleExtra = getIntent().getBundleExtra(MainActivity.BUNDLE_CONTENT);
        if (bundleExtra != null) {
            mContent = (Content) bundleExtra.getSerializable(MainActivity.BUNDLE_CONTENT);
        }

    }

    @Override
    public void loadViews() {
        mToolbar.setNavigationIcon(this.getDrawable(R.drawable.ic_arrow_white));

        mTextViewToolbarTitle.setText(mContent.getSection().getName().toUpperCase());

        mTextViewTitleDetail.setText(mContent.getTitle());

        mTextViewTextDetail.setText(mContent.getSubtitle());

        if (ListUtils.listEmptyOrNull(mContent.getAuthors()) == false)
            mTextViewInfo.setText(mContent.getAuthors().get(0));

        mTextViewInfoDateTime.setText(DateTimeUtils.applyDateTimeWithHourMinute(mContent.getDateTimePostIn(), true));

        if (ListUtils.listEmptyOrNull(mContent.getImagens()) == false) {

            Imagen mIagem = mContent.getImagens().get(0);

            PicassoUtil.setImagem(this, mIagem.getUrl(), mImageViewDestak);

            if (StringUtils.isNullOrEmpty(mIagem.getSubtitle()) == false && StringUtils.isNullOrEmpty(mIagem.getSource()) == false) {
                String subtitle = String.format("%s " + getString(Constants.ConstantsStrings.PICTURE) + ": %s", mIagem.getSubtitle(), mIagem.getSource());

                mTextViewImageText.setVisibility(View.VISIBLE);
                mTextViewImageText.setText(subtitle);
            }


        } else {
            PicassoUtil.setImagemNotFound(this, mImageViewDestak);
        }

        mTextViewNews.setText(mContent.getText());

    }

    @Override
    public void events() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                finish();

                return true;

            case R.id.action_news_share:

                shareTextUrl();

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_SUBJECT, mContent.getTitle());
        share.putExtra(Intent.EXTRA_TEXT, mContent.getUrl());

        startActivity(Intent.createChooser(share, getString(Constants.ConstantsStrings.TEXT_SHARE)));
    }

}
