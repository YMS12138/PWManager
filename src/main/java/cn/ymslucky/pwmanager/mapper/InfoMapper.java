package cn.ymslucky.pwmanager.mapper;

import cn.ymslucky.pwmanager.model.AccountInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfoMapper {
    @Select("SELECT * FROM info WHERE user=#{nickname}")
    @Results(value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "title",property = "title"),
            @Result(column = "account",property = "account"),
            @Result(column = "password",property = "password"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "description",property = "description"),
            @Result(column = "user",property = "user")
    })
    public List<AccountInfo> selectAllByUser(String nickname);

    @Insert("INSERT INTO " +
            "info(title,account,password,create_time,description,user) " +
            "VALUE(#{title},#{account},#{password},#{createTime},#{description},#{user})")
    public int insert(AccountInfo accountInfo);

    @Delete("DELETE FROM info WHERE id=#{id}")
    public int deleteById(int id);
}
