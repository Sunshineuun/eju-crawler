package com.qiusm.eju.crawler.utils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * 迭代工具类
 */
public class IterableUtil {

    /**
     * 带索引的的迭代逻辑
     *
     * @param elements 需要遍历的元素
     * @param action   操作方法
     * @param <E>      范型
     */
    public static <E> void forEach(
            Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);

        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }
}
