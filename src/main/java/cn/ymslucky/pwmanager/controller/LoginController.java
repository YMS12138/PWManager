package cn.ymslucky.pwmanager.controller;

import cn.ymslucky.pwmanager.APP;
import cn.ymslucky.pwmanager.service.UserService;
import cn.ymslucky.pwmanager.tools.StageTool;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 登录
 */
@Slf4j
public class LoginController {
    @FXML
    public TextField nickname;
    @FXML
    public TextField password;

    private UserService userService = new UserService();

    public void login() {
        //校验账号密码
        boolean res = userService.login(nickname.getText(), password.getText().getBytes());

        if (res) {
            try {
                //执行顺序不能颠倒, 在加载时将显示用户名，若颠倒，则无法显示用户名，因为在加载前usesr为null
                APP.user = nickname.getText().trim();
                APP.load();
            } catch (Exception e) {
                log.warn("主页面加载失败!");
                log.error(e.getMessage() + "\n");
            }
        } else {
            StageTool.INFO("登录失败！");
        }
    }

    public void register() {
        boolean res = userService.register(nickname.getText(), password.getText().getBytes());

        StageTool.INFO(res ? "注册成功！" : "注册失败！");
    }

    public void exit() {
        Platform.exit();
    }
}
