package com.hakunamatata.demo.common.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @authoer:ZongBin
 * @createDate:2023/1/16
 * @description:
 */
@Component
public class IdGenerator {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
