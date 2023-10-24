package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int teacherId;
    @NonNull
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Date dob;
    private int age;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    @ManyToOne
    private Department department;
    private int salary;
}
