package org.rakibhasan.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.rakibhasan.blog.entities.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private int id;

    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long.")
    private String name;

    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters long.")
    private String password;

    @NotEmpty(message = "About section cannot be empty.")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}
