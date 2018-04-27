package com.ppc.blog.article;

import java.unit.Date;
import java.io.Serializable;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.ppc.blog.account.AccountEntity;

@Entity
@Table(name = "t_article")
@GenericGenerator(name = "uuid2",  strategy = "org.hibernate.id.UUIDGenerator")
public class ArticleEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false, updatable = false)
  private AccountEntity author;
  
  @Id
  @GenericGenerator(generator = "uuid2")
  @Column(length = 36)
  private String id;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(nullable = false, length = 1024)
  private String content;

  @Column(nullable = false, updatable = false)
  private Date createdTime;

  private Date updatedTime;

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

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
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
