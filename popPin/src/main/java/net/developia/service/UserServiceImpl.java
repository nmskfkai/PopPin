package net.developia.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.developia.domain.UserDTO;
import net.developia.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDTO userDTO) {
    	// 비밀번호 암호화
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // 활성화 상태 설정
        userDTO.setEnabled('1');
        // Provider 설정
        userDTO.setProvider("local");
        userDTO.setProviderId(null);
        
        // 사용자 등록
        userMapper.insertUser(userDTO);
        // 권한 부여
        userMapper.insertAuthority(userDTO.getUsername(), "ROLE_MEMBER");
    }

    @Override
    public boolean authenticate(String email, String password) {
        UserDTO user = userMapper.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public String generatePasswordResetToken(String email) {
        UserDTO user = userMapper.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userMapper.updateResetToken(email, token);
            return token; // 이메일 전송 로직 추가 가능
        }
        return null;
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        UserDTO user = userMapper.findByResetToken(token);
        if (user != null) {
            userMapper.updatePassword(user.getEmail(), newPassword);
            userMapper.updateResetToken(user.getEmail(), null); // 토큰 초기화
            return true;
        }
        return false;
    }
}
