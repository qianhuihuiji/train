package com.nofirst.train.member.service;

import cn.hutool.core.collection.CollectionUtil;
import com.nofirst.train.common.exception.BusinessException;
import com.nofirst.train.common.exception.BusinessExceptionEnum;
import com.nofirst.train.common.util.SnowUtil;
import com.nofirst.train.member.domain.Member;
import com.nofirst.train.member.domain.MemberExample;
import com.nofirst.train.member.mapper.MemberMapper;
import com.nofirst.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long registerUser(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollectionUtil.isNotEmpty(members)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
