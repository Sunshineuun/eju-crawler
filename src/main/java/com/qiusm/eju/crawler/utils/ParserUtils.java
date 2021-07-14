package com.qiusm.eju.crawler.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qiusm.eju.crawler.base.entity.Parser;
import com.qiusm.eju.crawler.base.entity.TaskInstance;
import com.qiusm.eju.crawler.base.parser.ParserInterface;
import com.xiaoleilu.hutool.bean.BeanUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author qiushengming
 */
@Data
@Slf4j
public class ParserUtils {

    public static Map<String, Object> convert(String config) {
        return JSONObject.parseObject(config, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 判断解析器没有子集 并且是最后一个
     *
     * @param id   当前id
     * @param list 解析器列表
     */
    public static Boolean isLastParser(long id, List<Parser> list) {
        List<Parser> groupList = getGroupList(id, list);
        if (groupList == null || groupList.isEmpty()) {
            return null;
        }
        return groupList.stream()
                .allMatch(e -> e.getId().compareTo(id) == 0
                        && CollectionUtils.isEmpty(e.getChildList())
                );
    }

    /**
     * 查询某个解析器所在的某一层的list ，用于判断某个解析器是否是最后一个
     */
    public static List<Parser> getGroupList(long id, List<Parser> list) {
        for (Parser p : list) {
            if (p.getId().compareTo(id) == 0) {
                return list;
            }
        }
        for (Parser p : list) {
            if (!CollectionUtils.isEmpty(p.getChildList())) {
                List<Parser> l = getGroupList(id, p.getChildList());
                if (l != null) {
                    return getGroupList(id, l);
                }
            }
        }
        return null;
    }

    /**
     * 查询某个解析器所在的某一层的list ，用于判断某个解析器是否是最后一个
     */
    private static List<Parser> getGroupList(String code, List<Parser> list) {
        for (Parser p : list) {
            if (p.getCode().equals(code)) {
                return list;
            }
        }
        for (Parser p : list) {
            if (!CollectionUtils.isEmpty(p.getChildList())) {
                List<Parser> l = getGroupList(code, p.getChildList());
                if (l != null) {
                    return getGroupList(code, l);
                }
            }
        }
        return null;
    }

    /**
     * 获取某个解析器
     */
    public static Parser getParser(String code, Long id, List<Parser> list) {
        if (id != null) {
            for (Parser p : Objects.requireNonNull(getGroupList(id, list))) {
                if (p.getId().compareTo(id) == 0) {
                    return p;
                }
            }
        } else if (StringUtils.isNotBlank(code)) {
            for (Parser p : Objects.requireNonNull(getGroupList(code, list))) {
                if (p.getCode().equals(code)) {
                    return p;
                }
            }
        }
        return null;
    }

    public static List<Parser> convert(String config, List<Parser> list) {
        Map<String, Object> configList = convert(config);
        //  {"1":[{"4":[]},{"5":[]},{"6":[]}]},{"2":[]},{"3":[]}
        List<Parser> resultList = new ArrayList<>();
        if (configList != null && !configList.isEmpty()) {
            configList.forEach((k, v) -> {
                // 只有当是map类型的时候
                if (!(v instanceof Map)) {
                    v = null;
                }

                Parser parser = getChildParser(Long.parseLong(k), (Map) v, list);
                resultList.add(parser);
            });
        }
        return resultList;
    }

    private static Parser getChildParser(Long id, Map<String, Object> configList, List<Parser> list) {
        Parser pr = list.stream().filter(e -> e.getId().compareTo(id) == 0).findFirst().orElse(null);
        Parser parser = new Parser();
        if (pr == null) {
            log.error("解析器ID:{}.不存在", id);
        } else {
            BeanUtil.copyProperties(pr, parser);
            List<Parser> resultList = new ArrayList<>();
            if (!MapUtils.isEmpty(configList)) {
                configList.forEach((k, v) -> {
                    if (!(v instanceof Map)) {
                        v = null;
                    }
                    Parser p = getChildParser(Long.parseLong(k), (Map) v, list);
                    resultList.add(p);
                });
            }
            parser.setChildList(resultList);
        }
        return parser;
    }

    public static ParserInterface getParser(TaskInstance taskInstance, Parser parser, Boolean isLastParser) {
        ParserInterface parserInterface = null;
        try {

            Class<?> parserInterfaceClass = Class.forName(parser.getClazz());
            //获取指定的私有构造方法
            Constructor<?> parserzz = parserInterfaceClass.getDeclaredConstructor(Parser.class, TaskInstance.class);

            parserInterface = (ParserInterface) parserzz.newInstance(parser, taskInstance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return parserInterface;
    }

}
