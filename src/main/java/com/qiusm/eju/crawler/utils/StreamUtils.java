package com.qiusm.eju.crawler.utils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtils {

    /**
     * Description: stream去重时，调用它可免除 泛型重写equals和hashcode方法
     * <p>
     * 调用示例：
     * reportPoolList是查询出来的List<ReportPool>集合，通过过滤，对postCode属性（岗位代码）进行去重，返回
     * List<ReportPool> distinctElements = reportPoolList.stream()
     * .filter(StreamUtil.distinctByKey(p -> p.getPostcode()))
     * .collect(Collectors.toList());
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
