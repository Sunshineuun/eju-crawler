package com.qiusm.eju.crawler.mybatis.typehandlerspackage;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Map.class)
public class MapTypeHandler extends BaseTypeHandler<Map<?, ?>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map map, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONObject.toJSONString(map));
    }

    @Override
    public Map<?,?> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String result = resultSet.getString(s);
        return JSONObject.parseObject(result, Map.class);
    }

    @Override
    public Map<?,?> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String result = resultSet.getString(i);
        return JSONObject.parseObject(result, Map.class);
    }

    @Override
    public Map<?,?> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String result = callableStatement.getString(i);
        return JSONObject.parseObject(result, Map.class);
    }
}
