package lk.greenshadow.greens.dto.impl;

import lk.greenshadow.greens.dto.UserStatus;
import lk.greenshadow.greens.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String id;
    private String email;
    private String password;
    private Role role;
    private String staffId;
}
