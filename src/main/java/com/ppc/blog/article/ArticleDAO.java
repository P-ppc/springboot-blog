package com.ppc.blog.article;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ppc.blog.article.ArticleEntity;

@Repository
public interface ArticleDAO
extends JpaSpecificationExecutor<ArticleEntity>,
        CrudRepository<ArticleEntity, String> {
  ArticleEntity findOneById(String id);
  List<ArticleEntity> findAll(Specification<ArticleEntity> spec, Sort sort);
  Page<ArticleEntity> findAll(Specification<ArticleEntity> spec, Pageable pageable);
}
