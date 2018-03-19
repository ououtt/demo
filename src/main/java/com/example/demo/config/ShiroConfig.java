package com.example.demo.config;

import com.example.demo.shiro.StatelessDefaultSubjectFactory;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-18 23:12
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSubjectFactory subjectFactory() {

        StatelessDefaultSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();

        return subjectFactory;
    }


    /**
     * Add.2.4
     * <p>
     * session管理器：
     * <p>
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * <p>
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     *
     * @return
     */
    @Bean

    public DefaultSessionManager sessionManager() {

        DefaultSessionManager sessionManager = new DefaultSessionManager();

        sessionManager.setSessionValidationSchedulerEnabled(false);

        return sessionManager;

    }

    /**
     * shiro安全管理器:
     * <p>
     * 主要是身份认证的管理，缓存管理，cookie管理，
     * <p>
     * 所以在实际开发中我们主要是和SecurityManager进行打交道的
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        /*

         * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions

         * 所以需要配合context.setSessionCreationEnabled(false);

         */


        ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        return securityManager;

    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager securityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        factoryBean.setSecurityManager(securityManager);

        return factoryBean;

    }

}
