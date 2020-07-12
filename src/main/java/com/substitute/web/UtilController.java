package com.substitute.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.substitute.service.ActivitiesService;
import com.substitute.util.MailUtils;
import com.substitute.util.PhoneCodeUtil;



/**
 * @author lilei
 *
 * 2020年6月4日
 */
@Controller
@RequestMapping("/util")
public class UtilController extends BaseController{
	@Autowired
	private ActivitiesService activitiesService;
	
	/**
	 * 获取tel验证码
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="Telcode",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> Telcode(@RequestParam String tel){
		String skin = "3";//登录
		response.setCharacterEncoding("utf-8");
		String code = null;
		Map<String,Object> modelMap=new HashMap<>();
		if(tel!=null) 
		{
			if(tel.length()==11) 
			{
				String sign = "1";//签名
				code = PhoneCodeUtil.main(tel,skin,sign);
			}
		}
		if(code!=null) 
		{
			modelMap.put("message", "验证码发送成功");
			modelMap.put("code", code);
		}else 
		{
			modelMap.put("message", "验证码发送失败");
		}
		
		return modelMap;
	}
	/**
	 * 获取tel验证码
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="Emailcode",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> Emailcode(@RequestParam String mail){
		int ran = (int) ((Math.random() * 9 + 1) * 100000);
		String code = String.valueOf(ran);
		response.setCharacterEncoding("utf-8");
		
		Map<String,Object> modelMap=new HashMap<>();
		if(mail!=null) 
		{
			System.out.println(mail);
			MailUtils.SendMail(mail, code, "corasun项目组");
		}
		if(code!=null) 
		{
			modelMap.put("message", "验证码发送成功");
			modelMap.put("code", code);
		}else 
		{
			modelMap.put("message", "验证码发送失败");
		}
		
		return modelMap;
	}
}
