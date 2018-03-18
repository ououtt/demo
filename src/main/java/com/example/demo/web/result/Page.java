package com.example.demo.web.result;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:07
 **/
public class Page <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认单面记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 15;

    private static final List EMPTY_LIST = new ArrayList(0);

    private static final String EXCEPTION_MSG = "创建分页对象失败,pageNum:%d,records:%d,pageSize:%d,size:%d";

    /**
     * 首选页面记录数
     */
    private int pageSize;

    /**
     * 结果列表
     */
    private List<T> result;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 所有记录数
     */
    private long records;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 从pageHelper的分页对象对象构造
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> Page<T> buildFromPageInfo(PageInfo<T> pageInfo) {
        return new Page<>(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getList());
    }

    /**
     * @param pageNum  当前页
     * @param records  总数据数
     * @param pageSize 每页大小
     * @param result   当前页数据
     */
    public Page(int pageNum, long records, int pageSize, List<T> result) {
        if (result == null) {
            result = EMPTY_LIST;
        }
        int pages = validAndGetPages(pageNum, records, pageSize, result);
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.pages = pages;
        this.records = records;
        this.result = result;
    }

    /**
     * 使用默认15的分页大小
     *
     * @param pageNum 当前页
     * @param records 总数据数
     * @param result  当前页数据
     */
    public Page(int pageNum, long records, List<T> result) {
        this(pageNum, records, DEFAULT_PAGE_SIZE, result);
    }

    /**
     * 校验参数并返回总页数
     *
     * @param pageNum  当前页
     * @param records  总数据数
     * @param pageSize 每页大小
     * @param result   当前页数据
     * @return
     */
    private int validAndGetPages(int pageNum, long records, int pageSize, List<T> result) {

        /**
         * 当前页必须大于0
         * 总记录数可以等于0
         * 每页大小必须大于0
         */
        if (pageNum <= 0 || records < 0 || pageSize < 0) {
            throw new RuntimeException(String.format(EXCEPTION_MSG, pageNum, records, pageSize, result.size()));
        }

        /**
         * 数据量大小必须小于每页大小
         */
        if (pageSize < result.size()) {
            throw new RuntimeException(String.format(EXCEPTION_MSG, pageNum, records, pageSize, result.size()));
        }

        // 计算总页数
        int pages = Math.toIntExact((records / pageSize + (records % pageSize > 0 ? 1 : 0)));

        /**
         * 总页数必须大于等于当前页码
         */
        if (pages < pageNum) {
            throw new RuntimeException(String.format(EXCEPTION_MSG, pageNum, records, pageSize, result.size()));
        }

        // 返回总页数
        return pages;
    }

    /**
     * @return 是否有上一页
     */
    public boolean getPreviousPage() {
        return this.pageNum - 1 > 0;
    }

    /**
     * @return 是否有下一页
     */
    public boolean getNextPage() {
        return this.pageNum + 1 <= this.pages;
    }

    /**
     * @return 当前页的第一个数据的位置
     */
    public int getPageFirstRecNum() {
        return (pageNum - 1) * pageSize + 1;
    }

    /**
     * @return 当前页的最后一个数据的位置
     */
    public int getPageLastRecNum() {
        return pageNum * pageSize;
    }

    public List<T> getResult() {
        return result;
    }

    public int getPageNum() {
        return pageNum;
    }

    public long getRecords() {
        return records;
    }

    public int getPages() {
        return pages;
    }

    public int getPageSize() {
        return pageSize;
    }
}