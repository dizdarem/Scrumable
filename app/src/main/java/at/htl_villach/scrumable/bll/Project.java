package at.htl_villach.scrumable.bll;

public class Project {
    private String projectName;
    private String describtion;

    public Project(String projectName, String describtion) {
        this.projectName = projectName;
        this.describtion = describtion;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", describtion='" + describtion + '\'' +
                '}';
    }
}