package io.renren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
        String fullUrl=null;
      //  System.out.println("Old URL ="+url);

        String newStr = url.replace("_","/");
     //   System.out.println("New URL ="+newStr);
		return "sys/" + newStr + ".html";
	}
}
