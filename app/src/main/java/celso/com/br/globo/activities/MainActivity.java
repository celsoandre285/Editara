package celso.com.br.globo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import celso.com.br.globo.adapters.AdapterNews;
import celso.com.br.globo.R;
import celso.com.br.globo.base.BaseActivity;
import celso.com.br.globo.interfaces.IActivity;
import celso.com.br.globo.interfaces.InfraUtils;
import celso.com.br.globo.manager.NewsManager;
import celso.com.br.globo.models.Content;
import celso.com.br.globo.models.Imagen;
import celso.com.br.globo.models.Publish;
import celso.com.br.globo.operation.OperationsListener;
import celso.com.br.globo.utils.Constants;
import celso.com.br.globo.utils.ShimmerUtil;

public class MainActivity extends BaseActivity implements IActivity, AdapterNews.INewsAdapter, View.OnClickListener {

    public static final String BUNDLE_CONTENT = "bundleContent";

    //Properties
    private NewsManager newsManager;
    private AdapterNews mAdapterNews;
    private Content mContent;

    //Components
    private ImageView mImageViewDestak;
    private TextView mTextViewSection;
    private TextView mTextViewText;
    private ShimmerFrameLayout mShimmerFrameLayout;
    private RecyclerView mRecycleViewNews;
    private ConstraintLayout mConstraintLayoutImage;
    private Toolbar mToolbar;
    private TextView mTextViewToolbarTitle;
    private LinearLayout mLayoutFullPosts;
    private LinearLayout mLayoutPostNotFound;
    private LinearLayout mLayoutNotFoundInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.initialize();
        this.loadData();
        this.loadViews();
        this.events();
    }

    @Override
    public void initialize() {
        newsManager = new NewsManager();

        mLayoutFullPosts = findViewById(R.id.layout_full_content);
        mLayoutPostNotFound = findViewById(R.id.layout_empty);
        mLayoutNotFoundInternet = findViewById(R.id.layout_not_internet);

        mImageViewDestak = findViewById(R.id.image_view_destak);
        mTextViewSection = findViewById(R.id.text_view_section);
        mTextViewText = findViewById(R.id.text_view_text);
        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        mRecycleViewNews = findViewById(R.id.recycle_view_news);
        mConstraintLayoutImage = findViewById(R.id.constraint_layout_image);

        mToolbar = findViewById(R.id.toolbar);
        mTextViewToolbarTitle = findViewById(R.id.toolbar_title);





    }

    @Override
    public void loadData() {


    }

    @Override
    public void loadViews() {

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(this.getDrawable(R.drawable.as_above));

        mAdapterNews = new AdapterNews();
        mAdapterNews.setListener(this);
        mRecycleViewNews.setLayoutManager(new LinearLayoutManager(this));
        mRecycleViewNews.addItemDecoration(new DividerItemDecoration(mRecycleViewNews.getContext(), DividerItemDecoration.VERTICAL));
        mRecycleViewNews.setAdapter(mAdapterNews);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        mTextViewToolbarTitle.setText(getString(Constants.ConstantsStrings.TOOLBAR_TITLE).toUpperCase());



        if(InfraUtils.isConnectingToInternet(this)==true) {

            ShimmerUtil.start(mShimmerFrameLayout);

            newsManager.getAllNews(new OperationsListener<Publish>() {
                @Override
                public void OnSuccess(Publish publish) {
                    if (publish != null) {
                        setImageDestak(publish);

                        mAdapterNews.setList(publish.getContents());
                        mLayoutFullPosts.setVisibility(View.VISIBLE);
                        ShimmerUtil.stop(mShimmerFrameLayout);
                    } else {
                        mLayoutPostNotFound.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void OnFailure(int errorCode, String errorMessage) {
                    ShimmerUtil.stop(mShimmerFrameLayout);
                }
            });

        }else{
            mLayoutNotFoundInternet.setVisibility(View.VISIBLE);
            ShimmerUtil.stop(mShimmerFrameLayout);
        }

    }

    @Override
    public void events() {
        mConstraintLayoutImage.setOnClickListener(this);

    }

    private void setImageDestak(Publish publish) {


        if (publish != null) {

            mContent = publish.getFeaturedNews();

            Imagen imagen = mContent.getImagens().get(0);

            mTextViewSection.setText(mContent.getSection().getName());
            mTextViewText.setText(mContent.getTitle());

            Picasso.with(this)
                    .load(imagen.getUrl())
                    .priority(Picasso.Priority.HIGH)
                    .into(mImageViewDestak);

        }

    }

    @Override
    public void onClickItem(Content content) {
        Log.i("onClickItem", "onClickItem: " + content.getTitle());
        seeDetail(content);
    }

    private void seeDetail(Content content) {
        Bundle bundle = new Bundle();

        bundle.putSerializable(BUNDLE_CONTENT, content);
        Intent mIntent = new Intent(this, NewsDetailsActivity.class);
        mIntent.putExtra(BUNDLE_CONTENT, bundle);

        startActivity(mIntent);
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        switch (viewId) {
            case R.id.constraint_layout_image:
                seeDetail(mContent);
                break;
            default:
                break;
        }

    }


}
