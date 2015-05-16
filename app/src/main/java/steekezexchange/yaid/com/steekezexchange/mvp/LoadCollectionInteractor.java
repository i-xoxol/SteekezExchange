package steekezexchange.yaid.com.steekezexchange.mvp;

/**
 * Created by Игорь on 16.05.2015.
 */
public interface LoadCollectionInteractor {
    public void loadItemsFromInternalStorage(String name, CollectionFinishedListener listener);
}

