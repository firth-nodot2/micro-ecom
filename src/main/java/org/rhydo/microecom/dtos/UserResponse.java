package org.rhydo.microecom.dtos;

import lombok.Data;
import org.rhydo.microecom.enums.UserRole;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
