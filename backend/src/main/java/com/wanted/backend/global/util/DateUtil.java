package com.wanted.backend.global.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {
    public static LocalDateTime now() {
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        return LocalDateTime.now(zoneId);
    }
}
