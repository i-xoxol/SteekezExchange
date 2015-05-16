package steekezexchange.yaid.com.steekezexchange.mvp;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public interface CollectionFinishedListener {
    void onFinished(List<SteekezItem> items);
}
