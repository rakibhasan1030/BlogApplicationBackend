package org.rakibhasan.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 10000)
    private String content;

    private String image;

    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments  = new HashSet<>();

}






































