package net.developia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.ImageVO;
import net.developia.domain.PopUpStoreVO;
import net.developia.service.StoreService;

@Controller
@Log4j
@RequestMapping("/store/*")
@AllArgsConstructor
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 관리자가 팝업스토어 신청 목록 조회
    @GetMapping("/addstorelist")
    public String getPendingStores(Model model) {
        List<PopUpStoreVO> pendingStores = storeService.getPendingStores();
        model.addAttribute("pendingStores", pendingStores);
        return "/store/addStoreList";  // 신청 목록을 보여줄 JSP
    }
    
    // Store 신청 상세 페이지로 이동
    @GetMapping("/approveReject")
    public String getAddStoreDetail(@RequestParam("storeId") Long storeId, Model model) {
        PopUpStoreVO store = storeService.getAddStoreId(storeId);
        model.addAttribute("store", store);
        return "store/approveReject"; // storeDetail.jsp로 이동
    }
    
    // 팝업스토어 승인/거절 처리
    @PostMapping("/approveReject")
    public String approveReject(@RequestParam("storeId") Long storeId, @RequestParam("status") int status) {
        storeService.updateStoreStatus(storeId, status); // storeService에서 상태 업데이트 처리
        return "redirect:/store/list";  // 처리 후 팝업스토어 목록으로 리다이렉트
    }

    // 팝업스토어 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        try {
        	List<PopUpStoreVO> stores = storeService.getList();
        	// 이미지 데이터를 따로 가져오기
            Map<Long, List<ImageVO>> imagesMap = new HashMap<>();
            for (PopUpStoreVO store : stores) {
                List<ImageVO> images = storeService.getImagesByStoreId(store.getStoreId());
                imagesMap.put(store.getStoreId(), images);
            }

            model.addAttribute("imagesMap", imagesMap);
            model.addAttribute("stores", stores);
            log.info("Image list: " + imagesMap);
            log.info("Store list: " + stores); // 로그를 추가하여 데이터를 확인
            
            return "store/list";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Store 상세 페이지로 이동
    @GetMapping("/storeDetail")
    public String getStoreDetail(@RequestParam("storeId") Long storeId, Model model) {
        PopUpStoreVO store = storeService.getStoreId(storeId);
        model.addAttribute("store", store);
        return "store/storeDetail"; // storeDetail.jsp로 이동
    }
    
    // Store 검색 페이지
    @GetMapping("/search")
    public String searchStores(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        // 서비스 계층에서 검색 실행
        List<PopUpStoreVO> stores = storeService.searchStores(keyword);
        // 이미지 데이터를 따로 가져오기
        Map<Long, List<ImageVO>> imagesMap = new HashMap<>();
        for (PopUpStoreVO store : stores) {
            List<ImageVO> images = storeService.getImagesByStoreId(store.getStoreId());
            imagesMap.put(store.getStoreId(), images);
        }
       
        log.info("Image list: " + imagesMap);
        log.info("Store list: " + stores); // 로그를 추가하여 데이터를 확인
        model.addAttribute("imagesMap", imagesMap);
        model.addAttribute("stores", stores); // JSP로 전달
        return "store/searchResults"; // 검색 결과를 보여줄 JSP 경로
    }
    
    // 맵으로 검색 페이지
    @GetMapping("/mapSearch")
    public String mapSearch(Model model) {
    	
    	
    	return "store/mapSearch";
    }
    
    @GetMapping("/new")
    public String newStore() {
    	
    	return "store/new";
    }
    
    @PostMapping("/new")
    public String newStore(@ModelAttribute PopUpStoreVO store, RedirectAttributes rttr ) {
    	// 임의로 "user00" 값을 createdBy에 설정
        store.setCreatedBy("user00");

        // 서비스 호출하여 저장 처리
        storeService.register(store);

        // 성공 메시지 설정 (optional)
        rttr.addFlashAttribute("message", "팝업 스토어가 성공적으로 등록되었습니다.");
    	return "redirect:/store/addstorelist";
    }
}
