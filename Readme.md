# 							账号管理器

## 一、实现技术

JavaFX、MySQL

## 二、功能概述

### 1. 用户注册



### 2. 用户登录

![登录](C:\Users\Administrator\Pictures\Saved Pictures\登录)

### 3. 新建账号记录

可记录内容：

**标题、账号名、密码、创建日期、描述**

### 4. 删除账号记录

### 5. 查看自己的所有账号记录

![主界面](C:\Users\Administrator\Pictures\Saved Pictures\主界面)

## 三、备注

程序对账号密码进行了AES加密，存储进数据库的数据为密文，数据信息仅在程序内可见。

密钥保存在代码中---SecretKey

```java
public class JNCryptorTool {
    public static final AES256JNCryptor JNCryptor = new AES256JNCryptor();
    public static final String SecretKey = "123456789abcdefg";
}
```

有BUG尽量自己修（不需要通知我），BUG反馈：ymslucky@163.com