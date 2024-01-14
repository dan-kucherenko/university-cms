package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Teacher extends UserEntity {
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private Double salary;

    public Teacher(UserEntity user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public String getInitials() {
        return super.getLastName() + ' ' + super.getFirstName().charAt(0) + '.';
    }
}


