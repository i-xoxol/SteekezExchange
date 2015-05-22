package steekezexchange.yaid.com.steekezexchange.entity;

/**
 * Created by Игорь on 13.05.2015.
 */
public class FriendItem {
    private String email;
    private boolean interesting;

    public boolean isInteresting() {
        return interesting;
    }

    public void setInteresting(boolean interesting) {
        this.interesting = interesting;
    }

    public FriendItem(String email, boolean interesting) {

        this.email = email;
        this.interesting = interesting;
    }

    public FriendItem(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if(o instanceof FriendItem)
        {
            if(((FriendItem) o).getEmail().equals(email))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
