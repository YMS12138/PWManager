package cn.ymslucky.pwmanager.model;

import lombok.Data;

import java.sql.Date;

/**
 * 账号信息
 */
@Data
public class AccountInfo {
    private int id;
    private String title;
    private String account;
    private byte[] password;
    private Date createTime;
    private String description;
    private String user;
}
