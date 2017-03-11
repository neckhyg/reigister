package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.ConferenceUserEntity;
import io.renren.service.ConferenceUserService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;

import io.renren.entity.SysUserEntity;
import io.renren.service.SysUserService;
import io.renren.utils.ShiroUtils;
import simplemail.Mail;
import simplemail.MailUtil;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-02 09:56:17
 */
@Controller
@RequestMapping("/sys/conference")
public class ConferenceUserController {
	@Autowired
	private ConferenceUserService conferenceUserService;
    @Autowired
    private SysUserService sysUserService;

	@RequestMapping("/conferenceuser.html")
	public String list(){
		return "conferenceuser/conferenceuser.html";
	}
	
	/**
	 * 列表
	 */
//	@ResponseBody
//	@RequestMapping("/list")
//	@RequiresPermissions("sys:conference:list")
//	public R list(Integer page, Integer limit){
//		Map<String, Object> map = new HashMap<>();
//		map.put("offset", (page - 1) * limit);
//		map.put("limit", limit);
//
//
//		//查询列表数据
//		List<ConferenceUserEntity> conferenceUserList = conferenceUserService.queryList(map);
//		int total = conferenceUserService.queryTotal(map);
//
//
//		PageUtils pageUtil = new PageUtils(conferenceUserList, total, limit, page);
//
//		return R.ok().put("page", pageUtil);
//	}
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:conference:list")
    public R list(String name,Integer page, Integer limit){
        Map<String, Object> map = new HashMap<>();
        Long userId = ShiroUtils.getUserId();
        SysUserEntity user = sysUserService.queryObject(userId);
        name  = user.getUsername();
        System.out.println("name : " +name);
        map.put("name", name);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据

        List<ConferenceUserEntity> conferenceUserList = conferenceUserService.queryList(map);
        int total = conferenceUserService.queryTotal(map);

//        for(int i =0; i< total; i++){
//            ConferenceUserEntity user =   conferenceUserList.get(i);
//            String testtype = user.getTestType().trim();
//        }
        PageUtils pageUtil = new PageUtils(conferenceUserList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:conference:info")
	public R info(@PathVariable("id") Integer id){
		ConferenceUserEntity conferenceUser = conferenceUserService.queryObject(id);
		
		return R.ok().put("conferenceUser", conferenceUser);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sys:conference:save")
	public R save(@RequestBody ConferenceUserEntity conferenceUser){
		conferenceUserService.save(conferenceUser);
        sendMail(conferenceUser.getEmail());
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:conference:update")
	public R update(@RequestBody ConferenceUserEntity conferenceUser){
		conferenceUserService.update(conferenceUser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sys:conference:delete")
	public R delete(@RequestBody Integer[] ids){
		conferenceUserService.deleteBatch(ids);
		
		return R.ok();
	}

    private boolean sendMail(String receiver){

        Mail mail = new Mail();

        mail.setHost("smtp.wxgyxy.cn");
        mail.setSender("heyg@wxgyxy.cn");
        mail.setUsername("heyg@wxgyxy.cn");
        mail.setPassword("wxgy2013");

        mail.setReceiver(receiver);

        mail.setSubject("更新个人信息");
        mail.setMessage("更新个人信息成功");
        MailUtil util = new MailUtil();
        if(util.send(mail)){

            return true;
        }else{
            return false;
        }
    }
}
