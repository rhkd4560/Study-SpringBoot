package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /*@GetMapping(value = "/{facebook|google|kakao}/complete")
    public String loginComplete(HttpSession session) {
        OAuth2Authentication authentication = (OAuth2Authentication)
                SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>)
                authentication.getUserAuthentication().getDetails();
        session.setAttribute("user", User.builder()
                .name(map.get("name"))
                .email(map.get("email"))
                .principal(map.get("id"))
                .socialType(SocialType.FACEBOOK)
                .createdDate(LocalDateTime.now())
                .build()
        );

        return "redirect:/board/list";
    }*/ //유저 정보를 가져와서 세션에 저장하는 코드

    @GetMapping(value = "/loginSuccess")
    public String loginComplete(@SocialUser User user)
    {
        return "redirect:/board/list";
    }

}
