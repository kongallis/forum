package com.kongallis.forum.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne()
    @JoinColumn
    private Post post;
    @ManyToOne()
    @JoinColumn
    private User user;
    @NotBlank
    private String body;
    @NotBlank
    private Date createdAt;

}
