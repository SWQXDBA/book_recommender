package com.example.book_recommender.Controller;
import com.example.book_recommender.Service.UserService;
import com.example.book_recommender.ViewModel.UserRegisterRequestVO;
import com.example.book_recommender.beans.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController{

    @Autowired
    UserService userService;
    @RequestMapping(value = "register",method = {RequestMethod.POST})
    public AjaxResult register(@RequestBody UserRegisterRequestVO userRegisterRequestVO){
        if (userService.register(userRegisterRequestVO.getUsername(),userRegisterRequestVO.getPassword())) {
            return AjaxResult.success("注册成功!");
        }else{
            return AjaxResult.error("用户名已存在!");
        }
    }

}