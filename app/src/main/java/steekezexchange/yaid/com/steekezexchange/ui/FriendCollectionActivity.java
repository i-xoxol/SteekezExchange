package steekezexchange.yaid.com.steekezexchange.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;
import steekezexchange.yaid.com.steekezexchange.mvp.CollectionPresenter;
import steekezexchange.yaid.com.steekezexchange.mvp.CollectionPresenterImpl;
import steekezexchange.yaid.com.steekezexchange.mvp.CollectionView;
import steekezexchange.yaid.com.steekezexchange.utils.FileHelper;
import steekezexchange.yaid.com.steekezexchange.utils.Parser;

/**
 * Created by Игорь on 16.05.2015.
 */
public class FriendCollectionActivity extends Activity implements CollectionFragment.SaveDataListener, CollectionView{

    private String ownerName;
    public final static String NAME_KEY = "NAME_KEY";

    private ProgressBar progressBar;
    private FrameLayout container;
    CollectionFragment collectionFragment;
    CollectionPresenter collectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_collection);

        ownerName = getIntent().getStringExtra(NAME_KEY);
        if(ownerName==null || ownerName.length()==0)
            finish();

        getActionBar().setTitle(ownerName);

        initViews();

        if (findViewById(R.id.frameForCollection)!=null)
        {
            if(savedInstanceState != null)
                return;

            collectionFragment = (CollectionFragment) getFragmentManager().findFragmentById(R.id.frameForCollection);

            if(collectionFragment==null)
                collectionFragment = new CollectionFragment();

            getFragmentManager().beginTransaction().add(R.id.frameForCollection, collectionFragment).commit();
        }
        collectionPresenter = new CollectionPresenterImpl(this,this,ownerName);
    }

    private void initViews()
    {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        container = (FrameLayout)findViewById(R.id.frameForCollection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        collectionPresenter.onLoad();
    }

    @Override
    public void saveData(String collectionName, ArrayList<SteekezItem> collection) {
        if(collection!=null)
            FileHelper.writeFile(this, collectionName, Parser.getStringData(collectionName, collection));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        container.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<SteekezItem> items) {
        if(collectionFragment!=null)
            if (collectionFragment.isAdded())
                collectionFragment.showCollection(ownerName, (ArrayList<SteekezItem>) items);
            else
                collectionFragment.prepareData(ownerName,(ArrayList<SteekezItem>) items);

    }
}
