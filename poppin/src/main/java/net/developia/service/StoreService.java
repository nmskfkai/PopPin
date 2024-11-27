package net.developia.service;

import java.util.List;

import net.developia.domain.ImageVO;
import net.developia.domain.PopUpStoreVO;

public interface StoreService {
    // 팝업스토어 목록 조회
    public List<PopUpStoreVO> getList() throws Exception;

    // 특정 Store 정보 조회
    public PopUpStoreVO getStoreId(Long storeId);

    // 팝업스토어 신청
    public void applyStore(PopUpStoreVO popUpStore);

    public PopUpStoreVO getAddStoreId(Long storeId);
    
    // 관리자가 팝업스토어 신청 목록 조회
    public List<PopUpStoreVO> getPendingStores();

    // 팝업스토어 상태 업데이트
    public void updateStoreStatus(Long storeId, int status);  // 상태 업데이트 메서드
    
    //검색 리스트
    List<PopUpStoreVO> searchStores(String keyword);
    
    //각 스토어 이미지 
    List<ImageVO> getImagesByStoreId(Long storeId);

	void register(PopUpStoreVO store);

}
