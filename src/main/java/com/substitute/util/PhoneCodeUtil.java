package com.substitute.util;
/**
短信验证码
 商品购买地址： https://market.aliyun.com/products/57126001/cmapi024822.html
 String host = "https://fesms.market.alicloudapi.com"; //服务器
 String path = "/sms/"; //接口地址
 */


import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilei
 *
 * 2019年12月2日
 */
public class PhoneCodeUtil {
//	public static String GetCode_Login() 
//	{
//		phone = 
//	}
	public static String main(String phone,String skin,String sign) {
	    String host = "https://fesms.market.alicloudapi.com";
	    String path = "/smsmsg";
	    String method = "GET";
	    String appcode = "3ffed24d10374a13a933325d3fa7728f";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    int ran = (int) ((Math.random() * 9 + 1) * 100000);
	    querys.put("param", String.valueOf(ran));
	    querys.put("phone", phone);
	    querys.put("sign", sign);
	    querys.put("skin", skin);
            //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                * 或者直接下载：
                * http://code.fegine.com/HttpUtils.zip
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                * 相关jar包（非pom）直接下载：
                * http://code.fegine.com/aliyun-jar.zip
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
                //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
	    	//获取response的body
//	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    	return String.valueOf(ran);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return String.valueOf(ran);
		
	}
}
