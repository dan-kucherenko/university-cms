package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
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
}
