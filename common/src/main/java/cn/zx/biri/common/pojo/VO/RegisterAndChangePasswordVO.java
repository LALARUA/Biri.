package cn.zx.biri.common.pojo.VO;

import javax.validation.constraints.NotNull;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:24 2019/2/26 0026
 */
public class RegisterAndChangePasswordVO extends LoginVO {

    @NotNull(message = "验证码不能为空")
    private String CAPTCHA;

    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCAPTCHA() {
        return CAPTCHA;
    }

    public void setCAPTCHA(String CAPTCHA) {
        this.CAPTCHA = CAPTCHA;
    }
}
