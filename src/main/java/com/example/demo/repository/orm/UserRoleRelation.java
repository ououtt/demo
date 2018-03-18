package com.example.demo.repository.orm;

import javax.persistence.*;

@Table(name = "user_role_relation")
public class UserRoleRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 手机
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * @return user_id - 用户名称
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户名称
     *
     * @param userId 用户名称
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取手机
     *
     * @return role_id - 手机
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置手机
     *
     * @param roleId 手机
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}