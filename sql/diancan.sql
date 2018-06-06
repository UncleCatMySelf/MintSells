/*
Navicat MySQL Data Transfer

Source Server         : mypc
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : diancan

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-06 21:30:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1528008329496154126', '1528008329307472971', '123456', '皮蛋粥', '3.20', '1', 'http://xxxx.jpg', '2018-06-03 14:45:29', '2018-06-03 14:45:29');
INSERT INTO `order_detail` VALUES ('1528016315848792362', '1528016315820927896', '123456', '皮蛋粥', '3.20', '2', 'http://xxxx.jpg', '2018-06-03 16:58:35', '2018-06-03 16:58:35');
INSERT INTO `order_detail` VALUES ('1528016939499122060', '1528016939418834757', '123456', '皮蛋粥', '3.20', '2', 'http://xxxx.jpg', '2018-06-03 17:08:59', '2018-06-03 17:08:59');
INSERT INTO `order_detail` VALUES ('1528266876066851800', '1528266876038655356', '123456', '皮蛋粥', '3.20', '2', 'http://s2.cdn.xiachufang.com/4ea69a32874211e6b87c0242ac110003_384w_512h.jpg?imageView2/2/w/660/interlace/1/q/90', '2018-06-06 14:34:36', '2018-06-06 14:34:36');
INSERT INTO `order_detail` VALUES ('1876234', '222222222', '123333', '皮蛋粥', '1.20', '2', 'http://xxxx.jpg', '2018-06-03 10:31:09', '2018-06-03 10:31:09');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('123456', 'MYSelf', '18820116736', '广州', '123114', '2.30', '2', '0', '2018-06-02 23:22:35', '2018-06-04 15:12:42');
INSERT INTO `order_master` VALUES ('123457', 'MYSelf', '18820116736', '广州', '123114', '2.50', '2', '0', '2018-06-02 23:32:18', '2018-06-04 16:35:38');
INSERT INTO `order_master` VALUES ('1528008329307472971', 'MySelf', '18820116736', '广州', '112114', '3.20', '2', '1', '2018-06-03 14:45:29', '2018-06-04 16:38:07');
INSERT INTO `order_master` VALUES ('1528016315820927896', '张三', '18866661111', '广州', 'asldkfmaoncqnvqiov', '6.40', '0', '0', '2018-06-03 16:58:35', '2018-06-04 15:08:23');
INSERT INTO `order_master` VALUES ('1528016939418834757', '张三', '18866661111', '广州', 'asldkfmaoncqnvqiov', '6.40', '0', '0', '2018-06-03 17:08:59', '2018-06-03 17:08:59');
INSERT INTO `order_master` VALUES ('1528266876038655356', '燕燕', '18866661111', '广州', 'asldkfmaoncqnvqiov', '6.40', '0', '0', '2018-06-06 14:34:36', '2018-06-06 14:34:36');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热销榜', '2', '2018-06-01 18:24:34', '2018-06-01 18:24:34');
INSERT INTO `product_category` VALUES ('4', '女生最爱', '3', '2018-06-01 20:23:15', '2018-06-01 20:23:15');
INSERT INTO `product_category` VALUES ('6', '男生专享', '5', '2018-06-01 21:02:07', '2018-06-01 21:02:07');
INSERT INTO `product_category` VALUES ('7', '淑女美颜', '54', '2018-06-05 17:08:28', '2018-06-05 17:08:37');
INSERT INTO `product_category` VALUES ('8', '夏日清凉', '101', '2018-06-06 15:09:54', '2018-06-06 15:09:54');
INSERT INTO `product_category` VALUES ('10', '冬日温暖', '102', '2018-06-06 15:55:08', '2018-06-06 15:55:08');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` int(11) NOT NULL,
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('123456', '皮蛋粥', '3.20', '97', '很好喝的粥', 'http://s2.cdn.xiachufang.com/4ea69a32874211e6b87c0242ac110003_384w_512h.jpg?imageView2/2/w/660/interlace/1/q/90', '1', '2', '2018-06-01 21:29:14', '2018-06-06 20:16:20');
INSERT INTO `product_info` VALUES ('223456', '皮皮虾', '3.20', '100', '很好吃的虾，很好很好吃', 'http://a0.att.hudong.com/63/29/01300000057455120349291249177.jpg', '0', '2', '2018-06-01 22:01:10', '2018-06-05 16:53:01');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('123456', 'Myself', '123456', 'aaa', '2018-06-06 14:17:45', '2018-06-06 14:17:45');
