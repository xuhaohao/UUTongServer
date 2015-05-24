/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50704
Source Host           : 127.0.0.1:3306
Source Database       : school2

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2015-05-24 22:42:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for assetinfo
-- ----------------------------
DROP TABLE IF EXISTS `assetinfo`;
CREATE TABLE `assetinfo` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `type` varchar(36) NOT NULL,
  `mac` varchar(72) DEFAULT NULL,
  `address` varchar(72) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `backupUrl` varchar(256) DEFAULT NULL,
  `ownershipType` int(1) DEFAULT '0' COMMENT '1校园2班级4个人',
  `ownership` varchar(36) DEFAULT NULL,
  `cover` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_assettype` (`type`),
  CONSTRAINT `fk_assettype` FOREIGN KEY (`type`) REFERENCES `assettype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assetinfo
-- ----------------------------
INSERT INTO `assetinfo` VALUES ('0', '北京市实验幼儿园-校门口', '0', null, '校门口', 'http://121.42.146.235/hls/DHE153E/playlist.m3u8', null, '0', '', '2015-04-17 22:28:43');
INSERT INTO `assetinfo` VALUES ('1', '北京市实验幼儿园-活动室', '0', null, '活动室', 'http://121.42.146.235/hls/DHC2655/playlist.m3u8', null, '0', '', '2015-04-17 22:29:24');
INSERT INTO `assetinfo` VALUES ('2', '北京市实验幼儿园-食堂', '0', null, '食堂', 'http://121.42.146.235/hls/DHCB26F/playlist.m3u8', null, '0', '', '2015-04-17 22:29:54');
INSERT INTO `assetinfo` VALUES ('3', '北京市实验幼儿园-休息室', '0', null, '休息室', 'http://121.42.146.235/hls/DH0B319/playlist.m3u8', null, '0', '', '2015-04-17 22:30:11');

-- ----------------------------
-- Table structure for assettype
-- ----------------------------
DROP TABLE IF EXISTS `assettype`;
CREATE TABLE `assettype` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) DEFAULT NULL,
  `manufacturer` varchar(256) DEFAULT NULL,
  `parameter` varchar(512) DEFAULT NULL,
  `cover` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assettype
