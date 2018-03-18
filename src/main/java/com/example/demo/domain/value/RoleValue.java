package com.example.demo.domain.value;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 19:14
 **/
@Data
public class RoleValue implements GrantedAuthority {

    private Integer id;

    private String roleName;

    private Integer state;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
