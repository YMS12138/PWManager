package cn.ymslucky.pwmanager.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT password FROM user WHERE nickname=#{nickname}")
    public String selectByNickname(@Param("nickname") String nickname);

    @Insert("INSERT INTO user(nickname,password) VALUE(#{nickname},#{password})")
    public int register(@Param("nickname") String nickname, @Param("password") byte[] password);
}
