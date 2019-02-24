package cn.zx.biri.common.pojo;

import javax.validation.constraints.NotNull;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:05 2019/2/23 0023
 */
public class User {

    private String username;


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
