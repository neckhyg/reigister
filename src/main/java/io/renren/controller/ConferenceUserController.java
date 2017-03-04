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
	
	@RequestMapping("/conferenceuser.html")
	public String list(){
		return "conferenceuser/conferenceuser.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:conference:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ConferenceUserEntity> conferenceUserList = conferenceUserService.queryList(map);
		int total = conferenceUserService.queryTotal(map);

//        Set<String> set = map.keySet();
//        for (String s:set) {
//            System.out.println(s+","+map.get(s));
//        }
		for(int i =0; i< total; i++){
            ConferenceUserEntity user =   conferenceUserList.get(i);
            String testtype = user.getTestType().trim();
            System.out.println("testtype"+testtype);
          //  if(testtype !=null)      {
//                switch (testtype){
//                    case "sk":
//                        user.setTestType("数控技术");
//                        break;
//                    case "dz":
//                        user.setTestType("电子信息");
//                        break;
//                    case "fz":
//                        user.setTestType("服装工程");
//                        break;
//                    case "tc":
//                        user.setTestType("陶瓷艺术");
//                        break;
//                    default:
//                    //    user.setTestType("陶瓷艺术");
//                        break;
//
//                }
           // }

        }
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
	
}
