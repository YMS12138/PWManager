package cn.ymslucky.pwmanager.service;

import cn.ymslucky.pwmanager.mapper.UserMapper;
import cn.ymslucky.pwmanager.tools.JNCryptorTool;
import cn.ymslucky.pwmanager.tools.SqlSessionTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.cryptonode.jncryptor.CryptorException;

@Slf4j
public class UserService {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    public UserService() {
        sqlSession = SqlSessionTool.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 注册
     */
    public boolean register(String nickname, byte[] password) {
        log.info("正在注册账号(" + nickname + ")...");

        log.info("开始数据加密......");
        try {
            JNCryptorTool.JNCryptor.encryptData(password, JNCryptorTool.SecretKey.toCharArray());
        } catch (CryptorException e) {
            log.warn("数据加密失败！");
            log.error(e.getMessage());
            return false;
        }
        log.info("数据加密完成!");

        try {
            if (userMapper.register(nickname, password) == 1) {
                log.info("注册成功！\n");
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.warn("注册失败!\n");
            log.error(e.getMessage() + "\n");
            sqlSession.rollback();

            return false;
        }

        return true;
    }

    /**
     * 登录
     */
    public boolean login(String nickname, byte[] password) {
        String temp = userMapper.selectByNickname(nickname);
        byte[] pwd;
        if (temp != null) {
            pwd = temp.getBytes();
        } else {
            return false;
        }

        if (password.length == 0 || password.length != pwd.length) {
            return false;
        }

        for (int i = 0; i < pwd.length; i++) {
            if (pwd[i] != password[i]) {
                return false;
            }
        }

        log.info(nickname + " 登录成功!\n");
        return true;
    }
}
