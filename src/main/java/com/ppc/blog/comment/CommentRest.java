package com.ppc.blog.comment;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;

import com.ppc.blog.common.Response;
import com.ppc.blog.account.AccountEntity;
import com.ppc.blog.article.ArticleEntity;
import com.ppc.blog.article.ArticleDAO;
import com.ppc.blog.comment.CommentEntity;
import com.ppc.blog.comment.CommentDAO;
import com.ppc.blog.comment.CommentValidator;

@RestController
public class CommentRest {
  @Autowired
  private ArticleDAO articleDAO; 

  @Autowired
  private CommentDAO commentDAO;

  @RequestMapping(value = "/comments", method = RequestMethod.POST)
  public Response createComment(
    @RequestBody
    @Validated(value = CommentValidator.CreateGroup.class)
    CommentValidator comment,
    HttpServletRequest request) {
    AccountEntity accountEntity = (AccountEntity) request.getSession().getAttribute("currentUser");
    if (accountEntity == null) {
      return new Response("COMM_ERROR_UNLOGIN", "you have not login");
    }

    ArticleEntity articleEntity = articleDAO.findOneById(comment.getArticleId());
    if (articleEntity == null) {
      return new Response("COMM_ERROR_NOTFOUND", "not found article");
    }

    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setAuthor(accountEntity);
    commentEntity.setArticle(articleEntity);
    commentEntity.setContent(comment.getContent());
    commentEntity.setCreatedTime(new Date());
    commentDAO.save(commentEntity);
    return new Response(commentEntity);
  }

  @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
  public Response getComment(@PathVariable("id") String id) {
    CommentEntity commentEntity = commentDAO.findOneById(id);
    if (commentEntity == null) {
      return new Response("COMM_ERROR_NOTFOUND", "not found comment");
    }
    return new Response(commentEntity);
  }

  @RequestMapping(value = "/articles/{id}/comments", method = RequestMethod.GET)
  public Response getCommentsByArticle(@PathVariable("id") String id) {
    return new Response(commentDAO.findByArticleIdOrderByCreatedTimeDesc(id));
  }
}
