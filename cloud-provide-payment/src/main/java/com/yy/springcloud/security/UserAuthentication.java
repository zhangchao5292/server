package com.yy.springcloud.security;

import com.yy.springcloud.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  UserAuthentication
 *
 * @author tianyi.wei
 */
public class UserAuthentication implements Authentication {
    private final SysUser sysUser;
    private boolean authenticated = true;
    private List<GrantedAuthority> authorityList = new ArrayList<>();

    public UserAuthentication(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public Object getCredentials() {
        return sysUser.getUserId();
    }

    @Override
    public Object getDetails() {
        return sysUser;
    }

    @Override
    public Object getPrincipal() {
        // 使得标注 @AuthenticationPrincipal (@LumiUser) 可以使用
        return sysUser;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return "";
    }
}
