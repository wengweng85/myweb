/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : myweb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-26 22:33:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `page_design`
-- ----------------------------
DROP TABLE IF EXISTS `page_design`;
CREATE TABLE `page_design` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `page_name` varchar(200) DEFAULT NULL COMMENT '模型名称',
  `page_describe` varchar(1000) DEFAULT NULL COMMENT '页面说明',
  `serialized_data` text COMMENT '页面设计数据模型',
  `isvalid` varchar(10) DEFAULT NULL COMMENT '是否有效(1是 0 否)',
  `designtime` datetime DEFAULT NULL COMMENT '保存时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_design
-- ----------------------------
INSERT INTO `page_design` VALUES ('207a47f8-4218-11e7-88c8-507b9df7ea15', '测试', '这是一个测试的例子', '[     {         &quot;x&quot;: 0,         &quot;y&quot;: 2,&quot;width&quot;: 12,         &quot;height&quot;: 2,&quot;dataurl&quot;:&quot;http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html&quot;},     {         &quot;x&quot;: 0,         &quot;y&quot;: 0,&quot;width&quot;: 6,         &quot;height&quot;: 2,&quot;dataurl&quot;:&quot;http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html&quot;},     {         &quot;x&quot;: 6,         &quot;y&quot;: 0,&quot;width&quot;: 6,         &quot;height&quot;: 2,&quot;dataurl&quot;:&quot;http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html&quot;} ]', '1', '2017-05-26 22:17:20');




/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : myweb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-04 22:27:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `userid` varchar(32) NOT NULL COMMENT '用户表流水号',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `uuidname` varchar(200) DEFAULT NULL COMMENT '登录名',
  `cnname` varchar(200) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(200) DEFAULT NULL COMMENT '电话',
  `enabled` varchar(10) DEFAULT NULL COMMENT '是否有效标志(1有效 0无效)',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '0192023a7bbd73250516f069df18b500', 'admin', '超级管理员', null, null, '1');
