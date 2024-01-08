package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @ManyToOne
    private Role role;
}
