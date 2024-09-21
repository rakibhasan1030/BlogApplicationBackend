package org.rakibhasan.blog.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer id;

    @NotEmpty(message = "Title cannot be empty.")
    @Size(max = 100, message = "Category title must not exceed 100 characters.")
    private String categoryTitle;

    @NotEmpty(message = "Description cannot be empty.")
    private String categoryDescription;

}
