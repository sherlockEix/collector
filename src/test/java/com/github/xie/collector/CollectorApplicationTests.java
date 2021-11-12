package com.github.xie.collector;

import com.github.xie.collector.handler.TestHandler;
import com.github.xie.collector.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest()
class CollectorApplicationTests {
    @Autowired
    private UserService userService;
    @MockBean
    private TestHandler testHandler;

    @Test
    void test_no_operator_annotation() {
        String username = userService.getUsername();
        Mockito.verify(testHandler).log("get_user", "get username", null, null);
        Assertions.assertEquals("sherlock Xie", username);
    }

    @Test
    void test_normal_log() {
        String       username   = "sherlockXie";
        int          deleteUser = userService.deleteUser("admin", username);
        List<Object> objects    = new ArrayList<>();
        objects.add(username);
        Mockito.verify(testHandler).log("del_user", "delete user", "admin", objects);
        Assertions.assertEquals(1, deleteUser);
    }

}
