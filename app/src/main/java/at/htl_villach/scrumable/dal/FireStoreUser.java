package at.htl_villach.scrumable.dal;

import java.io.Serializable;

public class FireStoreUser implements  Serializable{
    private String info;

    public FireStoreUser(String info) {
        this.info = info;
    }

    public FireStoreUser() {
        this("nix");
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
