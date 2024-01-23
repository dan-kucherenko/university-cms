package ua.foxminded.kucherenko.task3.controller.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    private int id;
    private int roleId;
}
