package com.example.demo.repository.orm;

import javax.persistence.*;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 0:删除 1:可用
     */
    private Integer state;

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
     * 获取用户名称
     *
     * @return role_name - 用户名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置用户名称
     *
     * @param roleName 用户名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
}