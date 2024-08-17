package org.rakibhasan.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
