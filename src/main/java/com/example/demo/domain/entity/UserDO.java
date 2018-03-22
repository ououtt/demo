package com.example.demo.domain.entity;

import com.example.demo.domain.value.RoleValue;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 18:50
 **/
@Data
public class UserDO implements UserDetails {

    private static final long serialVersionUID = 3627092793498700715L;

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

    private LocalDateTime create;

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
