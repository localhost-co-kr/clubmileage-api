package com.triple.clubmileage.api.configuration;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * h2 console이 Spring Web MVC Servlet 기반에서만 동작하므로
 * h2 console 서버를 수동으로 부팅해야하는 이슈가 있음
 * <p>
 * https://stackoverflow.com/questions/52949088/h2-db-not-accessible-at-localhost8080-h2-console-when-using-webflux
 */
@Slf4j
@Component
@Profile("local")
public class H2Console {

    private Server webServer;
    private Server tcpServer;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
        this.tcpServer = org.h2.tools.Server.createTcpServer("-webPort", "9092", "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
        this.tcpServer.stop();
    }
}
