package com.ppc.blog.article;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;

import com.ppc.blog.article.ArticleEntity;
import com.ppc.blog.article.ArticleDAO;

public class ArticleService {
  @AutoWired
  private ArticleDAO articleDAO;
  
  private Sort sort = new Sort(Sort.Direction.DESC, "createdTime");

  private Specification<ArticleEntity> getWhereClause(Map<String, String> queryParams) {
    String title = queryParams.get("title");
    String content = queryParams.get("content");
    String authorId = queryParams.get("authorId");

    return new Specification<ArticleEntity>() {
      @Override
      public Predicate toPredicate(Root<ArticleEntity> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (StringUtils.isNotBlank(title)) {
          predicate.getExpressions().add(cb.like(r.<String>get("title"), "%" + StringUtils.trim(title) + "%"));
        }
        if (StringUtils.isNotBlank(content)) {
          predicate.getExpressions().add(cb.like(r.<String>get("content"), "%" + StringUtils.trim(content) + "%"));
        }
        if (StringUtils.isNotBlank(authorId)) {
          predicate.getExpressions().add(cb.equal(r.<String>get("authorId"), authorId));
        }
        return predicate;
      }
    }
  }

  public List<ArticleEntity> query(Map<String, String> queryParams) {
    return articleDAO.findAll(Specifications.where(getWhereClause(queryParams)), sort);
  }

  public Page<ArticleEntity> query(Map<String, String> queryParams, int page, int pageSize) {
    Pageable pageable = new PageRequest(page, size, sort);
    return articleDAO.findAll(Specifications.where(getWhereClause(queryParams)), pageable);
  }
}
