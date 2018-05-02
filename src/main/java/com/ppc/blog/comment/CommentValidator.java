package com.ppc.blog.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentValidator {
  @NotBlank(message = "article id must not be empty", groups = { CreateGroup.class })
  private String articleId;

  @NotBlank(message = "comment must not be empty", groups = { CreateGroup.class })
  @Size(max = 128, message = "content length must less than or equal to 128", groups = { CreateGroup.class })
  private String content;
  
  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public interface CreateGroup {}
}
