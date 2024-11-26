package net.developia.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.developia.domain.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO userDTO);
    UserDTO findByEmail(String email);
    UserDTO findByResetToken(String token);
    void updateResetToken(String email, String token);
    void updatePassword(String email, String newPassword);
    void insertAuthority(@Param("username") String username, @Param("authority") String authority); // 권한 삽입

}
