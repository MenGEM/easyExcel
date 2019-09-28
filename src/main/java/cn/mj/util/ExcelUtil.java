package cn.mj.util;

import cn.mj.listener.ExcelListener;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

@Slf4j
public class ExcelUtil {

    /**
     * 解析excel文件内容
     *
     * @param fileName
     * @return
     */
    public static List<List<Object>> readExcel(String fileName) {
        ExcelReader excelReader=null;
        //根据文件名得到文件存入file中
        File file = new File(fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 解析每行结果在listener中处理
        ExcelListener listener = new ExcelListener();
        //得到文件的一个后缀名
        String hz=fileName.substring(fileName.indexOf(".")+1,fileName.length());
        if(hz.equals("xls")) {
            //根据后缀名判断来解决一个版本问题
            excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        }else{
            excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
        }
        excelReader.read();
        List<List<Object>> datas = listener.getDatas();
        return datas;
    }

}