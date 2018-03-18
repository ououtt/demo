package com.example.demo.domain.entity;

import com.example.demo.constant.enums.SexEnum;
import com.example.demo.domain.value.RoleValue;
import com.example.demo.repository.orm.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 18:50
 **/
@Data
public class UserDO implements UserDetails {

    private Integer id;

    private String username;

    private String mobilePhone;

    private String email;

    private String password;

    private Integer sex;

    private String idCard;

    private String images;

    private Integer cityCode;

    private String address;

    private Integer state;

    private Date create;

    private Integer valid;

    private List<RoleValue> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
