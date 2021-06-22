package com.securus.ciim.main.controller;


import com.securus.ciim.main.service.MainService;
import com.securus.ciim.main.service.SummaryService;
import com.securus.ciim.main.vo.AdminVo;
import com.securus.ciim.user.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ResourceLoader;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@Slf4j
public class MainController {

    MainService mainService;
    ResourceLoader resourceLoader;
    SummaryService summaryService;


    @GetMapping("/license")
    public String license(){
        return "라이센스가 만료되었습니다.";
    }

    @GetMapping("/hello")
    public String hello (){


        return "hello securus";
    }

    /**
     * 로그아웃
     * */
    @DeleteMapping("/")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginCheck");
        session.removeAttribute("adminVo");
        session.invalidate();
        log.info("#logout " );
        return "/";
    }

    @GetMapping("/admin/admin")
    public Map<String,Object> getAdminList(PageVO pageVO) throws Exception{

        HashMap<String,Object> map = new HashMap<>();
        if(mainService.roleCheck()!=1){
            return map;
        }
        map.put("pageVO",mainService.getPageVO(pageVO));
        map.put("adminList",mainService.getAdminList(pageVO));
        log.info("####/admin - pageVO : {}",map.get("pageVO"));
        return map;
    }


    @PostMapping("/admin/admin")
    public int adminInsert(AdminVo adminVo) throws Exception{
        if(mainService.roleCheck()!=1){
            return 0;
        }
        log.info("##insert : {}",adminVo);
        mainService.adminInsert(adminVo);
        return 1;
    }

    @DeleteMapping("/admin/admin")
    public int adminDelete(@RequestBody List<AdminVo> deleteList)throws Exception{
        if(mainService.roleCheck()!=1){
            return 0;
        }
        log.info("###user - deleteList : {}" , deleteList);


        return mainService.adminDelete(deleteList);
    }

    @GetMapping("/admin/admin/{userId}")
    public int idCheck(@PathVariable("userId") String userId)throws Exception{
        if(mainService.roleCheck()!=1){
            return 0;
        }

        return mainService.idCheck(userId);
    }


    @PutMapping("/admin/admin/{seq}")
    public int userUpdate(@RequestBody AdminVo adminVo) throws Exception{
        if(mainService.roleCheck()!=1){
            return 0;
        }
        log.info("####userUpdate - userMap : {}" , adminVo);

        return mainService.adminUpdate(adminVo);
    }

    @GetMapping("/summary")
    public HashMap<String,HashMap<String,Integer>> getSummary(){
        HashMap<String,HashMap<String,Integer>> hashMap = new HashMap<>();
        //연령대
        hashMap.put("ageSummary",summaryService.getAgeSummary());

        //성별
        hashMap.put("sexSummary",summaryService.getSexSummary());

        //지역별
        hashMap.put("addrSummary",summaryService.getAddrSummary());

        //회원가입날짜별
        hashMap.put("joinSummary",summaryService.getJoinUserSummary());

        //문자발송동의
        hashMap.put("smsSummary",summaryService.getSmsSummary());

        //메일발송동의
        hashMap.put("newSummary",summaryService.getNewSummary());


        return hashMap;
    }



}
