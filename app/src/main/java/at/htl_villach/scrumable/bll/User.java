package at.htl_villach.scrumable.bll;

import android.widget.DatePicker;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private DatePicker date;

    public User(String username, String password, DatePicker date) {
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }
}