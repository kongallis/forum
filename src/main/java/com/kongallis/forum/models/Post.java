package com.kongallis.forum.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    private Date createdDate;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User userId;

//    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @ManyToOne()
    @JoinColumn
    private User userId;

    @OneToMany(targetEntity=Comment.class, mappedBy="post", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
