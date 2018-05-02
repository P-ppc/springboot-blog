package com.ppc.blog.comment;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ppc.blog.comment.CommentEntity;

@Repository
public interface CommentDAO
extends JpaSpecificationExecutor<CommentEntity>,
        CrudRepository<CommentEntity, String> {
  CommentEntity findOneById(String, id);
  List<CommentEntity> findByArticleIdOrderByCreatedTimeDesc(String articleId);
}
