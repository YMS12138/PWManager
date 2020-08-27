package cn.ymslucky.pwmanager.service;

import cn.ymslucky.pwmanager.mapper.InfoMapper;
import cn.ymslucky.pwmanager.model.AccountInfo;
import cn.ymslucky.pwmanager.tools.JNCryptorTool;
import cn.ymslucky.pwmanager.tools.SqlSessionTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.cryptonode.jncryptor.CryptorException;

import java.util.List;

@Slf4j
public class AccountInfoService {

    private InfoMapper mapper;
    private SqlSession sqlSession;

    String temp;

    public AccountInfoService() {
        sqlSession = SqlSessionTool.getSqlSession();
        mapper = sqlSession.getMapper(InfoMapper.class);
    }

    /**
     * 查找当前用户的所有信息
     */
    public List<AccountInfo> selectAllByUser(String nickname) {
        log.info("查询当前用户的所有账号数据......");

        List<AccountInfo> accountInfos = mapper.selectAllByUser(nickname);
        sqlSession.commit();

        log.info("查询成功！共" + accountInfos.size() + "条记录");

        if (accountInfos.size() > 0) {
            log.info("开始数据解密......");
            accountInfos.forEach(accountInfo -> {
                try {
                    byte[] bytes = JNCryptorTool.JNCryptor.decryptData(accountInfo.getPassword(), JNCryptorTool.SecretKey.toCharArray());
                    accountInfo.setPassword(bytes);
                } catch (CryptorException e) {
                    log.warn("数据解密失败！当前数据id：" + accountInfo.getId());
                    log.error(e.getMessage());
                }
            });
            log.info("数据解密完成!\n");
        }


        return accountInfos;
    }

    /**
     * 创建一条账号信息
     */
    public int insertInfo(AccountInfo accountInfo) {
        log.info("新建账号中：" + accountInfo.getAccount());

        log.info("开始数据加密......");
        //加密 "账号密码"
        try {
            byte[] bytes = JNCryptorTool.JNCryptor.encryptData(accountInfo.getPassword(), JNCryptorTool.SecretKey.toCharArray());
            accountInfo.setPassword(bytes);
        } catch (CryptorException e) {
            log.warn("未能进行加密，创建失败！\n");
            return 0;
        }
        log.info("数据加密完成!");

        int insert = mapper.insert(accountInfo);
        //提交事务
        sqlSession.commit();

        log.info("新建成功！\n");

        return insert;
    }

    /**
     * 根据id删除一条账号信息
     */
    public void deleteById(int id) {
        log.info("正在删除账号信息: id---" + id);

        int i = mapper.deleteById(id);
        sqlSession.commit();

        log.info("操作成功！已删除 " + i + "条记录\n");
    }
}
