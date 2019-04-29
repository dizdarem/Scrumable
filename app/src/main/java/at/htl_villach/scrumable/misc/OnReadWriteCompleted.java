package at.htl_villach.scrumable.misc;

import java.util.ArrayList;

import at.htl_villach.scrumable.bll.User;

public interface OnReadWriteCompleted {
    void onReadCompleted(ArrayList<User> collUsers);
    void onWriteCompleted(String message);
}