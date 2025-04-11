package edu.icet.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Integer age;
}
