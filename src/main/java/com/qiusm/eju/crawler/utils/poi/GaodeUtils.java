package com.qiusm.eju.crawler.utils.poi;

import org.apache.commons.lang3.StringUtils;

import java.awt.geom.Point2D;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author qiushengming
 */
public class GaodeUtils {

    public final static String PRE_FIX = "http://mapdata-api.ejudata.com/inner/map/get?url=";

    /**
     * 判断点是否在多边形内。
     *
     * @param point 测试点
     * @param pts   多边形的点
     */
    public static boolean isInPolygon(Point2D.Double point, List<Point2D.Double> pts) {

        int N = pts.size();
        boolean boundOrVertex = true;
        //交叉点数量
        int intersectCount = 0;
        //浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        //临近顶点
        Point2D.Double p1, p2;
        //当前点
        Point2D.Double p = point;

        p1 = pts.get(0);
        for (int i = 1; i <= N; ++i) {
            if (p.equals(p1)) {
                return boundOrVertex;
            }

            p2 = pts.get(i % N);
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;
            }

            // 射线穿过算法
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                if (p.y <= Math.max(p1.y, p2.y)) {
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
                        return boundOrVertex;
                    }

                    if (p1.y == p2.y) {
                        if (p1.y == p.y) {
                            return boundOrVertex;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        if (Math.abs(p.y - xinters) < precision) {
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (p.x == p2.x && p.y <= p2.y) {
                    Point2D.Double p3 = pts.get((i + 1) % N);
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        if (intersectCount % 2 == 0) {
            //偶数在多边形外
            boundOrVertex = false;
        } else {
            //奇数在多边形内
            boundOrVertex = true;
        }

        return boundOrVertex;
    }

    /**
     * 高德接口访问需要key，该方法主要对其进行封装。
     * 样例结果：http://mapdata-api.ejudata.com/inner/map/get?url=http%3A%2F%2Frestapi.amap.com%2Fv3%2Fplace%2Faround%3Flocation%3D121.520873%2C31.477357%26keywords%3D%26types%3D010000%26radius%3D1500%26offset%3D50%26extensions%3Dall%26page%3D1%26limitStrategy%3D1
     *
     * @param url url
     * @return url
     */
    public static String packageUrl(String url) {
        try {
            if (StringUtils.isNotBlank(url)) {
                url = URLEncoder.encode(url + "&limitStrategy=1");
                url = PRE_FIX + url;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
