package com.github.dhslrl321.coupon.util;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TimeBasedUniqueIdGeneratorTest {

    public static final String ANY = "any";

    @Test
    @Disabled("유일 id 생성기에 대한 효용 테스트로 regressive 하지 않아서 disabled")
    void 유일_id_생성기_충돌_테스트() {
        Map<Long, String> validator = new HashMap<>();

        // 10 만번 충돌 테스트
        for (int i = 0; i < 100_000; i++) {
            Long generated = TimeBasedUniqueIdGenerator.getInstance().gen();
            validator.put(generated, ANY);
        }
    }
}