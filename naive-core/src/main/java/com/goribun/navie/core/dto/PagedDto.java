package com.goribun.navie.core.dto;

import java.util.Collection;

/**
 * 分页列表Dto
 *
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class PagedDto<T> extends BaseDto {

    //总记录
    private int rowCount;
    //总页数
    private int pageCount;
    //当前页
    private int pageIndex;
    //数据列表
    private Collection<T> list;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }
}
