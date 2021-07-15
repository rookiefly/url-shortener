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
                        "首页地址：http://{}\n" +
                        "Swagger地址：http://{}doc.html\n" +
                        "----------------------------------------------------------",
                urlCtx, urlCtx);
    }
}
