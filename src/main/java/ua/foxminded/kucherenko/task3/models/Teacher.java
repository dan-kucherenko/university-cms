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
@EqualsAndHashCode
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private int teacherId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Integer age;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private Double salary;
}
