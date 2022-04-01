package com.example.book_recommender.Controller;

import com.example.book_recommender.Service.BookService;
import com.example.book_recommender.beans.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("getRecommendBook")
    public AjaxResult getRecommendBook( @AuthenticationPrincipal Principal principal) {

        return AjaxResult.success(bookService.getRecommendBook(principal.getName()));
    }

    @GetMapping("addInterestBook")
    public AjaxResult addInterestBook( @AuthenticationPrincipal Principal principal, List<Long> bookIds) {
        if (bookService.addInterestBook(principal.getName(),bookIds)){
            return AjaxResult.success();
        }
        return AjaxResult.error("添加失败!请刷新重试!");
    }
    @GetMapping("addBook")
    public AjaxResult addBook( String bookName) {
        if (bookService.addBook(bookName)){
            return AjaxResult.success();
        }
        return AjaxResult.error("添加失败!请刷新重试!");
    }
}