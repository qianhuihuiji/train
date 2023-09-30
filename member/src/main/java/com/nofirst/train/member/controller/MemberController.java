package com.nofirst.train.member.controller;

import com.nofirst.train.common.resp.CommonResp;
import com.nofirst.train.member.req.MemberRegisterReq;
import com.nofirst.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("count")
    public CommonResp<Integer> count(){
        int count = memberService.count();

        return new CommonResp<>(count);
    }

    @PostMapping("register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long userId = memberService.registerUser(req);

        return new CommonResp<>(userId);
    }
}
