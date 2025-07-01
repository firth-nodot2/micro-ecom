package org.rhydo.microecom.dtos;

import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
