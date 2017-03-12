package io.renren.controller;

import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.lang.System;
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

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import io.renren.utils.ExcelUtil;
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

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;

//    HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
//    HttpServletResponse response = ServletActionContext.getResponse();
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
    /**
    @RequestMapping(value = "exportfeedback")
    @ResponseBody

     * 导出excel
     */
    @ResponseBody
    @RequestMapping("/export")
    @RequiresPermissions("sys:conference:export")
    public R exportExcel()      {
//            HttpServletResponse response,
//                                    @RequestParam(value="query", required=false) String searchText,
//                                    @RequestParam(value="type", required=false) String strType,
//                                    @RequestParam(value="startDate", required=false) String startDate,
//                                    @RequestParam(value="endDate", required=false) String endDate)

        String fileName = "用户信息"+System.currentTimeMillis()+".xls"; //文件名
        String sheetName = "用户信息";//sheet名

        String []title = new String[]{"id","工号","姓名","性别","部门","手机","邮箱","考试类别"};//标题

        Map<String, Object> map = new HashMap<>();
        Long userId = ShiroUtils.getUserId();
        SysUserEntity user = sysUserService.queryObject(userId);
         String name  = user.getUsername();
        System.out.println("name : " +name);
        map.put("name", name);
//        map.put("offset", (page - 1) * limit);
//        map.put("limit", limit);
        List<ConferenceUserEntity> list = conferenceUserService.queryList(map);
      //  List<Feedback> list = appinfoService.getAllFeedbackForExcel(searchText, strType, startDate, endDate);//内容list

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String [][]values = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
            ConferenceUserEntity obj = list.get(i);
            values[i][0] = obj.getId()+"";
            values[i][1] = obj.getUserId();
            values[i][2] = obj.getUserName();
            values[i][3] = obj.getSex();
            values[i][4] = obj.getDepartment();
            values[i][5] = obj.getMobile();
            values[i][6] =  obj.getEmail();
            values[i][7] =  obj.getTestType();

        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);

        //将文件存到指定位置
        try {

            this.setResponseHeader( fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
//           doExportExcel(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return JResponse.success("ok");
        return R.ok();
    }

    public void setResponseHeader( String fileName) {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/x-download");
//
//        String fileName = "交易记录.xls";
//        fileName = URLEncoder.encode(fileName, "UTF-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    /**
     * excel导出交易记录
     * @param request
     * @param resp
     * @throws UnsupportedEncodingException
     */
    public void doExportExcel(List<ConferenceUserEntity> list) throws UnsupportedEncodingException
    {
//        response = RequestContextHolder.getRequestAttributes().getResponse();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HSSFWorkbook wb = new HSSFWorkbook();
//       System.out.println("request  = "+request+"   response ="+response);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");

        String fileName = "交易记录.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        HSSFSheet sheet = wb.createSheet("会员交易记录");
        sheet.setDefaultRowHeight((short) (2 * 256));
        sheet.setColumnWidth(0, 50 * 160);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        HSSFRow row = sheet.createRow((int) 0);
        sheet.createRow((int) 1);
        sheet.createRow((int) 2);
        sheet.createRow((int) 3);
        sheet.createRow((int) 4);
        sheet.createRow((int) 5);
        sheet.createRow((int) 6);
        sheet.createRow((int) 7);
        sheet.createRow((int) 8);
        sheet.createRow((int) 9);
        sheet.createRow((int) 10);
        sheet.createRow((int) 11);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCell cell = row.createCell(0);

        cell.setCellValue("编号 ");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("日期 ");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("订单号");
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("会员姓名");
        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("会员手机号");
        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("学校 ");
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("院系 ");
        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("交易日期 ");
        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("消费类型");
        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("产品名称");
        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("消费金额 ");
        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        List<ConferenceUserEntity> vUserOrder = list;

//        for (int i = 0; i < vUserOrder.size(); i++)
//        {
//            HSSFRow row1 = sheet.createRow((int) i + 1);
//            VUserOrder vuserOrder = vUserOrder.get(i);
//            row1.createCell(0).setCellValue(i + 1);
//            row1.createCell(1).setCellValue(DateUtils.getFormatDateTime(vuserOrder.getCreateTime()));//日期
//            row1.createCell(2).setCellValue(vuserOrder.getOrderCode());//订单号
//            row1.createCell(3).setCellValue(vuserOrder.getUserName());//会员姓名
//            row1.createCell(4).setCellValue(vuserOrder.getUserMphone());//会员手机号
//            row1.createCell(5).setCellValue(vuserOrder.getSchoolName());//学校
//            row1.createCell(6).setCellValue(vuserOrder.getFacultyName());//院系
//            row1.createCell(7).setCellValue(vuserOrder.getJyrq());//交易日期
//            int orderType = vuserOrder.getType();
//            String type = "";
//            if (orderType == OrderType.xx.getType())
//            {
//                type = "线下消费";
//            }
//            else if (orderType == OrderType.df.getType())
//            {
//                type = "网购代付";
//            }
//            else if (orderType == OrderType.tx.getType())
//            {
//                type = "用户提现";
//            }
//            else if (orderType == OrderType.qe.getType())
//            {
//                type = "全额还款";
//            }
//            else if (orderType == OrderType.fq.getType())
//            {
//                type = "分期还款";
//            }
//            row1.createCell(8).setCellValue(type);//消费类型
//            row1.createCell(9).setCellValue(vuserOrder.getProductName());//产品名称
//            row1.createCell(10).setCellValue(vuserOrder.getAmount().doubleValue());//消费金额
//            row1.createCell(11).setCellValue(
//                    (vuserOrder.getStatus() == 1) ? "交易成功" : (vuserOrder.getStatus() == 2) ? "失败" : "处理中");//状态
//        }
        try
        {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        }
        catch (Exception e)
        {
           // logger.info("=====导出excel异常====");

        }
//        catch (Exception e1)
//        {
//           // logger.info("=====导出excel异常====");
//        }
    }
}
