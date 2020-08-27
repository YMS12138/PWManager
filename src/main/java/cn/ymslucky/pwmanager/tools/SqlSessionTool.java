package cn.ymslucky.pwmanager.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlSessionTool {
    /**
     * 打开一个Session
     */
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        try {
            session = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }
}
