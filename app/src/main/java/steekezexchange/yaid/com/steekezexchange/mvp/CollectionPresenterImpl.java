package steekezexchange.yaid.com.steekezexchange.mvp;

import android.content.Context;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public class CollectionPresenterImpl implements CollectionPresenter, CollectionFinishedListener {

    private CollectionView collectionView;
    private Context context;
    private LoadCollectionInteractor loadCollectionInteractor;
    private String colName;

    public CollectionPresenterImpl(CollectionView collectionView, Context context, String colName) {
        this.collectionView = collectionView;
        this.context = context;
        this.loadCollectionInteractor = new LoadCollectionInteractorImpl(context, colName);
        this.colName = colName;
    }

    @Override
    public void onLoad() {
        collectionView.showProgress();
        loadCollectionInteractor.loadItemsFromInternalStorage(colName,this);
    }

    @Override
    public void onFinished(List<SteekezItem> items) {
        collectionView.setItems(items);
        collectionView.hideProgress();
    }

}
