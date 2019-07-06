//	
//package com.td.config;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.td.filter.MyFormAuthenticationFilter;
//import com.td.shiro.MyShiroRealm;
//import com.td.shiro.RetryLimitHashedCredentialsMatcher;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * shiro配置类
// */
//@Configuration
//public class ShiroConfig {
//
//    //创建ShiroFilterFactoryBean
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        //添加shiro内置过滤器
//        /*
//         * anon:表示可以匿名使用。
//           authc:表示需要认证(登录)才能使用，没有参数
//           roles：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
//           perms：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
//           rest：根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
//           port：当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
//           authcBasic：没有参数表示httpBasic认证
//           ssl:表示安全的url请求，协议为https
//           user:当登入操作时不做检查
//         */
//        // 拦截器.
//        Map<String, String> fMap = new LinkedHashMap<String, String>();
//        // 配置不会被拦截的链接 顺序判断
//        fMap.put("/statics/**", "anon");
//        fMap.put("/test/**", "anon");
//        fMap.put("/deviceInfo/**", "anon");
//        fMap.put("/device/**", "anon");
//        fMap.put("/wxChatMsg", "anon");
//        fMap.put("/testPay", "anon");
//        fMap.put("/pay/**", "anon");
//        fMap.put("/wechat/**", "anon");
//        fMap.put("wechat/**", "anon");
//        fMap.put("/order/**", "anon");
//        fMap.put("/validatecodeServlet", "anon");
//        fMap.put("/unlockAccount", "anon");
//        fMap.put("/wechatAuth", "anon");
//        fMap.put("/group**/**", "anon");
//        fMap.put("/MP_verify_4mQszAFnjKt6BYIv.txt", "anon");
//        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        fMap.put("/logout", "logout");
//        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        fMap.put("/**", "authc");
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面,设置的话没经过验证会发送test请求到控制器，由控制器决定转到对应视图
//
//        //解决登录成功后无法跳转到successUrl的问题
//        Map<String, Filter> map = new LinkedHashMap<>();
//        map.put("authc", new MyFormAuthenticationFilter());
//        shiroFilterFactoryBean.setFilters(map);
//
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接
////        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        // 未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/permission");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(fMap);
//        return shiroFilterFactoryBean;
//
//    }
//
//
//    /**
//     * 加密凭证匹配器以及次数匹配器
//     */
//    @Bean("credentialsMatcher")
//    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(){
//        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
//        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
//        retryLimitHashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于md5(md5(""));.
//        return retryLimitHashedCredentialsMatcher;
//    }
//
//    /**
//     * 缓存管理器
//     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return cacheManager;
//    }
//
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
////        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置匹配器
//        myShiroRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
//
////        myShiroRealm.setCachingEnabled(true);
////        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
////        myShiroRealm.setAuthenticationCachingEnabled(true);
////        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
////        myShiroRealm.setAuthenticationCacheName("authenticationCache");
////        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
////        myShiroRealm.setAuthorizationCachingEnabled(true);
////        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
////        myShiroRealm.setAuthorizationCacheName("authorizationCache");
//
//        return myShiroRealm;
//    }
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
////        securityManager.setCacheManager(ehCacheManager()); //设置缓存
//        return securityManager;
//    }
//
//    //---------------------------------------------------------------------
//    //权限验证
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//    //---------------------------------------------------------------------
//    //thy - shiro
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
//
//    //---------------------------------------------------------------------
//    /**
//     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
//     */
//    @Bean public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){ return new LifecycleBeanPostProcessor(); }
//
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    //---------------------------------------------------------------------
//    //异常处理
//    @Bean
//    public HandlerExceptionResolver solver(){
//        HandlerExceptionResolver handlerExceptionResolver=new MyExceptionResolver();
//        return handlerExceptionResolver;
//    }
//
//
//}