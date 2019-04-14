package at.htl_villach.scrumable.bll;

/**
 * Created by pupil on 4/11/19.
 */

public class BacklogItem {
    private int id;
    private String title;
    private String describtion;
    private StatusEnum status = StatusEnum.PRODUCT_BACKLOG; //default: in Product backlog

    public BacklogItem(int id, String title, String describtion, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.describtion = describtion;
        this.status = status;
    }

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

    @Override
    public String toString() {
        return "BacklogItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", describtion='" + describtion + '\'' +
                ", status=" + status +
                '}';
    }
}