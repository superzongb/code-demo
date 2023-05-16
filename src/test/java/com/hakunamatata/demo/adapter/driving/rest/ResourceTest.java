package com.hakunamatata.demo.adapter.driving.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@ExtendWith(SpringExtension.class)
// 由于完整启动了 Servlet 进行从 HTTP 请求开始的集成测试，所以需要在测试时随机分配端口以便于测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ResourceTest {

    // 注入分配的随机端口供测试代码使用
    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        // 配置 RestAssured 使用随机端口
        RestAssured.port = port;
    }
}
