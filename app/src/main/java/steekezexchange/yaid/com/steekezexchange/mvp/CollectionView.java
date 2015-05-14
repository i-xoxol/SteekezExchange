package steekezexchange.yaid.com.steekezexchange.mvp;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public interface CollectionView {

    public void showProgress();

    public void hideProgress();

    public void setItems(List<FriendItem> items);
}
