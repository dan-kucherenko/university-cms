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
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Administrator(UserEntity user) {
        this.setUser(user);
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhone());
        this.setRole(user.getRole());
    }
}
