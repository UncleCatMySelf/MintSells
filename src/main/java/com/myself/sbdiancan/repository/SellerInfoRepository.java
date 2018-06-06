package com.myself.sbdiancan.repository;

import com.myself.sbdiancan.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA DAO层
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 17:29 2018\6\5 0005
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);

}
