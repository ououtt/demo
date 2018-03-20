package com.example.demo.repository.orm;

import java.util.Date;
import javax.persistence.*;

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 0:删除 1:可用
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_update")
    private Date gmtUpdate;

    /**
     * 内容
     */
    private String text;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取0:删除 1:可用
     *
     * @return state - 0:删除 1:可用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0:删除 1:可用
     *
     * @param state 0:删除 1:可用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_update - 修改时间
     */
    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    /**
     * 设置修改时间
     *
     * @param gmtUpdate 修改时间
     */
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    /**
     * 获取内容
     *
     * @return text - 内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置内容
     *
     * @param text 内容
     */
    public void setText(String text) {
        this.text = text;
    }
}