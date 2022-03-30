package com.example.book_recommender.Controller;
import com.example.book_recommender.Service.InterestService;

import com.example.book_recommender.beans.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("interest")
public class InterestController{
    @Autowired
    InterestService interestService;
    @GetMapping("addInterestForUser")
    public AjaxResult addInterestForUser(List<Long> interestIds, @AuthenticationPrincipal Principal principal){
        if (interestService.addInterestForUser(principal.getName(),interestIds)) {
            return AjaxResult.success();
        }

        return AjaxResult.error("添加失败 请刷新重试");
    }
    @GetMapping("deleteInterestForUser")
    public AjaxResult deleteInterestForUser( @AuthenticationPrincipal Principal principal, Long interestId) {
        if (interestService.deleteInterestForUser(principal.getName(),interestId)) {
            return AjaxResult.success();
        }
        return AjaxResult.error("删除失败 请刷新重试");

    }
    public AjaxResult addInterest(String interestName) {
        if (   interestService.addInterest(interestName)) {
            return AjaxResult.success();
        }
        return AjaxResult.error("添加失败 可能重复了!");



    }
}