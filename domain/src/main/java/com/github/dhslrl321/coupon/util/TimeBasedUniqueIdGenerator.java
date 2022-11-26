package com.github.dhslrl321.coupon.util;

import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TimeBasedUniqueIdGenerator {

    private TimeBasedUniqueIdGenerator() {}

    public static TimeBasedUniqueIdGenerator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        final static TimeBasedUniqueIdGenerator INSTANCE = new TimeBasedUniqueIdGenerator();
    }

    public Long gen() {
        StringBuilder builder = new StringBuilder();
        appendNowEpochTime(builder);
        appendRandom8Length(builder);
        return Long.parseLong(builder.toString());
    }

    private void appendNowEpochTime(StringBuilder builder) {
        builder.append(ZonedDateTime.now().toEpochSecond());
    }

    private void appendRandom8Length(StringBuilder builder) {
        IntStream.range(0, 8).forEach(i -> builder.append(getRandomNumber()));
    }

    private int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 9);
    }
}
