package com.ppc.blog.article;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Page;

import com.ppc.blog.common.Response;
import com.ppc.blog.article.ArticleEntity;
import com.ppc.blog.article.ArticleDAO;
import com.ppc.blog.article.ArticleService;
import com.ppc.blog.article.ArticleValidator;
import com.ppc.blog.account.AccountEntity;

@RestController
public class ArticleRest {
  @Autowired
  private ArticleDAO articleDAO;

  @Autowired
  private ArticleService articleService;

  @RequestMapping(value = "/articles", method = RequestMethod.POST) 
  public Response createArticle(
    @RequestBody
    @Validated(value = ArticleValidator.CreateGroup.class)
    ArticleValidator article,
    HttpServletRequest request) {
    AccountEntity accountEntity = (AccountEntity) request.getSession().getAttribute("currentUser");
    if (accountEntity == null) {
      return new Response("COMM_ERROR_UNLOGIN", "you have not login");
    }

    ArticleEntity articleEntity = new ArticleEntity();
    articleEntity.setAuthor(accountEntity);
    articleEntity.setTitle(article.getTitle());
    articleEntity.setContent(article.getContent());
    articleEntity.setCreatedTime(new Date());
    articleDAO.save(articleEntity);
    return new Response(articleEntity);  
  }

  @RequestMapping(value = "/articles/{id}", method = RequestMethod.PUT)
  public Response updateArticle(
    @PathVariable("id") String id,
    @RequestBody
    @Validated(value = ArticleValidator.UpdateGroup.class)
    ArticleValidator article,
    HttpServletRequest request) {
    AccountEntity accountEntity = (AccountEntity) request.getSession().getAttribute("currentUser");
    if (accountEntity == null) {
      return new Response("COMM_ERROR_UNLOGIN", "you have not login");    
    }

    ArticleEntity articleEntity = articleDAO.findOneById(id);
    if (articleEntity == null) {
      return new Response("COMM_ERROR_NOTFOUND", "not found article");
    } else if (!accountEntity.getId().equals(articleEntity.getAuthor().getId())) {
      return new Response("COMM_ERROR_NOPERMISSION", "you have no permission");
    }

    articleEntity.setTitle(article.getTitle());
    articleEntity.setContent(article.getContent());
    articleEntity.setUpdatedTime(new Date());
    articleDAO.save(articleEntity);
    return new Response(articleEntity);  
  }

  @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
  public Response deleteArticle(@PathVariable("id") String id, HttpServletRequest request) {
    AccountEntity accountEntity = (AccountEntity) request.getSession().getAttribute("currentUser"); 
    if (accountEntity == null) {
      return new Response("COMM_ERROR_UNLOGIN", "you have not login");
    }

    ArticleEntity articleEntity = articleDAO.findOneById(id);
    if (articleEntity == null) {
      return new Response("COMM_ERROR_NOTFOUND", "not found article");
    } else if (!accountEntity.getId().equals(articleEntity.getAuthor().getId())) {
      return new Response("COMM_ERROR_NOPERMISSION", "you have no permission"); 
    }
    articleDAO.delete(articleEntity);
    return new Response(true);  
  }

  @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
  public Response getArticle(@PathVariable("id") String id) {
    ArticleEntity articleEntity = articleDAO.findOneById(id);
    if (articleEntity == null) {
      return new Response("COMM_ERROR_NOTFOUND", "not found article");
    }
    return new Response(articleEntity);  
  }

  @RequestMapping(value = "/articles/queryAll", method = RequestMethod.POST)
  public Response queryAllArticles(
    @RequestBody
    @Validated(value = ArticleValidator.QueryAllGroup.class)
    ArticleValidator article) {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("title", article.getTitle());
    queryParams.put("content", article.getContent());
    queryParams.put("authorId", article.getAuthorId());
    List<ArticleEntity> articleList = articleService.query(queryParams);
    return new Response(articleList);
  }

  @RequestMapping(value = "/articles/queryPage", method = RequestMethod.POST)
  public Response queryPageArticles(
    @RequestBody
    @Validated(value = ArticleValidator.QueryPageGroup.class)
    ArticleValidator article) {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("title", article.getTitle());
    queryParams.put("content", article.getContent());
    queryParams.put("authorId", article.getAuthorId());
    Page<ArticleEntity> articlePage = articleService.query(queryParams, article.getPage(), article.getPageSize());
    return new Response(articlePage);  
  }
}
