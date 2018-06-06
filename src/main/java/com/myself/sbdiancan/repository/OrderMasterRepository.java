package com.myself.sbdiancan.repository;

import com.myself.sbdiancan.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA DAO层
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 22:20 2018\6\2 0002
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
