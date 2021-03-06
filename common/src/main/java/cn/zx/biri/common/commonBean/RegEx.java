package cn.zx.biri.common.commonBean;



/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:48 2019/2/23 0023
 */
public enum RegEx {
    EMAIL("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"),
    PHONE("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$"),
    ISBN("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$");


    String desRegex;

    RegEx(String desRegex){
        this.desRegex = desRegex;
    }

    @Override
    public String toString() {
        return desRegex;
    }
}
