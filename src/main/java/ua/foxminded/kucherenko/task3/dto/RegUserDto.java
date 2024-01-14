package ua.foxminded.kucherenko.task3.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegUserDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
}
