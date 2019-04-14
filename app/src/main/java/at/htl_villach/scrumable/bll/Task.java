package at.htl_villach.scrumable.bll;

public class Task extends BacklogItem {
    public Task(int id, String title, String describtion, StatusEnum status) {
        super(id, title, describtion, status);
    }
}
