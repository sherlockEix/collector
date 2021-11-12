package com.github.xie.collector.service;

import com.github.xie.collector.annotation.Log;
import com.github.xie.collector.annotation.Operator;
import com.github.xie.collector.annotation.OperatorData;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Override
    @Log(code = "get_user", desc = "get username")
    public String getUsername() {
        return "sherlock Xie";
    }

    @Override
    @Log(code = "del_user", desc = "delete user")
    public int deleteUser(@Operator String operator, @OperatorData String username) {
        return 1;
    }
}
