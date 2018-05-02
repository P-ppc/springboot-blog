package com.ppc.blog.comment;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.ppc.blog.account.AccountEntity;
import com.ppc.blog.article.ArticleEntity;

@Entity
@Table(name = "t_comment")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class CommentEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "article_id", nullable = false, updatable = false)
  private ArticleEntity article;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false, updatable = false) 
  private AccountEntity author;

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(length = 36)
  private String id;

  @Column(nullable = false, length = 128)
  private String content;

  @Column(nullable = false, updatable = false)
  private Date createdTime;

  private Date updatedTime;

  public void setArticle(ArticleEntity article) {
    this.article = article;
  }

  public ArticleEntity getArticle() {
    return article;
  }

  public void setAuthor(AccountEntity author) {
    this.author = author;
  }

  public AccountEntity getAuthor() {
    return author;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  public Date getUpdatedTime() {
    return updatedTime;
  }
}
