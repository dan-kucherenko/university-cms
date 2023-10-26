package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer studentId;
    private int groupId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Date dob;
    private int age;
    private String email;
    private String phone;
    private Integer yearOfStudy;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
}
