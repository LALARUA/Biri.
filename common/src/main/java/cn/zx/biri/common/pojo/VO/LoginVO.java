package cn.zx.biri.common.pojo.VO;

import cn.zx.biri.common.commonBean.RegEx;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:21 2019/2/26 0026
 */
public class LoginVO{
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",message = "请输入正确的邮箱")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
