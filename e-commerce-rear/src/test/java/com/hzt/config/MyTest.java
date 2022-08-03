package com.hzt.config;

import static org.mockito.Mockito.never;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hzt.App;
import com.hzt.pojo.UmsUser;
import com.hzt.service.IUmsUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class MyTest {
    @Resource
    IUmsUserService userService;
    @Test
    public void testHandler() {
        UmsUser umsUser = new UmsUser();
        umsUser.setLoginName("0001");
        umsUser.setPassword("123456");
        umsUser.setPhone("13044443333");
        umsUser.setEmail("aaa@qq.com");
        umsUser.setNickyName("name");
        umsUser.setActive(1);
        userService.save(umsUser);

    }
    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank("   "));
    }
}
