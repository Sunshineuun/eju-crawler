package com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import org.springframework.stereotype.Service;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.UNIT_ID;

/**
 * ajk单元号
 *
 * @author qiushengming
 */
@Service
public class AjkAppUnit extends AjkAppSkeletonBase {

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        super.parser(requestDto, responseDto);
        if (!responseDto.getSuccess()) {
            return;
        }
        JSONArray array = responseDto.getResult().getJSONArray("list");
        if (array == null) {
            return;
        }

        for (Object o : array) {
            JSONObject var1 = (JSONObject) o;
            var1.put(UNIT_ID, var1.get("id"));
        }
    }
}
