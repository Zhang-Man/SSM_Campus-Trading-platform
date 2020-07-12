package com.substitute.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 自定义json工具类
 * @author lianjie
 *
 */
public class JsonUtil {
	/**
	 * 将对象数组转化为json字符串
	 * @param objects
	 * @return
	 */
	public static String makeJson(Object [] objects){
		JSONArray array = JSONArray.fromObject(objects);
		return makeJson(array);
	}
	
	/**
	 * 将collection转化为json字符串
	 * @param collection
	 * @return
	 */
	public static String makeJson(Collection collection){
		JSONArray array = JSONArray.fromObject(collection);
		return array.toString();
	}
	
	/**
	 * 将单一对象转换为json字符串
	 * @param object
	 * @return
	 */
	public static String makeJson(Object object){
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		
		
		return jsonObject.toString();
		
	}
	
	/**
	 * 将json字符串写由response写入到客户端
	 * @param response
	 * @param json
	 */
	public static void writeJson(HttpServletResponse response,String json){
		response.setContentType("application/json;character=UTF8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



/**
 * 类：用于转换以yyyy-MM-dd格式的日期
 * @author lianjie
 *
 */
class JsonDateValueProcessor implements JsonValueProcessor{
	private  String datePattern = "yyyy-MM-dd";
	public JsonDateValueProcessor() {
		super();
	}
	public JsonDateValueProcessor(String datePattern) {
		super();
		this.datePattern = datePattern;
	}
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		try {
			if(value instanceof Date){
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
				Date date = (Date)value;
				return sdf.format(date);
			}
			return value == null ? null : value.toString();
		} catch (Exception e) {
			return null;
		}
	}
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return processArrayValue(value, jsonConfig);
	}
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	
	
}
