package at.htl_villach.scrumable.bll;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pupil on 4/11/19.
 */

public class BacklogItem implements Parcelable {
    private int id;
    private String title;
    private String describtion;
    private StatusEnum status = StatusEnum.PRODUCT_BACKLOG; //default: in Product backlog
    private User editor;

    public BacklogItem(int id, String title, String describtion, StatusEnum status, User editor) {
        this.id = id;
        this.title = title;
        this.describtion = describtion;
        this.status = status;
        this.editor = editor;
    }

    protected BacklogItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        describtion = in.readString();
        status = StatusEnum.valueOf(in.readString());
        editor = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<BacklogItem> CREATOR = new Creator<BacklogItem>() {
        @Override
        public BacklogItem createFromParcel(Parcel in) {
            return new BacklogItem(in);
        }

        @Override
        public BacklogItem[] newArray(int size) {
            return new BacklogItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "BacklogItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", describtion='" + describtion + '\'' +
                ", status=" + status +
                ", editor=" + editor +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(describtion);
        dest.writeString(status.toString());
        dest.writeParcelable( editor, flags);
    }
}