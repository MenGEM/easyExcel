package cn.mj.controller;

import cn.mj.pojo.User;
import cn.mj.service.UserService;
import cn.mj.util.ExcelUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/excel")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/selectAll")
    public String getAllUser(){
        List<User> userList = userService.selectAll();
        // 文件输出位置
        OutputStream out = null;
        try {
            //得到当前时间
            String format = new SimpleDateFormat("yyyy-MM-dd").format( new Date( ));
            //设置保存地址和类型
            File path = new File(/*desktop*/"D:\\"+format+".xlsx");
            //输出文件
            out = new FileOutputStream(path);
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0,User.class);
            // 写数据到 Writer 上下文中
            // 入参1: 创建要写入的模型数据
            // 入参2: 要写入的目标 sheet
            writer.write(userList, sheet1);
            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "失败";
        }finally {
            try {
                // 关闭流
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "失败";
            }
            return "成功!";
        }
    }


    @GetMapping("/writerExcel")
    @ResponseBody
    public List<List<Object>> writerExcel() {
        //将返回的数据进行一个封装
        List<List<Object>> lists = ExcelUtil.readExcel("D:\\2019-09-28.xlsx");
        if (lists != null) {
            //返回结果
            log.info("表数据："+lists);
        } else {
            log.info("空异常！");
        }
        return lists;
    }
}
