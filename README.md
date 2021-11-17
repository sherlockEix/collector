## How to use

- add dependency

```xml
<dependency>
    <groupId>io.github.sherlockeix</groupId>
    <artifactId>collector</artifactId>
    <version>0.0.1-beta</version>
</dependency>
```

- declared log handler

```java

@Component
public class TestHandler implements LogHandler {
    private static final Logger log = LoggerFactory.getLogger(TestHandler.class);

    @Override
    public void log(String operatorCode, String desc, Object operator, List<Object> operatorData) {
        log.info("code:{}.operator:{}.operator data:{}", operatorCode, operator, operatorData);
    }
}
```

- add `log`、`operator`、`operatorData` annotation in method need log

```java

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
```
