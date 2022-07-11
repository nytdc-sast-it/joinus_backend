package org.tdsast.joinus.config.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    // 将自己的验证方式加入容器
    @Bean
    CustomRealm myRealm() {
        return new CustomRealm();
    }

    @Bean
    ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        Map<String, String> map = new HashMap<>();
        map.put("/auth/**", "authc");
        map.put("/auth/login", "anon");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    DefaultWebSecurityManager manager(@Qualifier("myRealm") CustomRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }
}
