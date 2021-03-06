package steekezexchange.yaid.com.steekezexchange.mvp;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public interface DataFinishedListener {
    void onFinished(String collectionName, List<SteekezItem> myCollection, List<FriendItem> friends);
}
