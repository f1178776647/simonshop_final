/*
Navicat MySQL Data Transfer

Source Server         : hwsql
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : simonshop_final

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2017-05-07 22:52:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别标识符',
  `name` varchar(100) NOT NULL COMMENT '商品类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '家用电器');
INSERT INTO `t_category` VALUES ('2', '床上用品');
INSERT INTO `t_category` VALUES ('3', '文具用品');
INSERT INTO `t_category` VALUES ('4', '休闲食品');

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `url` varchar(100) NOT NULL COMMENT '用户请求资源url',
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `operation` varchar(200) NOT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', '郑晓红', 'http://localhost:8080/', '2017-05-05 22:59:28', 'findAllCategories方法准备执行');
INSERT INTO `t_log` VALUES ('2', '郑晓红', 'http://localhost:8080/', '2017-05-05 22:59:28', 'findAllCategories方法执行完毕');
INSERT INTO `t_log` VALUES ('3', '郑晓红', 'http://localhost:8080/', '2017-05-05 22:59:28', 'findAllProducts方法准备执行');
INSERT INTO `t_log` VALUES ('4', '郑晓红', 'http://localhost:8080/', '2017-05-05 22:59:28', 'findAllProducts方法执行完毕');
INSERT INTO `t_log` VALUES ('5', '郑晓红', 'http://localhost:8080/product/showProduct', '2017-05-05 22:59:28', 'findAllProducts方法准备执行');
INSERT INTO `t_log` VALUES ('6', '郑晓红', 'http://localhost:8080/product/showProduct', '2017-05-05 22:59:28', 'findAllProducts方法执行完毕');
INSERT INTO `t_log` VALUES ('7', '郑晓红', 'http://localhost:8080/user/register', '2017-05-05 23:00:38', 'addUser方法准备执行');
INSERT INTO `t_log` VALUES ('8', '郑晓红', 'http://localhost:8080/user/register', '2017-05-05 23:00:38', 'addUser方法执行完毕');
INSERT INTO `t_log` VALUES ('9', '郑晓红', 'http://localhost:8080/', '2017-05-05 23:00:41', 'findAllCategories方法准备执行');
INSERT INTO `t_log` VALUES ('10', '郑晓红', 'http://localhost:8080/', '2017-05-05 23:00:41', 'findAllCategories方法执行完毕');
INSERT INTO `t_log` VALUES ('11', '郑晓红', 'http://localhost:8080/', '2017-05-05 23:00:41', 'findAllProducts方法准备执行');
INSERT INTO `t_log` VALUES ('12', '郑晓红', 'http://localhost:8080/', '2017-05-05 23:00:41', 'findAllProducts方法执行完毕');
INSERT INTO `t_log` VALUES ('13', '郑晓红', 'http://localhost:8080/product/showProduct', '2017-05-05 23:00:41', 'findAllProducts方法准备执行');
INSERT INTO `t_log` VALUES ('14', '郑晓红', 'http://localhost:8080/product/showProduct', '2017-05-05 23:00:41', 'findAllProducts方法执行完毕');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单标识符',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `telephone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `total_price` double DEFAULT NULL COMMENT '总金额',
  `delivery_address` varchar(50) DEFAULT NULL COMMENT '送货地址',
  `order_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '郑晓红', '13956567889', '2000', '泸职院信息工程系', '2016-12-25 17:12:36');
INSERT INTO `t_order` VALUES ('2', '温志军', '13956678907', '1000', '泸职院机械工程系', '2016-12-02 17:12:17');
INSERT INTO `t_order` VALUES ('3', '郑晓红', '15890906789', '7000', '泸州职业技术学院信息工程系', '2017-05-05 22:57:15');
INSERT INTO `t_order` VALUES ('4', '郑晓红', '15890905678', '5000', '泸州职业技术学院信息系', '2017-05-06 13:32:44');
INSERT INTO `t_order` VALUES ('5', '郑晓红', '15890905678', '5000', '泸州职业技术学院信息系', '2017-05-06 13:33:32');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品标识符',
  `name` varchar(200) NOT NULL COMMENT '商品名称',
  `price` double NOT NULL COMMENT '商品单价',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(50) DEFAULT NULL COMMENT '商品图片',
  `category_id` int(11) NOT NULL COMMENT '商品类别标识符',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '容声电冰箱', '2000', '2017-05-03 17:38:27', '/images/product1.jpg', '1');
INSERT INTO `t_product` VALUES ('2', '松下电视', '5000', '2017-05-03 17:38:32', '/images/product2.jpg', '1');
INSERT INTO `t_product` VALUES ('3', '红岩墨水', '3', '2017-05-03 17:38:37', '/images/product3.jpg', '3');
INSERT INTO `t_product` VALUES ('4', '海尔洗衣机', '1000', '2017-05-03 17:38:40', '/images/product4.jpg', '1');
INSERT INTO `t_product` VALUES ('5', '新宇电饭煲', '1200', '2017-05-03 17:38:46', '/images/product5.jpg', '1');
INSERT INTO `t_product` VALUES ('6', '英雄微波炉', '600', '2017-05-03 17:38:50', '/images/product6.jpg', '1');
INSERT INTO `t_product` VALUES ('7', '红双喜席梦思', '700', '2017-05-03 17:38:55', '/images/product7.jpg', '2');
INSERT INTO `t_product` VALUES ('8', '旺仔牛奶糖', '24.4', '2017-05-03 17:39:00', '/images/product8.jpg', '4');
INSERT INTO `t_product` VALUES ('9', '西蒙枕头', '100', '2017-05-03 17:39:04', '/images/product9.jpg', '2');
INSERT INTO `t_product` VALUES ('10', '甜甜毛毯', '400', '2017-05-03 17:39:10', '/images/product10.jpg', '2');
INSERT INTO `t_product` VALUES ('11', '永久钢笔', '50', '2017-05-03 17:39:14', '/images/product11.jpg', '3');
INSERT INTO `t_product` VALUES ('12', '硬面抄笔记本', '5', '2017-05-03 17:39:17', '/images/product12.jpg', '3');
INSERT INTO `t_product` VALUES ('13', '晨光橡皮擦', '0.5', '2017-05-03 17:39:22', '/images/product13.jpg', '3');
INSERT INTO `t_product` VALUES ('14', '美的空调', '3000', '2017-05-03 17:39:25', '/images/product14.jpg', '1');
INSERT INTO `t_product` VALUES ('15', '迷你深海鱼肠', '14.4', '2017-05-03 17:41:24', '/images/product15.jpg', '4');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `popedom` int(11) DEFAULT NULL COMMENT '0：管理员；1：普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79', '15734345678', '2017-05-07 10:02:57', '0');
INSERT INTO `t_user` VALUES ('2', '郑晓红', '22e7e9d85b7fe6004f7b9f3aa592ea9ec9ce098682e8192fa83785f1784c768d1d1ac3b8afcae88666f66aec24739ac133e9d4adc7506f1a5f1f6078cb27c674', '13956567889', '2017-05-07 10:03:55', '1');
INSERT INTO `t_user` VALUES ('3', '温志军', '4cf5a5be41f417cb2087f1f17e44734ae9b1677dc6b0ed3b80de422cc8e5607980ed08334540c15966485db039927bade22d3dedd5fd3b3f7d9743c20310882c', '13956678907', '2017-05-07 10:04:30', '1');
INSERT INTO `t_user` VALUES ('4', '涂文艳', 'a73ae84199edd6790cfc5497e7d8fe7b600c71542c6b9fc77e3f43834564905dea73a533858cd0ddad1702074f32f0d9a44545c28ac17b4138204a746df393e1', '15890905678', '2017-05-07 10:04:37', '1');
