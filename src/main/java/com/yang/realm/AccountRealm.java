package com.yang.realm;

import com.yang.entity.Account;
import com.yang.mapper.AccountMapper;
import com.yang.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AccountRealm extends AuthorizingRealm {


    @Autowired
    private AccountService accountService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //在这里对用户的身份信息进行角色和权限的验证
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipals();


        //设置角色
        Set<String> set = new HashSet<>();
        set.add(account.getRole());
        //引入
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(set);


        //设置权限
        //将用户的权限也添加进去，该方法是重写的，也可以传入一个集合，代表一个用户用于多个权限
        info.addStringPermission(account.getPerms());

        return info;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //shiro框架进行认证的时候，首先是根据用户名进行处理的，根据用户名查询到了账户之后采取校验密码
        UsernamePasswordToken usernamePasswordToken = ((UsernamePasswordToken) authenticationToken);

        Account account = accountService.findByName(usernamePasswordToken.getUsername());

        if (account != null) {
            //查询到了account
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }


        return null;
    }
}
