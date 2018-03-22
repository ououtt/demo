package com.example.demo.repository.orm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 手机
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别:1男，2女
     */
    private Integer sex;

    /**
     * 身份证号码
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 图片路径
     */
    private String images;

    @Column(name = "city_code")
    private Integer cityCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 0:删除 1:可用
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_update")
    private LocalDateTime gmtUpdate;

    /**
     * 是否验证
     */
    private Integer valid;

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
     * @return username - 用户名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取手机
     *
     * @return mobile_phone - 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机
     *
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别:1男，2女
     *
     * @return sex - 性别:1男，2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别:1男，2女
     *
     * @param sex 性别:1男，2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取身份证号码
     *
     * @return id_card - 身份证号码
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号码
     *
     * @param idCard 身份证号码
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取图片路径
     *
     * @return images - 图片路径
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置图片路径
     *
     * @param images 图片路径
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * @return city_code
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
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
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_update - 修改时间
     */
    public LocalDateTime getGmtUpdate() {
        return gmtUpdate;
    }

    /**
     * 设置修改时间
     *
     * @param gmtUpdate 修改时间
     */
    public void setGmtUpdate(LocalDateTime gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    /**
     * 获取是否验证
     *
     * @return valid - 是否验证
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置是否验证
     *
     * @param valid 是否验证
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }
}