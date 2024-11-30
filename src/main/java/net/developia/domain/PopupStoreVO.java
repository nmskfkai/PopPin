package net.developia.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class PopupStoreVO {
    private Long storeId;      // 팝업스토어 ID (기본 키)
    private String name;       // 이름
    private String location;   // 위치 정보
    private String description; // 설명
    private Date startDate;    // 시작 날짜
    private Date endDate;      // 종료 날짜
    private int views;         // 조회수
    private String createdBy;  // 작성자
    private int status;        // 승인 상태
}