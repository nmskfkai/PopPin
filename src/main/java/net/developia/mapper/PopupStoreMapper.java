package net.developia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.developia.domain.PopupStoreVO;

@Mapper
public interface PopupStoreMapper {
	
	// 팝업 스토어 등록
	public void registerPopupStore(PopupStoreVO popupStore);
	
	// 대기 팝업 목록 조회(status = 0)
	public List<PopupStoreVO> getPopupStoresByUser(String username);
	
	// 승인 팝업 목록 조회(status = 1)
	public List<PopupStoreVO> getApprovePopupStoresByUser(String username);
		
	// 거절 팝업 목록 조회(status = 2)
	public List<PopupStoreVO> getRejectedPopupStoresByUser(String username);
	
    // 사용자 특정 팝업스토어 상세 조회
    public PopupStoreVO getPopupStoreById(Long storeId);

    // 팝업스토어 수정
    public int updatePopupStore(PopupStoreVO popupStore);

    // 팝업스토어 삭제
    public int deletePopupStore(Long storeId);
}
