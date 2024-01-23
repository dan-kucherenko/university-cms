package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrators")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Administrator extends UserEntity {
    public Administrator(UserEntity user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
        this.setRole(user.getRole());
        this.setPassword(user.getPassword());
    }

    public Administrator(long id, String firstName, String lastName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }
}
