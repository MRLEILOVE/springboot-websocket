package com.liegq.www.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * MVC配置，在这里可以加入定义的拦截器
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-05 11:17 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
/*
* @SpringBootConfiguration继承自@Configuration，二者功能也一致，标注当前类是配置类，
并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到spring容器中，并且实例名就是方法名。
* */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    /**
     * 添加资源处理程序
     * <br>创建人： leigq
     * <br>创建时间： 2018-11-07 11:06
     * <br>
     *
     * @param registry 资源处理程序注册表
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
                // 这是请求url的匹配模式，匹配url根路径下的所有路径（包括子路径，如果只有一个*，那就不包括子路径）
        registry.addResourceHandler("/static/**")
                // 这是文件路径的匹配模式，值上面匹配的路径在这个文件夹下面找文件
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/img/"); // 上面两个是默认的，也可以加上这种自定义的
        super.addResourceHandlers(registry);
    }

    /**
     * 解决跨域问题
     * <br/>
     * 参考:<a href='https://www.cnblogs.com/520playboy/p/7306008.html'>springboot中通过cors协议解决跨域问题</a>
     * <br>创建人： leigq
     * <br>创建时间： 2018-11-07 11:07
     * <br>
     *
     * @param registry 跨域注册器
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//"/**"代表所有路径
                .allowedOrigins("*")
                .allowedMethods("CONNECT", "HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE", "TRACE")
                .allowCredentials(true)
                .allowedHeaders("Origin", "No-Cache", "X-Requested-With", "If-Modified-Since", "Pragma",
                        "Last-Modified", "Cache-Control", "Expires", "Content-Type", "X-E4M-With", "Accept")
                .maxAge(3600);
        super.addCorsMappings(registry);
    }


    //    还有以下 WebMvcConfigurerSupport 比较常用的重写接口：
    //    /** 解决跨域问题 **/
    //    public void addCorsMappings(CorsRegistry registry) ;
    //    /** 这里配置视图解析器 **/
    //    void configureViewResolvers(ViewResolverRegistry registry);
    //    /** 配置内容裁决的一些选项 **/
    //    void configureContentNegotiation(ContentNegotiationConfigurer configurer);
    //    /** 视图跳转控制器 **/
    //    void addViewControllers(ViewControllerRegistry registry);
    //    /** 默认静态资源处理器 **/
    //    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

}
