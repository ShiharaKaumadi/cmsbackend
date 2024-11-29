package lk.greenshadow.greens.dto.impl;

import lk.greenshadow.greens.dto.StaffStatus;
import lk.greenshadow.greens.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String gender;
    private String designation;
    private String email;
    private Date dob;
    private String address;
    private String contact;
    private Date joinDate;
    private Role role;
    private List<String> fieldIds;
}
