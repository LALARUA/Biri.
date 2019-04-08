package cn.zx.biri.adminservice.service;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:04 2019/4/8 0008
 */
public interface AdminService {

    @GetMapping("shelvesBook")
    public Map shelvesBook();
}
