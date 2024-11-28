package net.developia.domain;

import lombok.Data;

@Data
public class UserDTO {
    private String username;   // 사용자 이름 (기본 키)
    private String email;      // 이메일
    private char enabled;      // 활성화 여부
    private String nickname;   // 닉네임
    private String password;   // 비밀번호
    private String provider;   // 소셜 로그인 제공자
    private String providerId; // 소셜 로그인 제공자의 사용자 고유 ID
}