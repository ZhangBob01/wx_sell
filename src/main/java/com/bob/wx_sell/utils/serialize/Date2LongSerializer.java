package com.bob.wx_sell.utils.serialize;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-02 22:15
 * @Description:
 * 修改实体字段中的时间数据
 */
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        gen.writeNumber(date.getTime()/1000);
    }
}
