package Library.Entity;

public class Member {
    private String name;
    private Long schoolID;
    private Boolean hasAccess = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Long schoolID) {
        this.schoolID = schoolID;
    }

    public Boolean getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", schoolID=" + schoolID +
                ", hasAccess=" + hasAccess +
                '}';
    }
}
