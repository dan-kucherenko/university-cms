package ua.foxminded.kucherenko.task3.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GroupStudentsInfo {
    @Id
    private int groupId;
    private String groupName;
    private long numOfStudents;

    public GroupStudentsInfo() {
    }

    public GroupStudentsInfo(int groupId, String groupName, long numOfStudents) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.numOfStudents = numOfStudents;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public long getNumOfStudents() {
        return numOfStudents;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + groupId +
                ", name='" + groupName + '\'' +
                ", quantity=" + numOfStudents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupStudentsInfo that = (GroupStudentsInfo) o;

        if (getGroupId() != that.getGroupId()) return false;
        if (getNumOfStudents() != that.getNumOfStudents()) return false;
        return getGroupName() != null ? getGroupName().equals(that.getGroupName()) : that.getGroupName() == null;
    }

    @Override
    public int hashCode() {
        int result = getGroupId();
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        result = 31 * result + (int) (getNumOfStudents() ^ (getNumOfStudents() >>> 32));
        return result;
    }
}