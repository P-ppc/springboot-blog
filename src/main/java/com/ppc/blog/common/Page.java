package com.ppc.blog.common;

public class Page<T> {
  private List<T> content;
  private int page;
  private int pageSize;
  private int totalCount;
  private int totalPage;
  private boolean hasPrev;
  private boolean hasNext;
  // extra infomation
  private Object extra;

  public void setContent(List<T> content) {
    this.content = content;
  }
  
  public List<T> getContent() {
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

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setHasPrev(boolean hasPrev) {
    this.hasPrev = hasPrev;
  }

  public boolean getHasPrev() {
    return hasPrev;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public boolean getHasNext() {
    return hasNext;
  }

  public void setExtra(Object extra) {
    this.extra = extra;
  }

  public Object getExtra() {
    return extra;
  }
}
