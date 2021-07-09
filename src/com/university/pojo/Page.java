package com.university.pojo;

import java.util.List;

/**
 * @author shk
 * @create 2021--05--07--18:44
 */
public class Page<T> {
    public static final Integer PAGE_SIZE=4;
    //当前页码
    private Integer pageNo;
    //总页数
    private Integer pageTotal;
    //每记录数
    private Integer pageSize=PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //当前页数据
    private List<T> items;
    //分页条地址
    private String url;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
       pageNo=pageNo>pageTotal?pageTotal:pageNo;
       pageNo=pageNo<1?1:pageNo;
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
