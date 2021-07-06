package com.qiusm.eju.crawler.utils;

import redis.clients.util.JedisClusterCRC16;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;

import static com.qiusm.eju.crawler.constant.SymbolicConstant.UNDERSCORE;


public class CycleAtomicInteger {

    private final static long PARK_TIME = 1000L * 1000;
    private static final int INT_RANGE = 2;
    private final int range;
    private final String[] names;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final static Map<Integer, Function<Integer, Boolean>> SOLT_NUM = new HashMap<>();

    static {
        SOLT_NUM.put(0, s -> s >= 0 && s < 5461 ? Boolean.TRUE : Boolean.FALSE);
        SOLT_NUM.put(1, s -> s >= 5461 && s < 10922 ? Boolean.TRUE : Boolean.FALSE);
        SOLT_NUM.put(2, s -> s >= 10922 && s < 16384 ? Boolean.TRUE : Boolean.FALSE);
    }

    public CycleAtomicInteger(int range, String[] names) {
        this.range = range;
        this.names = names;
    }

    public CycleAtomicInteger(int range, String name) {
        if (range < INT_RANGE) {
            throw new IllegalArgumentException();
        }
        this.range = range;
        this.names = new String[range];
        /*
        0 ~ 5461
        5461 ~ 10922
        10922 ~ 16384
        */
        int soltSize = SOLT_NUM.size();
        for (int i = 0, x = 0; i < range; i++) {
            String k;
            while (true) {
                k = name + UNDERSCORE + x++;
                int slot = JedisClusterCRC16.getSlot(k);
                if (SOLT_NUM.get(i % soltSize).apply(slot)) {
                    break;
                }
            }
            this.names[i] = k;
        }
    }


    /**
     * 获取下个原子值
     */
    public final String next() {
        for (; ; ) {
            int c = counter.get();
            int next = (c + 1) % range;
            if (counter.compareAndSet(c, next)) {
                return this.names[c];
            } else {
                LockSupport.parkNanos(PARK_TIME);
            }
        }
    }

    public final String[] getNames() {
        return names;
    }

    public static void main(String[] args) throws Exception {
        CycleAtomicInteger cycleAtomicInteger = new CycleAtomicInteger(6, "house:crawler:taskpool:1545");

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                String name = cycleAtomicInteger.next();
                System.out.println(Thread.currentThread().getName() + "_" + name);
                LockSupport.parkNanos(1000L * 1000);
            }
        }).start();

    }
}
