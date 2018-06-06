package com.myself.sbdiancan.service.impl;

import com.myself.sbdiancan.exception.SbException;
import com.myself.sbdiancan.service.RedisLock;
import com.myself.sbdiancan.service.SecKillService;
import com.myself.sbdiancan.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 16:59 2018\6\6 0006
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    private static final int TIMEOUT = 10 * 1000; //超时时间 10s

    @Autowired
    private RedisLock redisLock;

    /**
     * 某活动，皮蛋粥特价，限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }

    private String queryMap(String productId){
        return "活动，皮蛋粥特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目："
                + orders.size() + " 人";
    }


    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    /**
     * 不建议使用synchronized
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))){
            throw new SbException(101,"当前人数过多，请稍后再试");
        }

        //1. 查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0){
            throw new SbException(100, "活动结束");
        }else{
            //2. 下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3. 减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
        //解锁
        redisLock.unlock(productId, String.valueOf(time));
    }


}
