package com.hzt.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BasicConfig {
    /*
    前后端两个ip地址跨域访问
    */
    @Bean
    public CorsFilter getCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //允许什么域名
        config.addAllowedOrigin("*");//*表示什么都允许
        //允许什么访问方式get post等
        config.addAllowedMethod("*");
        //允许什么样的请求头
        config.addAllowedHeader("*");//开发可以用*，上线必须改

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    /**
     * 密码加密
     */
    @Bean
    BCryptPasswordEncoder getpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
