package org.rakibhasan.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

    private Integer id;

    @NotEmpty(message = "Title cannot be empty.")
    @Size(max = 300, message = "Category title must not exceed 300 characters.")
    private String title;

    @NotEmpty(message = "Content cannot be empty.")
    @Size(max = 10000, message = "Post content must not exceed 10000 characters.")
    private String content;

    @NotEmpty(message = "Image cannot be empty.")
    private String image;

    private Date date;

    private CategoryDto category;

    private UserDto user;

}
