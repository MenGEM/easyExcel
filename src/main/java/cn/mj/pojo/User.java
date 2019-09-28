package cn.mj.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseRowModel {

    @ExcelProperty(value = "id",index = 0)
    private int id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "用户名",index = 2)
    private String username;

    @ExcelProperty(value = "密码",index = 3)
    private String password;

    @ExcelProperty(value = "电子邮箱",index = 4)
    private String email;

    @ExcelProperty(value = "电话",index = 5)
    private String phone;

    @ExcelProperty(value = "地址",index = 6)
    private String address;
}