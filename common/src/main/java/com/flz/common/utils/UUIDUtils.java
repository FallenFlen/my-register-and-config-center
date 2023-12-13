package com.flz.common.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UUIDUtils {

    public static String uuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
