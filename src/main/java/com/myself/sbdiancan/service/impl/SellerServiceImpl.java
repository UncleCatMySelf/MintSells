package com.myself.sbdiancan.service.impl;

import com.myself.sbdiancan.dataobject.SellerInfo;
import com.myself.sbdiancan.repository.SellerInfoRepository;
import com.myself.sbdiancan.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 17:35 2018\6\5 0005
 */
@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

}
