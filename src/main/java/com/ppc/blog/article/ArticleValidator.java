package com.ppc.blog.article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;

public class ArticleValidator {
  @NotBlank(message = "title must not be empty", groups = { CreateGroup.class, UpdateGroup.class })
  @Size(min = 5, max = 20, message = "title length must between 5 ~ 20", groups = { CreateGroup.class, UpdateGroup.class })
  private String title;

  @NotBlank(message = "content must not be empty", groups = { CreateGroup.class, UpdateGroup.class })
  @Size(max = 1024, message = "content length must less than or equal to 1024", groups = { CreateGroup.class, UpdateGroup.class })
  private String content; 
  
  @NotNull(message = "page must not be null", groups = { QueryPageGroup.class })
  @Min(value = 1, message = "page must more than or equal to 1", groups = { QueryPageGroup.class })
  private int page;
  
  @NotNull(message = "pageSize must not be null", groups = { QueryPageGroup.class })
  @Min(value = 1, message = "pageSize must more than or equal to 1", groups = { QueryPageGroup.class })
  @Max(value = 100, message = "pageSize must less than or equal to 100", groups = { QueryPageGroup.class }) 
  private int pageSize;

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

  public void setPage(int page) {
    this.page = page;
  }

  public int getPage() {
    return page;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageSize() {
    return pageSize;
  }

  public interface CreateGroup {}
  public interface UpdateGroup {}
  public interface QueryAllGroup {}
  public interface QueryPageGroup {}
}
