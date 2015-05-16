package steekezexchange.yaid.com.steekezexchange.mvp;

import android.content.Context;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public class DataPresenterImpl implements CollectionPresenter, DataFinishedListener {

    private MainView mainView;
    private Context context;
    private LoadDataInteractor loadDataInteractor;

    public DataPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        loadDataInteractor = new LoadDataInteractorImpl(context);
    }

    @Override
    public void onLoad() {
        mainView.showProgress();
        loadDataInteractor.loadItemsFromInternalStorage(this);
    }

    @Override
    public void onFinished(String collectionName, List<SteekezItem> myCollection, List<FriendItem> friendItems) {
        mainView.setItems(collectionName,myCollection,friendItems);
        mainView.hideProgress();
    }
}
