package com.ls.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 自定义响应结构
 */
public class DataResult {
 
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
 
    // 响应业务状态
    private Integer status;
 
    // 响应消息
    private String msg;
 
    // 响应中的数据
    private Object data;
 
    public static DataResult build(Integer status, String msg, Object data) {
        return new DataResult(status, msg, data);
    }
 
    public static DataResult ok(Object data) {
        return new DataResult(data);
    }
 
    public static DataResult ok() {
        return new DataResult(null);
    }
 
    public DataResult() {
 
    }
 
    public static DataResult build(Integer status, String msg) {
        return new DataResult(status, msg, null);
    }
 
    public DataResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
 
    public DataResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
 
//    public Boolean isOK() {
//        return this.status == 200;
//    }
 
    public Integer getStatus() {
        return status;
    }
 
    public void setStatus(Integer status) {
        this.status = status;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public Object getData() {
        return data;
    }
 
    public void setData(Object data) {
        this.data = data;
    }
 
    /**
     * 将json结果集转化为DataResult对象
     * 
     * @param jsonData json数据
     * @param clazz DataResult中的object类型
     * @return
     */
    public static DataResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, DataResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
 
    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static DataResult format(String json) {
        try {
            return MAPPER.readValue(json, DataResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static DataResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
 
}

