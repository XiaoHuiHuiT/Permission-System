# SQL脚本

```mysql
/*
Navicat MySQL Data Transfer

Source Server         : LK_Conncetion
Source Server Version : 80016
Source Host           : 127.0.0.1:3306
Source Database       : promission

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-07-07 14:48:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '技术部');
INSERT INTO `department` VALUES ('2', '人事部');
INSERT INTO `department` VALUES ('3', '财务部');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `inputtime` datetime DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `dep_id` bigint(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'scott', '2019-10-31 16:00:00', '18819522037', '303158131@qq.com', '0', '1', '1', 'bc6db50e32a82020a2bc35504286ef92');
INSERT INTO `employee` VALUES ('2', '一个xiao1', '2019-10-31 16:00:00', '18819522037', '303158131@qq.com', null, '1', '1', '123456');
INSERT INTO `employee` VALUES ('3', '一个xiao', '2019-11-22 16:00:00', '18819522037', '303158131@qq.com', '0', '1', '1', '123456');
INSERT INTO `employee` VALUES ('4', '一个xiao2', '2019-11-17 16:00:00', '18819522037', 'zhengqi@qq.com', '0', '0', '1', '123456');
INSERT INTO `employee` VALUES ('5', '一个xiao', '2019-11-07 16:00:00', '18819522037', 'chengyaojin@qq.com', '1', null, null, '123456');
INSERT INTO `employee` VALUES ('6', '的撒的撒', null, null, null, '0', null, null, null);
INSERT INTO `employee` VALUES ('7', '恶趣味无其他与', null, null, null, '1', null, null, null);
INSERT INTO `employee` VALUES ('8', '发的广泛地人', '2019-11-11 16:00:00', '18819522037', 'zhengqi@qq.com', '0', '1', '1', null);
INSERT INTO `employee` VALUES ('9', '热热完成', null, null, null, '0', null, null, null);
INSERT INTO `employee` VALUES ('10', '萨达群翁无', null, null, null, '1', null, null, null);
INSERT INTO `employee` VALUES ('11', '头痛药', null, null, null, '1', null, null, null);
INSERT INTO `employee` VALUES ('12', '和规范化股份', null, null, null, null, null, null, null);
INSERT INTO `employee` VALUES ('13', '的死皮程序', null, null, null, '1', null, null, null);
INSERT INTO `employee` VALUES ('14', '规划覅偶匹配表云', null, null, null, null, null, null, null);
INSERT INTO `employee` VALUES ('15', 'Vic剖哦', null, null, null, null, null, null, null);
INSERT INTO `employee` VALUES ('16', '的撒的撒多', null, null, null, null, null, null, null);
INSERT INTO `employee` VALUES ('17', '部分过一会', null, null, null, null, null, null, null);
INSERT INTO `employee` VALUES ('18', '打野热一热', null, null, null, null, null, '3', null);
INSERT INTO `employee` VALUES ('19', '胡增明', '2019-11-13 16:00:00', '18819522037', '303158131@qq.com', '0', '0', '2', '123456');
INSERT INTO `employee` VALUES ('20', 'admin8888', '2019-11-25 16:00:00', '18819522037', '303158131@qq.com', '0', '0', '2', '123456');
INSERT INTO `employee` VALUES ('21', '胡增明', '2019-11-24 16:00:00', '18819522037', '303158131@qq.com', '0', '1', '1', '123456');
INSERT INTO `employee` VALUES ('22', 'admin1', null, null, null, null, null, null, '1234');
INSERT INTO `employee` VALUES ('23', 'tyh', '2019-11-07 16:00:00', '18819522037', '303158131@qq.com', '1', '0', '1', '41d0d33928f7d754e868dd72c787dfa2');
INSERT INTO `employee` VALUES ('24', 'admin', null, null, null, '0', '1', '1', '539b31f986eadbeffcbaa791dc93e278');
INSERT INTO `employee` VALUES ('25', 'admin666', '2019-11-23 16:00:00', '1.8819522037E10', '303158131@qq.com', null, null, null, null);
INSERT INTO `employee` VALUES ('26', 'test', '2019-11-23 16:00:00', '18819522037', '303158131@qq.com', '1', '0', '2', 'b1af7050817903e86aeac673376a0c96');
INSERT INTO `employee` VALUES ('27', 'test2', '2019-11-17 16:00:00', '18819522037', '303158131@qq.com', '1', '0', '2', 'edc875c95dfb283f24ec2ad438a3912a');
INSERT INTO `employee` VALUES ('28', '一个xiao222', '2019-11-29 16:00:00', '18819522037', '303158131@qq.com', '1', '0', '1', '0dc851731ecdc92ce17778f78667cc86');
INSERT INTO `employee` VALUES ('30', 'scott1232131', null, '', '', '1', null, null, 'e09294ee13bfaf728c62dd7b70787875');
INSERT INTO `employee` VALUES ('31', 'scott12321321321', '2019-11-29 16:00:00', '18819522037', 'chengyaojin@qq.com', '1', '0', '1', 'fbd6661d9adbe6f1da5c92c62c2f3d42');
INSERT INTO `employee` VALUES ('32', 'scotttest', '2019-11-29 16:00:00', '13548784551', 'chengyaojin@qq.com', '1', '1', '2', 'a35d6b1e441cbb7f1dae917355a08d61');
INSERT INTO `employee` VALUES ('33', 'scottz', null, '18819522037', '303158131@qq.com', '1', '0', '2', '7cb80d4fc2016b12b9846c70a5d661f4');
INSERT INTO `employee` VALUES ('34', '发的广泛地人sd', '2019-11-29 16:00:00', '13548784551', 'zhengqi@qq.com', '1', '0', '2', '5bf6f20637608f8cbe8734400b97066c');
INSERT INTO `employee` VALUES ('35', 'admin66666', '2019-11-23 16:00:00', '1.8819522037E10', '303158131@qq.com', null, null, null, null);
INSERT INTO `employee` VALUES ('36', 'aaa', '2019-11-23 16:00:00', '18819522037', '303158131@qq.com', null, null, null, null);
INSERT INTO `employee` VALUES ('37', 'test', '2019-11-25 16:00:00', '18819522037', 'zhengqi@qq.com', '0', '1', '1', '711674bc0b99ea1d3e7ab6e9f4166512');
INSERT INTO `employee` VALUES ('38', 'aaa', '2019-11-23 16:00:00', '18819522037', '303158131@qq.com', null, null, null, null);
INSERT INTO `employee` VALUES ('39', 'sss', '2019-11-23 16:00:00', '13745458784', '303158131@qq.com', null, null, null, null);
INSERT INTO `employee` VALUES ('40', 'jonathanlee', '2020-07-07 16:00:00', '18854511247', 'leadertangbn@gmail.com', '1', '1', '1', '57a89db8fd00a230f2d23b892cc78af1');

-- ----------------------------
-- Table structure for employee_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `employee_role_rel`;
CREATE TABLE `employee_role_rel` (
  `eid` bigint(20) NOT NULL,
  `rid` bigint(255) NOT NULL,
  PRIMARY KEY (`eid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `employee_role_rel_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`),
  CONSTRAINT `employee_role_rel_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role_rel
-- ----------------------------
INSERT INTO `employee_role_rel` VALUES ('21', '1');
INSERT INTO `employee_role_rel` VALUES ('28', '1');
INSERT INTO `employee_role_rel` VALUES ('37', '1');
INSERT INTO `employee_role_rel` VALUES ('40', '1');
INSERT INTO `employee_role_rel` VALUES ('1', '2');
INSERT INTO `employee_role_rel` VALUES ('20', '2');
INSERT INTO `employee_role_rel` VALUES ('21', '2');
INSERT INTO `employee_role_rel` VALUES ('26', '2');
INSERT INTO `employee_role_rel` VALUES ('33', '2');
INSERT INTO `employee_role_rel` VALUES ('37', '2');
INSERT INTO `employee_role_rel` VALUES ('21', '3');
INSERT INTO `employee_role_rel` VALUES ('23', '3');
INSERT INTO `employee_role_rel` VALUES ('27', '3');
INSERT INTO `employee_role_rel` VALUES ('31', '3');
INSERT INTO `employee_role_rel` VALUES ('32', '3');
INSERT INTO `employee_role_rel` VALUES ('34', '3');
INSERT INTO `employee_role_rel` VALUES ('37', '3');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '员工管理', '/employee', '5', '4');
INSERT INTO `menu` VALUES ('3', '角色权限管理', '/role', '5', '5');
INSERT INTO `menu` VALUES ('5', '系统管理', '', null, null);
INSERT INTO `menu` VALUES ('6', '菜单管理', '/menu', '5', '6');
INSERT INTO `menu` VALUES ('16', '系统管理2', '', null, null);
INSERT INTO `menu` VALUES ('17', 'aaa', '', '16', null);
INSERT INTO `menu` VALUES ('18', 'sss', '', '17', null);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) DEFAULT NULL,
  `presource` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '员工添加', 'employee:add');
INSERT INTO `permission` VALUES ('2', '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES ('3', '员工编辑', 'employee:edit');
INSERT INTO `permission` VALUES ('4', '员工主页', 'employee:index');
INSERT INTO `permission` VALUES ('5', '角色权限主页', 'role:index');
INSERT INTO `permission` VALUES ('6', '菜单主页', 'menu:index');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rnum` varchar(50) DEFAULT NULL,
  `rname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'hr', '人事');
INSERT INTO `role` VALUES ('3', 'manger', '经理');
INSERT INTO `role` VALUES ('5', '1101', '员工主页');

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel` (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`rid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES ('1', '1');
INSERT INTO `role_permission_rel` VALUES ('1', '3');
INSERT INTO `role_permission_rel` VALUES ('2', '4');
INSERT INTO `role_permission_rel` VALUES ('3', '1');
INSERT INTO `role_permission_rel` VALUES ('3', '2');
INSERT INTO `role_permission_rel` VALUES ('3', '3');
INSERT INTO `role_permission_rel` VALUES ('5', '1');
INSERT INTO `role_permission_rel` VALUES ('5', '2');

-- ----------------------------
-- Table structure for systemlog
-- ----------------------------
DROP TABLE IF EXISTS `systemlog`;
CREATE TABLE `systemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `optime` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `function` varchar(255) DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemlog
-- ----------------------------
```