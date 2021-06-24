package com.qiusm.eju.crawler.poi.gaode.entity;

import java.math.BigDecimal;

/**
 * @author qiushengming
 */
public class Point2D {
    public BigDecimal x;
    public BigDecimal y;

    public Point2D(String x, String y) {
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
    }
}
