package cn.zx.biri.adminservice.service.serviceImpl;

import cn.zx.biri.adminservice.feignService.BookService;
import cn.zx.biri.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:05 2019/4/8 0008
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    BookService bookService;
    @Override
    public Map shelvesBook() {
        Map map = bookService.shelvesBook();
        return map;
    }
}
