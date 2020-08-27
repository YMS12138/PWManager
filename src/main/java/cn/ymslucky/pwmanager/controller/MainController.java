package cn.ymslucky.pwmanager.controller;

import cn.ymslucky.pwmanager.APP;
import cn.ymslucky.pwmanager.model.AccountInfo;
import cn.ymslucky.pwmanager.service.AccountInfoService;
import cn.ymslucky.pwmanager.service.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Label nickname;

    @FXML
    private Button createBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button confirmBtn;

    @FXML
    private VBox menu;

    @FXML
    private TextField accountTitle;
    @FXML
    private TextField account;
    @FXML
    private TextField password;
    @FXML
    private DatePicker createTime;
    @FXML
    private TextArea description;

    private AccountInfoService infoService = new AccountInfoService();
    private UserService userService = new UserService();

    //当前选中 id
    private int id;

    /**
     * 初始化
     */
    public void initialize() {
        refresh();
        nickname.setText(APP.user);
    }

    /**
     * 打开新建账号页面
     */
    public void openCreatePage() {
        //设置 删除按钮 禁用
        deleteBtn.setDisable(true);

        //3.设置日期---当前时间
        createTime.setValue(LocalDate.now());

        //设置可编辑
        editAble();
    }

    /**
     * 删除当前展示的账户
     */
    public void deleteInfo() {
        infoService.deleteById(id);

        refresh();

        deleteBtn.setDisable(true);
    }

    /**
     * 根据当前页面的内容，新建一条信息
     */
    public void insert() {
        //TODO：数据校验
        //......

        //从页面获取信息
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setTitle(accountTitle.getText());
        accountInfo.setAccount(account.getText());
        accountInfo.setPassword(password.getText().getBytes());
        accountInfo.setCreateTime(Date.valueOf(createTime.getValue()));
        accountInfo.setDescription(description.getText());
        accountInfo.setUser(APP.user);

        //插入数据库
        int res = infoService.insertInfo(accountInfo);

        confirmBtn.setVisible(false);

        //重新载入菜单栏
        refresh();

        //设置不可编辑
        editEnable();
    }

    /**
     * 刷新菜单栏
     */
    public void refresh() {
        menu.getChildren().clear();

        ArrayList<AccountInfo> accountInfos = (ArrayList<AccountInfo>) infoService.selectAllByUser(APP.user);
        accountInfos.forEach(accountInfo -> {
            Button btn = new Button(accountInfo.getTitle());

            btn.setPrefWidth(200);
            btn.setPrefHeight(50);
            btn.setMinHeight(50);

            //css
            btn.setStyle("-fx-background-color: #304156;" +
                    "-fx-font-size: 24px;" +
                    "-fx-text-fill: white");
            //css:hover
            btn.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                btn.setStyle("-fx-background-color: lightgray;" +
                        "-fx-font-size: 24px;" +
                        "-fx-text-fill: white");
            });
            //css:exit
            btn.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                btn.setStyle("-fx-background-color: #304156;" +
                        "-fx-font-size: 24px;" +
                        "-fx-text-fill: white");
            });

            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                show(accountInfo);
            });

            menu.getChildren().add(btn);
        });
    }

    /**
     * 设置页面可编辑
     */
    public void editAble() {
        //1.显示出可编辑
        accountTitle.setStyle("-fx-background-color: #d4ecec");
        account.setStyle("-fx-background-color: #d4ecec");
        password.setStyle("-fx-background-color: #d4ecec");

        //2.设置可编辑
        accountTitle.setEditable(true);
        account.setEditable(true);
        password.setEditable(true);
        description.setEditable(true);

        //3.设置 确认按钮 可见
        confirmBtn.setVisible(true);

        //4.菜单栏可见
        menu.setDisable(true);

        //5.重置页面内容
        accountTitle.setText("Title");
        account.setText("account");
        password.setText("password");
        description.setText("description......");
    }

    /**
     * 设置页面不可编辑
     */
    public void editEnable() {
        //1.显示出可编辑
        accountTitle.setStyle("-fx-background-color: none");
        account.setStyle("-fx-background-color: none");
        password.setStyle("-fx-background-color: none");

        //2.设置可编辑
        accountTitle.setEditable(false);
        account.setEditable(false);
        password.setEditable(false);
        description.setEditable(false);

        deleteBtn.setDisable(false);

        //3.设置 确认按钮 可见
        confirmBtn.setVisible(false);

        //4.菜单栏可见
        menu.setDisable(false);
    }

    /**
     * 显示选中的账户信息
     */
    public void show(AccountInfo accountInfo) {
        accountTitle.setText(accountInfo.getTitle());
        account.setText(accountInfo.getAccount());
        password.setText(new String(accountInfo.getPassword()));
        createTime.setValue(accountInfo.getCreateTime().toLocalDate());
        description.setText(accountInfo.getDescription());

        id = accountInfo.getId();
        deleteBtn.setDisable(false);
    }

    public void exit() {
        Platform.exit();
    }
}
