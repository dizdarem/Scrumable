package at.htl_villach.scrumable.bll;

public class Task extends BacklogItem {
    private int task_id;
    public Task(int backlogItem_id, String title, String describtion, StatusEnum status, User editor, int task_id) {
        super(backlogItem_id, title, describtion, status, editor);
        this.task_id = task_id;
    }
}
