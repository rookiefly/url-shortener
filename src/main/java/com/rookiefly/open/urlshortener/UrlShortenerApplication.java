package com.rookiefly.open.urlshortener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SpringBootApplication
@EnableCaching
@Slf4j
public class UrlShortenerApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(UrlShortenerApplication.class, args);
        Environment env = application.getEnvironment();
        String contextPath = env.getProperty("server.servlet.context-path");
        contextPath = Optional.ofNullable(contextPath).orElse("").replaceFirst("/", "");
        contextPath = (contextPath.length() <= 0 || contextPath.endsWith("/")) ? contextPath : contextPath + "/";
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String serverPort = env.getProperty("server.port");
        String urlCtx = hostAddress + ":" + serverPort + "/" + contextPath;
        log.info("\n----------------------------------------------------------\n\t" +
                        "\t\t地址列表\n\t" +
                        "管理地址：http://{}\n" +
                        "----------------------------------------------------------",
                urlCtx
        );
    }

/*    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST", "PUT", "DELETE");
            }
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }*/
}
