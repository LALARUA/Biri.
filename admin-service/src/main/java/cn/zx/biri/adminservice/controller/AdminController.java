package cn.zx.biri.adminservice.controller;

import cn.zx.biri.adminservice.feignService.BookService;
import cn.zx.biri.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:52 2019/4/8 0008
 */
@RestController
public class AdminController {


    @Autowired
    AdminService adminService;


    @GetMapping("shelvesBook")
    public Map shelvesBook(){
       return adminService.shelvesBook();
    }
}
