package at.htl_villach.scrumable.bll;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date date;

    public User(String username, String password, Date date) {
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public User(String username) {
        this.username = username;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return username.toString();
    }
}