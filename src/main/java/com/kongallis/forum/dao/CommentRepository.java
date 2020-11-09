package com.kongallis.forum.dao;

import com.kongallis.forum.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public interface CommentRepository  extends JpaRepository<Post, Long> {
}