-- ----------------------------
INSERT INTO `assettype` VALUES ('0', 'IP摄像头', '广州市xxx公司', '高清摄像头', '2015-04-17 22:25:43');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` varchar(36) NOT NULL,
  `enumState` int(11) DEFAULT NULL,
  `itemIcon` varchar(256) DEFAULT NULL,
  `levelCode` varchar(36) DEFAULT NULL,
  `matchUrl` varchar(256) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `strHint` varchar(256) DEFAULT NULL,
  `strName` varchar(72) DEFAULT NULL,
  `theValue` varchar(72) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `parent` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tplla926m4vw69fuq8n58hjrf` (`parent`),
  CONSTRAINT `FK_tplla926m4vw69fuq8n58hjrf` FOREIGN KEY (`parent`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for childinfo
-- ----------------------------
DROP TABLE IF EXISTS `childinfo`;
CREATE TABLE `childinfo` (
  `id` varchar(36) NOT NULL,
  `name` varchar(18) NOT NULL,
  `nickName` varchar(18) DEFAULT NULL,
  `gender` int(1) NOT NULL DEFAULT '0' COMMENT '0女1男',
  `studentid` varchar(36) DEFAULT NULL,
  `portrait` varchar(36) DEFAULT NULL,
  `linkman1` varchar(11) DEFAULT NULL,
  `linkman2` varchar(11) DEFAULT NULL,
  `oid` varchar(36) DEFAULT NULL,
  `cover` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of childinfo
-- ----------------------------
INSERT INTO `childinfo` VALUES ('0', '付梦蝶', '梦蝶', '0', '511010861504001', null, '13810506706', null, '000000000000000000000000000000000001', '2015-04-17 22:11:59');
INSERT INTO `childinfo` VALUES ('1', '付梦蝶', '梦蝶', '0', '511010861504001', null, '18611188806', null, '000000000000000000000000000000000001', '2015-04-17 22:12:40');
INSERT INTO `childinfo` VALUES ('10', '郭子', null, '1', '511010861504011', null, '18834835401', null, '000000000000000000000000000000000001', '2015-04-29 15:14:12');
INSERT INTO `childinfo` VALUES ('11', '穆婉清', null, '0', '511010861504012', null, '15144215011', null, '000000000000000000000000000000000001', '2015-04-29 15:15:24');
INSERT INTO `childinfo` VALUES ('12', '茹越', null, '1', '511010861504013', null, '15303512427', null, '000000000000000000000000000000000001', '2015-04-29 15:16:20');
INSERT INTO `childinfo` VALUES ('13', '小勇', null, '0', '511010861504017', null, '13327439129', null, '000000000000000000000000000000000001', '2015-05-04 10:47:41');
INSERT INTO `childinfo` VALUES ('14', '梅小小', '小梅', '0', '511010861504014', null, '18734603296', null, '000000000000000000000000000000000001', '2015-05-04 20:00:27');
INSERT INTO `childinfo` VALUES ('15', '付晓语', '晓晓', '0', '511010861504020', null, '13810392478', null, '000000000000000000000000000000000001', '2015-05-04 20:10:13');
INSERT INTO `childinfo` VALUES ('16', '刘乐', '小兵', '1', '511010861504019', null, '18210545675', null, '000000000000000000000000000000000001', '2015-05-04 20:12:46');
INSERT INTO `childinfo` VALUES ('18', '温紫寰', '紫寰', '0', '511010861504018', null, '18910116626', null, '000000000000000000000000000000000001', '2015-05-10 22:35:25');
INSERT INTO `childinfo` VALUES ('2', '崔晨曦', '晨曦', '0', '511010861504003', null, '13910476264', null, '000000000000000000000000000000000001', '2015-04-17 22:13:32');
INSERT INTO `childinfo` VALUES ('3', '徐静蕾', null, '0', '511010861504004', null, '13911035367', null, '000000000000000000000000000000000001', '2015-04-17 22:14:37');
INSERT INTO `childinfo` VALUES ('4', '王中王', null, '1', '511010861504005', null, '18611088815', null, '000000000000000000000000000000000001', '2015-04-17 22:15:26');
INSERT INTO `childinfo` VALUES ('5', '马云', null, '1', '511010861504006', null, '13693160996', null, '000000000000000000000000000000000001', '2015-04-17 22:16:14');
INSERT INTO `childinfo` VALUES ('6', '徐大宝', null, '1', '511010861504007', null, '18612981554', null, '000000000000000000000000000000000001', '2015-04-17 22:17:43');
INSERT INTO `childinfo` VALUES ('7', '渠小艺', null, '0', '511010861504008', null, '13327439129', null, '000000000000000000000000000000000001', '2015-04-29 15:10:52');
INSERT INTO `childinfo` VALUES ('8', '王丽丽', null, '0', '511010861504009', null, '15903463135', null, '000000000000000000000000000000000001', '2015-04-29 15:11:50');
INSERT INTO `childinfo` VALUES ('9', '王风火', null, '1', '511010861504010', null, '18334728672', null, '000000000000000000000000000000000001', '2015-04-29 15:13:38');

-- ----------------------------
-- Table structure for newsdetail
-- ----------------------------
DROP TABLE IF EXISTS `newsdetail`;
CREATE TABLE `newsdetail` (
  `id` varchar(36) NOT NULL,
  `content` varchar(20480) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsdetail
-- ----------------------------

-- ----------------------------
-- Table structure for newsinfo
-- ----------------------------
DROP TABLE IF EXISTS `newsinfo`;
CREATE TABLE `newsinfo` (
  `id` varchar(36) NOT NULL,
  `title` varchar(36) NOT NULL,
  `author` varchar(11) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `cover` datetime NOT NULL,
  `optIn` int(11) DEFAULT '0' COMMENT '点赞数',
  `ownershipType` int(1) NOT NULL,
  `ownership` varchar(36) DEFAULT NULL,
  `image` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_otiwxnodr414meokyp36sw0ti` (`author`),
  CONSTRAINT `FK_otiwxnodr414meokyp36sw0ti` FOREIGN KEY (`author`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsinfo
-- ----------------------------
INSERT INTO `newsinfo` VALUES ('2663808d-4729-478b-95fd-5abc8dc66e5e', '复活节和', '13426422128', null, '得分', '2015-04-29 23:43:46', '0', '2', '000000000000000000000000000000000101', '');
INSERT INTO `newsinfo` VALUES ('32114fdd-1a6e-4ae2-a973-b62c47774dd0', '来自移动端', '13426422128', null, '公告内容', '2015-04-24 17:12:44', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('46c82bbe-b2a7-47e5-969a-5643ebec2169', '13810506706', '13810506706', null, '公告内容', '2015-04-24 17:17:19', '0', '1', '000000000000000001000000000000000000', null);
INSERT INTO `newsinfo` VALUES ('49892b27-cf1e-40f5-854c-a6480a6628c6', '计算机世界', '13426422128', null, '高哈哈镜阿巴斯捉襟见肘标准本职工作', '2015-05-11 22:54:42', '0', '2', '000000000000000000000000000000000101', 'news/ee3878fa-07e7-487a-99aa-d61844e8a2a7/0.png');
INSERT INTO `newsinfo` VALUES ('4d1b69c0-15b3-4bd4-b4bb-76ca4405c9e7', '来自移动端', '13426422128', null, '公告内容', '2015-04-24 17:10:42', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('4efaf128-9640-4ea1-8f41-8765c3422fd5', '徐浩浩发公告', '18612981554', null, '得分BBC x=\n', '2015-05-11 22:57:17', '0', '2', '000000000000000000000000000000000101', 'news/f3824a3a-1a5b-43b4-9b61-27c25f1495e3/0.png');
INSERT INTO `newsinfo` VALUES ('5bc5f128-1f68-4ba7-b3b7-257220b450b6', '凤凰花从', '13426422128', null, '得分', '2015-04-29 23:38:01', '0', '2', '000000000000000000000000000000000101', 'news/4a0547f5-bc83-4645-be6e-edf1e64df9cc/0.png');
INSERT INTO `newsinfo` VALUES ('7dbe33d6-c5cc-477d-97e7-7af6fcd93e92', '估计韩国', '13426422128', null, '得分发挥积极哈哈镜\n', '2015-04-27 23:04:37', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('8a7ae5df-0178-4edd-a410-9b6a415581dd', '户籍科', '13426422128', null, '得分你家', '2015-04-29 23:40:09', '0', '2', '000000000000000000000000000000000101', '');
INSERT INTO `newsinfo` VALUES ('8fba1590-971f-4a0b-8f51-459d6c0f25a5', '根据火车', '13426422128', null, '得分符合过程', '2015-04-29 00:04:03', '0', '2', '000000000000000000000000000000000101', 'news/16167c9b-9021-4342-964f-31d9b3fe6b32/0.png');
INSERT INTO `newsinfo` VALUES ('9ba7741a-0692-4a98-ad89-861b4c3bca85', '头像', '13426422128', null, '得分Jhahshshhxhhxjvhxhhxjdhshdhdhshshsghdhhshdhdhhdhhdhd手机计算机三国杀吸血鬼小户型自己喜欢香港喜欢继续代表性希金斯很喜欢xls进行\n\n', '2015-04-27 22:38:24', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('9d63e2ec-e586-4b37-bf55-5a697c4db8bc', '凤凰花', '13426422128', null, '得分成本好机会看看h GG健康好好看看更何况h GG健康\n', '2015-04-27 22:58:11', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('a829bba7-771d-4fef-94ff-9aa0aaebb2dd', '13810506706', '13810506706', null, '公告内容', '2015-04-24 17:17:42', '0', '1', '000000000000000001000000000000000000', null);
INSERT INTO `newsinfo` VALUES ('b24f734e-6b82-48b1-b24d-ad0bae14e5ff', '法国火车', '13426422128', null, '得分', '2015-04-29 22:57:44', '0', '2', '000000000000000000000000000000000101', 'news/93a14f41-03f8-455a-8bd7-0d0599297d17/0.png');
INSERT INTO `newsinfo` VALUES ('b70e1d2e-fc77-42de-a525-cbf1832c679e', '头像', '13426422128', null, '得分Jhahshshhxhhxjvhxhhxjdhshdhdhshshsghdhhshdhdhhdhhdhd手机计算机三国杀吸血鬼小户型自己喜欢香港喜欢继续代表性希金斯很喜欢xls进', '2015-04-27 22:38:38', '0', '2', '000000000000000000000000000000000101', null);
INSERT INTO `newsinfo` VALUES ('be4a9465-3297-4051-91e2-041b5264d0f0', '凤凰花发', '13426422128', null, '得分', '2015-04-29 23:13:44', '0', '2', '000000000000000000000000000000000101', 'news/d46a63d8-60da-421f-9b4e-8575cab255cf/0.png');
INSERT INTO `newsinfo` VALUES ('c172d509-7f14-4e5b-9545-b8412aa80221', '来自移动端', '18600540876', null, '公告内容', '2015-04-24 17:14:24', '0', '2', '000000000000000000000000000000000001', null);
INSERT INTO `newsinfo` VALUES ('c5257666-e52f-4361-8b74-516ff08740a2', '天后h GG', '13426422128', null, '得分', '2015-04-29 23:45:28', '0', '2', '000000000000000000000000000000000101', 'news/5ea30cd1-cfa4-41e4-89dc-a14406dd549d/0.png');
INSERT INTO `newsinfo` VALUES ('c6528626-a3db-40e2-a044-307205cd3c94', '图片公告', '13426422128', null, '哈哈', '2015-04-29 21:52:23', '0', '2', '000000000000000000000000000000000101', 'news/5c67a87c-f164-42a4-9525-f6c6aecce39e/0.png');
INSERT INTO `newsinfo` VALUES ('d33511b6-b53d-42c5-a8f0-0bc8557cc3be', '来自移动端', '18600540876', null, '公告内容', '2015-04-24 17:15:02', '0', '2', '000000000000000000000000000000000001', null);
INSERT INTO `newsinfo` VALUES ('dd401388-a625-4ea6-b06e-542cb76c44ea', '根据火车', '13426422128', null, '得分', '2015-04-29 22:46:11', '0', '2', '000000000000000000000000000000000101', 'news/5a580624-8e3c-45a3-a95d-f60253f2e801/0.png');

-- ----------------------------
-- Table structure for organizationinfo
-- ----------------------------
DROP TABLE IF EXISTS `organizationinfo`;
CREATE TABLE `organizationinfo` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `nickName` varchar(36) DEFAULT NULL,
  `parentId` varchar(36) DEFAULT NULL,
  `parentName` varchar(36) DEFAULT NULL,
  `location` varchar(256) DEFAULT NULL,
  `hint` varchar(36) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `cover` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ykrswhjjvq03tbpymdq576eh` (`parentId`),
  KEY `FK_2m2uo9p2o2xc0ps0k1i4h5f1i` (`hint`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organizationinfo
-- ----------------------------
INSERT INTO `organizationinfo` VALUES ('000000000000000000000000000000000001', '小班', '小班101', '000000000000000001000000000000000000', '北京市实验幼儿园', '101', '', null, '2015-04-17 22:23:28');
INSERT INTO `organizationinfo` VALUES ('000000000000000000000000000000000101', '大班', '大班102', '000000000000000001000000000000000000', '北京市实验幼儿园', '102', '', null, '2015-04-17 22:24:15');
INSERT INTO `organizationinfo` VALUES ('000000000000000001000000000000000000', '北京市实验幼儿园', '北京市实验幼儿园', null, null, '北京市怀柔区庙城镇赵各庄250号', '北京市创新示范幼儿园', null, '2015-05-06 23:15:13');
INSERT INTO `organizationinfo` VALUES ('000000000000000002000000000000000000', '智联新科媒体部门', '智联新科媒体部门', null, null, null, null, null, '2015-05-07 23:49:49');

-- ----------------------------
-- Table structure for organizationuser
-- ----------------------------
DROP TABLE IF EXISTS `organizationuser`;
CREATE TABLE `organizationuser` (
  `id` varchar(36) NOT NULL,
  `oid` varchar(36) NOT NULL,
  `uid` varchar(11) NOT NULL,
  `identity` int(1) NOT NULL DEFAULT '1' COMMENT '1成员2管理员',
  `cover` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organizationuser
-- ----------------------------
INSERT INTO `organizationuser` VALUES ('13327439129', '000000000000000000000000000000000101', '13327439129', '1', '2015-05-04 10:23:08');
INSERT INTO `organizationuser` VALUES ('13426422128', '000000000000000000000000000000000101', '13426422128', '2', '2015-04-17 21:58:09');
INSERT INTO `organizationuser` VALUES ('13693160996', '000000000000000000000000000000000101', '13693160996', '1', '2015-04-17 22:08:08');
INSERT INTO `organizationuser` VALUES ('13810392478', '000000000000000000000000000000000101', '13810392478', '1', '2015-05-04 20:02:00');
INSERT INTO `organizationuser` VALUES ('13810506706', '000000000000000000000000000000000101', '13810506706', '2', '2015-04-17 22:04:09');
INSERT INTO `organizationuser` VALUES ('13910476264', '000000000000000000000000000000000101', '13910476264', '1', '2015-04-17 22:08:51');
INSERT INTO `organizationuser` VALUES ('13911035367', '000000000000000000000000000000000101', '13911035367', '1', '2015-04-17 22:06:54');
INSERT INTO `organizationuser` VALUES ('13934245759', '000000000000000000000000000000000101', '13934245759', '1', '2015-04-29 12:06:37');
INSERT INTO `organizationuser` VALUES ('15144215011', '000000000000000000000000000000000101', '15144215011', '1', '2015-04-29 12:11:29');
INSERT INTO `organizationuser` VALUES ('15303512427', '000000000000000000000000000000000101', '15303512427', '1', '2015-04-29 12:12:40');
INSERT INTO `organizationuser` VALUES ('15903463135', '000000000000000000000000000000000101', '15903463135', '1', '2015-04-29 12:07:58');
INSERT INTO `organizationuser` VALUES ('18210545675', '000000000000000000000000000000000101', '18210545675', '1', '2015-05-04 20:03:14');
INSERT INTO `organizationuser` VALUES ('18334728672', '000000000000000000000000000000000101', '18334728672', '1', '2015-04-29 12:09:24');
INSERT INTO `organizationuser` VALUES ('18600540876', '000000000000000000000000000000000101', '18600540876', '2', '2015-04-17 21:55:50');
INSERT INTO `organizationuser` VALUES ('18611088815', '000000000000000000000000000000000101', '18611088815', '1', '2015-04-17 22:07:28');
INSERT INTO `organizationuser` VALUES ('18611188806', '000000000000000000000000000000000101', '18611188806', '1', '2015-04-17 22:05:42');
INSERT INTO `organizationuser` VALUES ('18612981554', '000000000000000000000000000000000101', '18612981554', '2', '2015-04-17 22:02:10');
INSERT INTO `organizationuser` VALUES ('18734603296', '000000000000000000000000000000000101', '18734603296', '1', '2015-05-04 19:57:33');
INSERT INTO `organizationuser` VALUES ('18834835401', '000000000000000000000000000000000101', '18834835401', '1', '2015-04-29 12:10:56');
INSERT INTO `organizationuser` VALUES ('18910116626', '000000000000000000000000000000000101', '18910116626', '1', '2015-05-11 22:41:03');

-- ----------------------------
-- Table structure for paraminfo
-- ----------------------------
DROP TABLE IF EXISTS `paraminfo`;
CREATE TABLE `paraminfo` (
  `pk` varchar(36) NOT NULL,
  `strCode` varchar(36) NOT NULL,
  `strHint` varchar(128) DEFAULT NULL,
  `strName` varchar(36) DEFAULT NULL,
  `strValue` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `UK_7ldfv92v5r7ub3tqi5cddsq3t` (`strCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paraminfo
-- ----------------------------

-- ----------------------------
-- Table structure for rightinfo
-- ----------------------------
DROP TABLE IF EXISTS `rightinfo`;
CREATE TABLE `rightinfo` (
  `pk` varchar(36) NOT NULL,
  `strHint` varchar(256) DEFAULT NULL,
  `strName` varchar(72) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rightinfo
-- ----------------------------

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `id` varchar(36) NOT NULL,
  `strName` varchar(72) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------

-- ----------------------------
-- Table structure for rolerightinfo
-- ----------------------------
DROP TABLE IF EXISTS `rolerightinfo`;
CREATE TABLE `rolerightinfo` (
  `id` varchar(36) NOT NULL,
  `fk_authority` varchar(36) DEFAULT NULL,
  `fk_roleInfo` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jilo9usbh1ribbhu06oykfgnc` (`fk_authority`),
  KEY `FK_gwkrcfch8w11fv5q1blbmvtxf` (`fk_roleInfo`),
  CONSTRAINT `FK_gwkrcfch8w11fv5q1blbmvtxf` FOREIGN KEY (`fk_roleInfo`) REFERENCES `roleinfo` (`id`),
  CONSTRAINT `FK_jilo9usbh1ribbhu06oykfgnc` FOREIGN KEY (`fk_authority`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolerightinfo
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` varchar(11) NOT NULL,
  `name` varchar(36) NOT NULL,
  `psd` varchar(32) NOT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `gender` int(1) NOT NULL,
  `enumState` int(1) NOT NULL,
  `portrait` varchar(36) DEFAULT NULL,
  `phone1` varchar(11) NOT NULL,
  `phone2` varchar(11) DEFAULT NULL,
  `email` varchar(56) DEFAULT NULL,
  `registerTime` datetime NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `identity` varchar(16) NOT NULL,
  `logonDevice` varchar(512) DEFAULT NULL,
  `rongToken` varchar(128) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `cover` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_crv103nka3vt7guxna5ipgkk` (`portrait`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('13327439129', '邵奇勇', 'CFCD208495D565EF66E7DFF9F98764DA', null, '1', '0', null, '13327439129', null, null, '2015-05-04 10:22:56', null, '家长', null, null, null, '2015-05-04 10:23:08');
INSERT INTO `userinfo` VALUES ('13426422128', '温雷华', 'CFCD208495D565EF66E7DFF9F98764DA', null, '0', '0', null, '13426422128', null, null, '2015-04-17 21:58:06', null, '教师', '867064012308886;00;+8618612981554;2015-05-11 09:15:24', 'vjEcTDUbEgXVef+CtWYkKmdOpn/59z4L0dPvIU0Yjuf+G45Wi7AAKuy5imB7qKH2Voz7Jj6/YY7pVkwapzLZU2M3+v6agJJS', null, '2015-04-17 21:58:09');
INSERT INTO `userinfo` VALUES ('13693160996', '马光', 'CFCD208495D565EF66E7DFF9F98764DA', '光仔', '1', '0', null, '13693160996', null, null, '2015-04-17 22:08:02', null, '家长', '863990020672091;78;;2015-05-11 13:47:18', 'Px3D1wTVwkAdSJ6407azft6qJD2Ub5DjW78C7GSO0i5vo9Zi2yN17RzdJ7LcPE/mAcplhfOIBKMwwEWPbEKjf+WdzKV9rwyj', null, '2015-04-17 22:08:08');
INSERT INTO `userinfo` VALUES ('13810392478', '付强', 'CFCD208495D565EF66E7DFF9F98764DA', null, '0', '0', null, '13810392478', null, null, '2015-05-04 20:01:51', null, '家长', '867064012308886;00;+8618612981554;2015-05-11 23:10:34', 'EGj6Iqxi5LMyA/cQPGx3IGdOpn/59z4L0dPvIU0Yjuf+G45Wi7AAKo9SCDuBUCS+oRbNGxWSYr5kAxjJpoZDlUezqFHpekIb', null, '2015-05-04 20:02:00');
INSERT INTO `userinfo` VALUES ('13810506706', '付海昆', 'CFCD208495D565EF66E7DFF9F98764DA', '付爷', '1', '0', null, '13810506706', null, null, '2015-04-17 22:04:05', null, '家长', null, null, null, '2015-04-17 22:04:09');
INSERT INTO `userinfo` VALUES ('13910476264', '崔海维', 'CFCD208495D565EF66E7DFF9F98764DA', 'triton', '1', '0', null, '13910476264', null, null, '2015-04-17 22:08:48', null, '家长', null, null, null, '2015-04-17 22:08:51');
INSERT INTO `userinfo` VALUES ('13911035367', '徐玉林', 'CFCD208495D565EF66E7DFF9F98764DA', '美玉如林', '1', '0', null, '13911035367', null, null, '2015-04-17 22:06:48', null, '家长', '860311029775681;01;;2015-05-11 13:58:12', 'l90cFg7iNXSmY7QmNtBORbOorLfOnTOXXjpiYC1VLDwE8Q8MS6q4BXigPxR+9PlVvMc9f8zdJ4YQ6WHGYt3unSWlQRSnMW39', null, '2015-04-17 22:06:54');
INSERT INTO `userinfo` VALUES ('13934245759', '渠艺', 'CFCD208495D565EF66E7DFF9F98764DA', null, '0', '0', null, '13327439129', null, null, '2015-04-29 12:06:50', null, '家长', null, null, null, '2015-04-29 12:06:37');
INSERT INTO `userinfo` VALUES ('15144215011', '穆继通', 'CFCD208495D565EF66E7DFF9F98764DA', '通天法师', '1', '0', null, '15144215011', null, null, '2015-04-29 12:11:26', null, '家长', null, null, null, '2015-04-29 12:11:29');
INSERT INTO `userinfo` VALUES ('15303512427', '茹九霄', 'CFCD208495D565EF66E7DFF9F98764DA', '九尾', '1', '0', null, '15303512427', null, null, '2015-04-29 12:12:45', null, '家长', null, null, null, '2015-04-29 12:12:40');
INSERT INTO `userinfo` VALUES ('15903463135', '王瑞丽', 'CFCD208495D565EF66E7DFF9F98764DA', '小丽', '0', '0', null, '15903463135', null, null, '2015-04-29 12:08:01', null, '家长', null, null, null, '2015-04-29 12:07:58');
INSERT INTO `userinfo` VALUES ('18210545675', '刘兵', 'CFCD208495D565EF66E7DFF9F98764DA', null, '1', '0', null, '18210545675', null, null, '2015-05-04 20:03:05', null, '家长', null, null, null, '2015-05-04 20:03:14');
INSERT INTO `userinfo` VALUES ('18334728672', '王飚', 'CFCD208495D565EF66E7DFF9F98764DA', '飙举电至', '1', '0', null, '18334728672', null, null, '2015-04-29 12:09:21', null, '家长', null, null, null, '2015-04-29 12:09:24');
INSERT INTO `userinfo` VALUES ('18600540876', '温雷霆', 'CFCD208495D565EF66E7DFF9F98764DA', '婷婷', '1', '0', null, '18600540876', null, null, '2015-04-17 21:55:37', null, '家长', '869421018989275;00;;2015-05-11 20:00:29', 'ehsKItVAY0gF5UgFqDs0WLOorLfOnTOXXjpiYC1VLDwE8Q8MS6q4BZmF/7GMSMws25tE1ao3gpXIiOQKrhu1aIKPjNBWxSru', null, '2015-04-17 21:55:50');
INSERT INTO `userinfo` VALUES ('18611088815', '王莹', 'CFCD208495D565EF66E7DFF9F98764DA', null, '1', '0', null, '18611088815', null, null, '2015-04-17 22:07:23', null, '家长', '352683053442858;00;null;2015-05-10 15:33:53', '7hxzNeG/JEdbptkmi5QXa7OorLfOnTOXXjpiYC1VLDwE8Q8MS6q4BWltdFwdr2CU25tE1ao3gpXq2AhcTZx5YopO7jDvRXwh', null, '2015-04-17 22:07:28');
INSERT INTO `userinfo` VALUES ('18611188806', '王晶', 'CFCD208495D565EF66E7DFF9F98764DA', '我不是导演', '0', '0', null, '18611188806', null, null, '2015-04-17 22:05:32', null, '家长', null, null, null, '2015-04-17 22:05:42');
INSERT INTO `userinfo` VALUES ('18612981554', '徐浩浩', 'CFCD208495D565EF66E7DFF9F98764DA', '', '1', '0', null, '18612981554', null, null, '2015-04-17 22:01:42', null, '教师', '867064012308886;00;+8618612981554;2015-05-11 22:56:56', 'qxBXaXOWLZfGP3VpQgun7bOorLfOnTOXXjpiYC1VLDwE8Q8MS6q4BXfypibhd9SuB5LZtWrz+wAOXgWbAA9vM1yV5XqpTInb', null, '2015-04-17 22:02:10');
INSERT INTO `userinfo` VALUES ('18734603296', '梅姐', 'CFCD208495D565EF66E7DFF9F98764DA', '梅姐', '1', '0', null, '18734603296', null, null, '2015-05-04 19:57:22', null, '家长', null, null, null, '2015-05-04 19:57:33');
INSERT INTO `userinfo` VALUES ('18834835401', '郭耀华', 'CFCD208495D565EF66E7DFF9F98764DA', '华仔', '1', '0', null, '18834835401', null, null, '2015-04-29 12:10:52', null, '家长', null, null, null, '2015-04-29 12:10:56');
INSERT INTO `userinfo` VALUES ('18910116626', '王杨', 'CFCD208495D565EF66E7DFF9F98764DA', '温夫人', '0', '0', null, '18910116626', null, null, '2015-05-10 22:34:06', null, '家长', null, null, null, '2015-05-10 22:34:18');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `id` varchar(36) NOT NULL,
  `fk_role` varchar(36) DEFAULT NULL,
  `fk_user` varchar(36) DEFAULT NULL,
  `fk_roleInfo` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kmk63m59h9lrpyvpwwf2tug89` (`fk_role`),
  KEY `FK_130h2ib6bi1g96aqvuqsxtcc9` (`fk_user`),
  KEY `FK_1sr7jclp56g5o0bdmd7fb0dxj` (`fk_roleInfo`),
  CONSTRAINT `FK_130h2ib6bi1g96aqvuqsxtcc9` FOREIGN KEY (`fk_user`) REFERENCES `userinfo` (`id`),
  CONSTRAINT `FK_1sr7jclp56g5o0bdmd7fb0dxj` FOREIGN KEY (`fk_roleInfo`) REFERENCES `roleinfo` (`id`),
  CONSTRAINT `FK_kmk63m59h9lrpyvpwwf2tug89` FOREIGN KEY (`fk_role`) REFERENCES `roleinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------

-- ----------------------------
-- Table structure for versioninfo
-- ----------------------------
DROP TABLE IF EXISTS `versioninfo`;
CREATE TABLE `versioninfo` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `feature` varchar(512) DEFAULT NULL,
  `beta` int(1) NOT NULL DEFAULT '0' COMMENT '0测试1正式',
  `url` varchar(256) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL,
  `cover` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of versioninfo
-- ----------------------------
INSERT INTO `versioninfo` VALUES ('0', '发布版1.1.0510.Night', '1.添加群聊天\\\\n2.地图和voip功能暂不可用', '0', 'http://121.42.146.235:9080/UUTong365/app-debug.apk', 'Android', '2015-04-17 22:43:05');
INSERT INTO `versioninfo` VALUES ('1', '发布版1.1.0511.Night', '1.发布公告的bug', '0', 'http://121.42.146.235:9080/UUTong365/app-debug.apk', 'Android', '2015-05-11 23:27:37');
