package edu.icet.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
