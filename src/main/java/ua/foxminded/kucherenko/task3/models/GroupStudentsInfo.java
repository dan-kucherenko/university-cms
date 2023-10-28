package ua.foxminded.kucherenko.task3.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GroupStudentsInfo {
    @Id
    private int groupId;
    private String groupName;
    private long numOfStudents;
}