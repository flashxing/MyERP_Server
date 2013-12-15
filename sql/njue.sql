-- phpMyAdmin SQL Dump
-- version 2.11.2.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2013 年 12 月 12 日 00:18
-- 服务器版本: 5.0.45
-- PHP 版本: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- 数据库: `njue`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_category`
--

CREATE TABLE `tb_category` (
  `cate_id` int(11) NOT NULL auto_increment,
  `cate_name` varchar(20) NOT NULL,
  `prefer_id` int(11) NOT NULL,
  PRIMARY KEY  (`cate_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- 导出表中的数据 `tb_category`
--

INSERT INTO `tb_category` (`cate_id`, `cate_name`, `prefer_id`) VALUES
(1, '测试分类1级1', 0),
(2, '测试分类1级2', 0),
(3, '常规类', 0),
(4, 'test', 1),
(5, '测试分类1级2-2级1', 2),
(6, '测试分类1级2-2级1-3级1', 5),
(7, 'test', 5),
(8, 'test2', 2),
(9, 'test4', 5),
(10, '天花板', 9),
(11, 'test', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_customer`
--

CREATE TABLE `tb_customer` (
  `id` char(10) NOT NULL,
  `customername` varchar(50) NOT NULL,
  `zip` char(6) default NULL,
  `address` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `connectionperson` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `bank` varchar(50) default NULL,
  `account` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `fax` varchar(20) default NULL,
  `available` int(11) NOT NULL,
  `cate_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_customer`
--

INSERT INTO `tb_customer` (`id`, `customername`, `zip`, `address`, `telephone`, `connectionperson`, `phone`, `bank`, `account`, `email`, `fax`, `available`, `cate_id`) VALUES
('CSKH1', '测试客户1', '', '', '', '', '', '', '', '', '', 0, 4),
('CSKH10', '测试客户10', '', 'sadf', '', '', '', '', '', '', '', 1, 10),
('KH2', '客户2', '', '', '', '', '', '', '', '', '', 0, 4),
('KH3', '客户3', '', '', '', '', '', '', '', '', '', 0, 6),
('KH4', '客户4', '', '', '', '', '', '', '', '', '', 1, 6),
('KH6', '客户6', '', '', '', '', '', '', '', '', '', 1, 6),
('KH7', '客户7', '', '', '', '', '', '', '', '', '', 1, 4);

-- --------------------------------------------------------

--
-- 表的结构 `tb_customer_category`
--

CREATE TABLE `tb_customer_category` (
  `cate_id` int(11) NOT NULL auto_increment,
  `cate_name` varchar(20) NOT NULL,
  `prefer_id` int(11) NOT NULL,
  PRIMARY KEY  (`cate_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 导出表中的数据 `tb_customer_category`
--

INSERT INTO `tb_customer_category` (`cate_id`, `cate_name`, `prefer_id`) VALUES
(2, '客户分类测试1级2', 0),
(3, '客户分类测试2级2', 2),
(4, '客户分类测试2级2', 3),
(6, '客户分类测试2级3', 3),
(7, 't', 3),
(8, 'test1', 7),
(10, 'test', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_discount`
--

CREATE TABLE `tb_discount` (
  `customer_id` varchar(20) NOT NULL,
  `discount` double NOT NULL default '1',
  PRIMARY KEY  (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_discount`
--

INSERT INTO `tb_discount` (`customer_id`, `discount`) VALUES
('CSKH1', 0.9),
('KH2', 0.85),
('KH3', 0.87),
('KH4', 1),
('KH6', 0.99),
('KH7', 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_giftin`
--

CREATE TABLE `tb_giftin` (
  `id` char(20) NOT NULL,
  `receiver` char(15) default NULL,
  `sh_id` int(11) NOT NULL,
  `inporttime` datetime NOT NULL,
  `operateperson` char(10) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double default NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_giftin`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_giftout`
--

CREATE TABLE `tb_giftout` (
  `id` char(20) NOT NULL,
  `receiver` char(15) default NULL,
  `sh_id` int(11) NOT NULL,
  `inporttime` datetime NOT NULL,
  `operateperson` char(10) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double default NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_giftout`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_goods`
--

CREATE TABLE `tb_goods` (
  `id` char(32) NOT NULL,
  `goodsname` varchar(50) NOT NULL,
  `produceplace` varchar(50) default NULL,
  `size` varchar(50) default NULL,
  `package` varchar(50) default NULL,
  `productcode` varchar(20) default NULL,
  `promitcode` varchar(20) default NULL,
  `description` varchar(50) default NULL,
  `price` double NOT NULL,
  `providerid` char(10) NOT NULL,
  `cateid` int(11) NOT NULL,
  `last_sale_price` double default '0',
  `last_stock_price` double default '0',
  `goods_num` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_goods`
--

INSERT INTO `tb_goods` (`id`, `goodsname`, `produceplace`, `size`, `package`, `productcode`, `promitcode`, `description`, `price`, `providerid`, `cateid`, `last_sale_price`, `last_stock_price`, `goods_num`) VALUES
('10d8f0df7a5ae74c626af4860089d9fd', '商品2', '', '', '', 'SP2', '', '1.4m', 12, '', 4, 10.2, 120, 48),
('471d87ab051696346625a5903284a87c', '商品5', '', '', '', 'SP5', '', '1m', 11, '', 3, 11, 1, 2),
('4c477c3ee49682dab11510e8e72fccdd', '商品1', '', '', '', 'SP1', '', '1m', 15, '', 6, 15, 0, 15),
('5d1b5c5d28bb956addd91d3c39428086', '商品4', '', '', '', 'SP4', '', '21m', 12.1, '', 3, 12, 101, 196),
('a196d45f7d9b802b87e3cc1d4b2e1f05', '商品8', '', '', '', 'SP8', '', '1m', 11.1, '', 8, 0, 0, 0),
('a6dfe14e28584ab9e1c4b5c16a244041', '商品11', '', '', '', 'SP11', '', '1m', 11.1, '', 10, 0, 12.5, 100),
('b91c80671861a851f8c9f0ae1c1d51ec', '商品3', '', '', '', 'SP3', '', '1.1m', 111, '', 6, 0, 13, 200);

-- --------------------------------------------------------

--
-- 表的结构 `tb_goods_discount`
--

CREATE TABLE `tb_goods_discount` (
  `customer_id` varchar(20) NOT NULL,
  `goodsid` varchar(32) NOT NULL,
  `discount` double NOT NULL default '1',
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_goods_discount`
--

INSERT INTO `tb_goods_discount` (`customer_id`, `goodsid`, `discount`) VALUES
('CSKH1', '4c477c3ee49682dab11510e8e72fccdd', 0.87),
('KH2', 'b91c80671861a851f8c9f0ae1c1d51ec', 0.9),
('KH3', '10d8f0df7a5ae74c626af4860089d9fd', 0.89);

-- --------------------------------------------------------

--
-- 表的结构 `tb_goods_item`
--

CREATE TABLE `tb_goods_item` (
  `port_id` char(20) NOT NULL,
  `goods_id` char(32) NOT NULL,
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `comment` varchar(100) default NULL,
  PRIMARY KEY  (`port_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_goods_item`
--

INSERT INTO `tb_goods_item` (`port_id`, `goods_id`, `id`, `number`, `unit_price`, `comment`) VALUES
('PI20131207191904', '10d8f0df7a5ae74c626af4860089d9fd', 0, 10, 120, '120'),
('PI20131207195850', '5d1b5c5d28bb956addd91d3c39428086', 0, 100, 12, '12'),
('PI20131207195850', '5d1b5c5d28bb956addd91d3c39428086', 1, 100, 11, '11'),
('PI20131207200137', '471d87ab051696346625a5903284a87c', 0, 10, 12, '12'),
('PI20131208235936', '471d87ab051696346625a5903284a87c', 0, 1, 101, '101'),
('PI20131209000600', '471d87ab051696346625a5903284a87c', 0, 1, 12, '12'),
('PI20131209001255', '471d87ab051696346625a5903284a87c', 0, 1, 121, '121'),
('PI20131209001425', '5d1b5c5d28bb956addd91d3c39428086', 0, 1, 101, '101'),
('PI20131209111059', '471d87ab051696346625a5903284a87c', 0, 1, 10, '10'),
('PI20131209111059', '471d87ab051696346625a5903284a87c', 1, 1, 12, '12'),
('PI20131211004215', '471d87ab051696346625a5903284a87c', 0, 1, 12, '12'),
('PI20131211004215', '471d87ab051696346625a5903284a87c', 1, 1, 11, '11'),
('PI20131211004415', '471d87ab051696346625a5903284a87c', 0, 1, 1, '1'),
('PI20131211004415', '471d87ab051696346625a5903284a87c', 1, 1, 1, '1'),
('PS20131208221828', '10d8f0df7a5ae74c626af4860089d9fd', 0, 10, 10.1, '10.1');

-- --------------------------------------------------------

--
-- 表的结构 `tb_income`
--

CREATE TABLE `tb_income` (
  `id` char(20) NOT NULL,
  `receiver` char(15) default NULL,
  `sh_id` int(11) NOT NULL,
  `inporttime` datetime NOT NULL,
  `operateperson` char(10) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double default NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_income`
--

INSERT INTO `tb_income` (`id`, `receiver`, `sh_id`, `inporttime`, `operateperson`, `number`, `price`, `comment`, `goodsid`) VALUES
('IC20131112225508', NULL, 1, '2013-11-12 22:55:08', 'test', 20, 0, '', '4c477c3ee49682dab11510e8e72fccdd'),
('IC20131112225557', NULL, 1, '2013-11-12 22:55:57', 'test', 15, 0, '', '4c477c3ee49682dab11510e8e72fccdd');

-- --------------------------------------------------------

--
-- 表的结构 `tb_inport`
--

CREATE TABLE `tb_inport` (
  `id` char(20) NOT NULL,
  `customer_id` char(10) NOT NULL,
  `sh_id` int(11) NOT NULL,
  `inporttime` datetime NOT NULL,
  `operateperson` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `price` float NOT NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(32) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `goodsid` (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_inport`
--

INSERT INTO `tb_inport` (`id`, `customer_id`, `sh_id`, `inporttime`, `operateperson`, `number`, `price`, `comment`, `goodsid`) VALUES
('PI20131021002204', '0', 1, '2013-10-21 00:22:04', 'admin', 40, 12, '', '10d8f0df7a5ae74c626af4860089d9fd'),
('PI20131025165606', '0', 1, '2013-10-25 16:56:06', 'admin', 20, 13.3, '', '10d8f0df7a5ae74c626af4860089d9fd'),
('PI20131106212043', '0', 2, '2013-11-06 21:20:43', 'admin', 300, 13, '', 'b91c80671861a851f8c9f0ae1c1d51ec'),
('PI20131201195024', '0', 1, '2013-12-01 19:50:24', 'test', 50, 11, '', '5d1b5c5d28bb956addd91d3c39428086'),
('PI20131204110756', '0', 1, '2013-12-04 11:07:56', 'test', 100, 12.5, '', 'a6dfe14e28584ab9e1c4b5c16a244041'),
('PI20131207185631', 'KH7', 1, '2013-12-07 18:56:31', 'test', 0, 0, '', ''),
('PI20131207185953', 'KH2', 1, '2013-12-07 18:59:53', 'test', 0, 0, '', ''),
('PI20131207190326', 'CSKH1', 1, '2013-12-07 19:03:26', 'test', 0, 0, '', ''),
('PI20131207191904', 'KH2', 1, '2013-12-07 19:19:04', 'test', 0, 0, '', ''),
('PI20131207195850', 'KH6', 1, '2013-12-07 19:58:50', 'test', 0, 2300, '', ''),
('PI20131207200137', 'KH2', 1, '2013-12-07 20:01:37', 'test', 0, 120, '', ''),
('PI20131208235936', 'CSKH10', 1, '2013-12-08 23:59:36', 'test', 0, 101, '', ''),
('PI20131209000542', 'CSKH10', 1, '2013-12-09 00:05:42', 'test', 0, 102, '', ''),
('PI20131209000600', 'CSKH10', 1, '2013-12-09 00:06:00', 'test', 0, 12, '', ''),
('PI20131209001255', 'CSKH10', 1, '2013-12-09 00:12:55', 'test', 0, 121, '', ''),
('PI20131209001425', 'CSKH10', 1, '2013-12-09 00:14:25', 'test', 0, 101, '', ''),
('PI20131209111059', 'CSKH10', 1, '2013-12-09 11:10:59', 'test', 0, 22, '', ''),
('PI20131211004215', 'CSKH10', 1, '2013-12-11 00:42:15', 'test', 0, 23, '', ''),
('PI20131211004415', 'CSKH10', 1, '2013-12-11 00:44:15', 'test', 0, 2, '', '');

-- --------------------------------------------------------

--
-- 表的结构 `tb_module`
--

CREATE TABLE `tb_module` (
  `module_id` int(11) NOT NULL auto_increment,
  `module_name` char(10) NOT NULL,
  PRIMARY KEY  (`module_id`),
  UNIQUE KEY `module_name` (`module_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- 导出表中的数据 `tb_module`
--

INSERT INTO `tb_module` (`module_id`, `module_name`) VALUES
(4, '基础信息管理'),
(6, '库存管理'),
(5, '查询管理'),
(1, '系统管理'),
(7, '统计报表'),
(8, '财务管理'),
(3, '进货管理'),
(2, '销售管理');

-- --------------------------------------------------------

--
-- 表的结构 `tb_operator`
--

CREATE TABLE `tb_operator` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `power` char(10) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_operator`
--

INSERT INTO `tb_operator` (`username`, `password`, `name`, `power`) VALUES
('admin', '123', '张三', '管理员');

-- --------------------------------------------------------

--
-- 表的结构 `tb_outcome`
--

CREATE TABLE `tb_outcome` (
  `id` char(20) NOT NULL,
  `receiver` char(15) default NULL,
  `sh_id` int(11) NOT NULL,
  `inporttime` datetime NOT NULL,
  `operateperson` char(10) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double default NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_outcome`
--

INSERT INTO `tb_outcome` (`id`, `receiver`, `sh_id`, `inporttime`, `operateperson`, `number`, `price`, `comment`, `goodsid`) VALUES
('OC20131112223531', NULL, 2, '2013-11-12 22:35:31', 'test', 20, 0, '', 'b91c80671861a851f8c9f0ae1c1d51ec'),
('OC20131112224410', NULL, 2, '2013-11-12 22:44:10', 'test', 20, 0, '', 'b91c80671861a851f8c9f0ae1c1d51ec'),
('OC20131112224444', NULL, 2, '2013-11-12 22:44:44', 'test', 20, 0, '', 'b91c80671861a851f8c9f0ae1c1d51ec'),
('OC20131112224526', NULL, 2, '2013-11-12 22:45:26', 'test', 20, 0, '', 'b91c80671861a851f8c9f0ae1c1d51ec'),
('OC20131112224707', NULL, 2, '2013-11-12 22:47:07', 'test', 20, 0, '', 'b91c80671861a851f8c9f0ae1c1d51ec');

-- --------------------------------------------------------

--
-- 表的结构 `tb_outport`
--

CREATE TABLE `tb_outport` (
  `id` char(20) NOT NULL,
  `customer_id` char(10) NOT NULL,
  `sh_id` int(11) NOT NULL,
  `outporttime` datetime NOT NULL,
  `operateperson` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `price` float NOT NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `goodsid` (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_outport`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_permission`
--

CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL auto_increment,
  `module` char(10) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

--
-- 导出表中的数据 `tb_permission`
--

INSERT INTO `tb_permission` (`id`, `module`, `roleid`) VALUES
(16, '销售管理', 4),
(17, '进货管理', 4),
(19, '销售管理', 2),
(20, '系统管理', 2),
(21, '进货管理', 2),
(27, '财务管理', 1),
(28, '销售管理', 1),
(29, '库存管理', 1),
(30, '统计报表', 1),
(31, '系统管理', 1),
(32, '基础信息管理', 1),
(33, '查询管理', 1),
(34, '进货管理', 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_provider`
--

CREATE TABLE `tb_provider` (
  `id` char(10) NOT NULL,
  `providername` varchar(50) NOT NULL,
  `zip` char(6) default NULL,
  `address` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `connectionperson` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `bank` varchar(50) default NULL,
  `account` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `fax` varchar(20) default NULL,
  `available` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_provider`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_receiptin`
--

CREATE TABLE `tb_receiptin` (
  `id` char(20) NOT NULL,
  `customer_id` char(10) NOT NULL,
  `money` double NOT NULL,
  `time` datetime NOT NULL,
  `operater` char(15) NOT NULL,
  `comment` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_receiptin`
--

INSERT INTO `tb_receiptin` (`id`, `customer_id`, `money`, `time`, `operater`, `comment`) VALUES
('receiptId2', '123', 123, '2013-11-12 22:55:08', 'ope', ''),
('RI20131211002041', 'CSKH10', 100, '2013-12-11 00:20:41', 'test', ''),
('RI20131211002105', 'CSKH10', 50, '2013-12-11 00:21:05', 'test', ''),
('RI20131211003457', 'CSKH10', 12, '2013-12-11 00:34:57', 'test', ''),
('RI20131211003541', 'CSKH10', 2, '2013-12-11 00:35:41', 'test', ''),
('RI20131211003928', 'CSKH10', 13, '2013-12-11 00:39:28', 'test', ''),
('RI20131211004535', 'CSKH10', 2, '2013-12-11 00:45:35', 'test', ''),
('RI20131211004603', 'CSKH10', 1, '2013-12-11 00:46:03', 'test', '');

-- --------------------------------------------------------

--
-- 表的结构 `tb_receiptout`
--

CREATE TABLE `tb_receiptout` (
  `id` char(20) NOT NULL,
  `customer_id` char(15) NOT NULL,
  `money` double NOT NULL,
  `time` datetime NOT NULL,
  `operater` char(15) NOT NULL,
  `comment` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_receiptout`
--

INSERT INTO `tb_receiptout` (`id`, `customer_id`, `money`, `time`, `operater`, `comment`) VALUES
('RO20131211002505', 'CSKH10', 22, '2013-12-11 00:25:05', 'test', ''),
('RO20131211002857', 'CSKH10', 12, '2013-12-11 00:28:57', 'test', ''),
('RO20131211003257', 'CSKH10', 25, '2013-12-11 00:32:57', 'test', ''),
('RO20131211004626', 'CSKH10', 2, '2013-12-11 00:46:26', 'test', '');

-- --------------------------------------------------------

--
-- 表的结构 `tb_receipt_item`
--

CREATE TABLE `tb_receipt_item` (
  `item` char(10) NOT NULL,
  PRIMARY KEY  (`item`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_receipt_item`
--

INSERT INTO `tb_receipt_item` (`item`) VALUES
('账款条目1'),
('账款条目2'),
('账款条目3');

-- --------------------------------------------------------

--
-- 表的结构 `tb_receipt_item_list`
--

CREATE TABLE `tb_receipt_item_list` (
  `id` char(30) NOT NULL,
  `receipt_id` char(20) NOT NULL,
  `receipt_item` char(15) NOT NULL,
  `money` double NOT NULL,
  `comment` varchar(100) default NULL,
  PRIMARY KEY  (`id`,`receipt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_receipt_item_list`
--

INSERT INTO `tb_receipt_item_list` (`id`, `receipt_id`, `receipt_item`, `money`, `comment`) VALUES
('0', 'receiptId2', 'receiptItem', 11, 'comment'),
('0', 'RI20131211002041', '账款条目2', 100, NULL),
('0', 'RI20131211002105', '账款条目1', 50, NULL),
('0', 'RI20131211003457', '账款条目2', 12, NULL),
('0', 'RI20131211003541', '账款条目2', 1, NULL),
('0', 'RI20131211003928', '账款条目2', 12, NULL),
('0', 'RI20131211004535', '账款条目2', 1, NULL),
('0', 'RI20131211004603', '账款条目3', 1, NULL),
('0', 'RO20131211002505', '账款条目2', 12, NULL),
('0', 'RO20131211002857', '账款条目1', 12, NULL),
('0', 'RO20131211003257', '账款条目3', 10, NULL),
('0', 'RO20131211004626', '账款条目2', 1, NULL),
('1', 'RI20131211003541', '账款条目2', 1, NULL),
('1', 'RI20131211003928', '账款条目2', 1, NULL),
('1', 'RI20131211004535', '账款条目2', 1, NULL),
('1', 'RO20131211002505', '账款条目2', 10, NULL),
('1', 'RO20131211003257', '账款条目3', 15, NULL),
('1', 'RO20131211004626', '账款条目2', 1, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 导出表中的数据 `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(1, '测试工程师'),
(2, '测试角色1'),
(4, '测试角色2');

-- --------------------------------------------------------

--
-- 表的结构 `tb_sales`
--

CREATE TABLE `tb_sales` (
  `id` char(20) NOT NULL,
  `customerid` char(10) NOT NULL,
  `discount` double NOT NULL,
  `time` datetime NOT NULL,
  `operateperson` char(50) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double NOT NULL,
  `comment` char(100) default NULL,
  `goodsid` char(32) NOT NULL,
  `storehouseid` int(11) NOT NULL,
  `money` double NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `goodsid` (`goodsid`),
  KEY `customerid` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_sales`
--

INSERT INTO `tb_sales` (`id`, `customerid`, `discount`, `time`, `operateperson`, `number`, `price`, `comment`, `goodsid`, `storehouseid`, `money`) VALUES
('PS20131106211507', 'KH2', 0.85, '2013-11-06 21:15:07', 'admin', 21, 10.2, '', '10d8f0df7a5ae74c626af4860089d9fd', 1, 214.2),
('PS20131106211658', 'KH2', 0.85, '2013-11-06 21:16:58', 'admin', 1, 10.2, '', '10d8f0df7a5ae74c626af4860089d9fd', 1, 10.2),
('PS20131201193239', 'KH4', 1, '2013-12-01 19:32:39', 'test', 20, 15, '', '4c477c3ee49682dab11510e8e72fccdd', 1, 300),
('PS20131201195041', 'KH6', 0.99, '2013-12-01 19:50:41', 'test', 20, 11.98, '', '5d1b5c5d28bb956addd91d3c39428086', 1, 239.6),
('PS20131201200215', 'KH2', 0.85, '2013-12-01 20:02:15', 'test', 5, 10.28, '', '5d1b5c5d28bb956addd91d3c39428086', 1, 51.4),
('PS20131201200233', 'KH2', 0.85, '2013-12-01 20:02:33', 'test', 5, 10.28, '', '5d1b5c5d28bb956addd91d3c39428086', 1, 51.4),
('PS20131208221828', 'CSKH10', 0, '2013-12-08 22:18:28', 'test', 0, 0, '', '', 2, 0),
('PS20131209110539', 'CSKH10', 0, '2013-12-09 11:05:39', 'test', 0, 0, '', '', 1, 0),
('PS20131209110558', 'CSKH10', 0, '2013-12-09 11:05:58', 'test', 0, 0, '', '', 1, 0),
('PS20131209110649', 'CSKH10', 0, '2013-12-09 11:06:49', 'test', 0, 0, '', '', 1, 0),
('PS20131209110739', 'CSKH10', 0, '2013-12-09 11:07:39', 'test', 0, 0, '', '', 1, 0),
('PS20131209110857', 'CSKH10', 0, '2013-12-09 11:08:57', 'test', 0, 0, '', '', 1, 0),
('PS20131209111425', 'CSKH10', 0, '2013-12-09 11:14:25', 'test', 0, 200, '', '', 1, 0),
('PS20131211004057', 'CSKH10', 0, '2013-12-11 00:40:57', 'test', 0, 230, '', '', 1, 0),
('PS20131211004250', 'CSKH10', 0, '2013-12-11 00:42:50', 'test', 0, 22, '', '', 1, 0),
('PS20131211004449', 'CSKH10', 0, '2013-12-11 00:44:49', 'test', 0, 22, '', '', 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_salesback`
--

CREATE TABLE `tb_salesback` (
  `id` char(20) NOT NULL,
  `customerid` char(10) NOT NULL,
  `discount` double NOT NULL,
  `time` datetime NOT NULL,
  `operateperson` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `price` float NOT NULL,
  `comment` varchar(100) default NULL,
  `goodsid` char(10) NOT NULL,
  `storehouseid` int(11) NOT NULL,
  `money` double NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `customerid` (`customerid`),
  KEY `goodsid` (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_salesback`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_sales_goods_item`
--

CREATE TABLE `tb_sales_goods_item` (
  `port_id` char(20) NOT NULL,
  `id` int(11) NOT NULL,
  `goods_id` char(32) NOT NULL,
  `number` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `comment` varchar(20) default NULL,
  PRIMARY KEY  (`port_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_sales_goods_item`
--

INSERT INTO `tb_sales_goods_item` (`port_id`, `id`, `goods_id`, `number`, `unit_price`, `comment`) VALUES
('PS20131209110558', 0, '5d1b5c5d28bb956addd91d3c39428086', 12, 10, '10'),
('PS20131209110649', 0, '471d87ab051696346625a5903284a87c', 1, 12, '12'),
('PS20131209110739', 0, '5d1b5c5d28bb956addd91d3c39428086', 1, 10, '10'),
('PS20131209110857', 0, '5d1b5c5d28bb956addd91d3c39428086', 1, 10, '10'),
('PS20131209110857', 1, '5d1b5c5d28bb956addd91d3c39428086', 2, 12, '12'),
('PS20131209111425', 0, '471d87ab051696346625a5903284a87c', 1, 100, '100'),
('PS20131209111425', 1, '471d87ab051696346625a5903284a87c', 1, 100, '100'),
('PS20131211004057', 0, '471d87ab051696346625a5903284a87c', 10, 12, '12'),
('PS20131211004057', 1, '471d87ab051696346625a5903284a87c', 10, 11, '11'),
('PS20131211004250', 0, '471d87ab051696346625a5903284a87c', 1, 11, '11'),
('PS20131211004250', 1, '471d87ab051696346625a5903284a87c', 1, 11, '11'),
('PS20131211004449', 0, '471d87ab051696346625a5903284a87c', 1, 11, '11'),
('PS20131211004449', 1, '471d87ab051696346625a5903284a87c', 1, 11, '11');

-- --------------------------------------------------------

--
-- 表的结构 `tb_stock`
--

CREATE TABLE `tb_stock` (
  `id` int(11) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `sh_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `goods_id` (`goods_id`,`sh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_stock`
--

INSERT INTO `tb_stock` (`id`, `goods_id`, `number`, `sh_id`) VALUES
(1, '10d8f0df7a5ae74c626af4860089d9fd', 48, 1),
(2, 'b91c80671861a851f8c9f0ae1c1d51ec', 200, 2),
(3, '4c477c3ee49682dab11510e8e72fccdd', 15, 1),
(7, '5d1b5c5d28bb956addd91d3c39428086', 196, 1),
(8, 'a6dfe14e28584ab9e1c4b5c16a244041', 100, 1),
(9, '471d87ab051696346625a5903284a87c', 2, 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_storagecheck`
--

CREATE TABLE `tb_storagecheck` (
  `id` int(11) NOT NULL auto_increment,
  `goodsid` char(10) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `goodsid` (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 导出表中的数据 `tb_storagecheck`
--


-- --------------------------------------------------------

--
-- 表的结构 `tb_storehouse`
--

CREATE TABLE `tb_storehouse` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `type` varchar(20) default NULL,
  `mnemonics` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 导出表中的数据 `tb_storehouse`
--

INSERT INTO `tb_storehouse` (`id`, `name`, `type`, `mnemonics`) VALUES
(1, '测试仓库1', '直营', 'CSCK1'),
(2, '测试仓库2', '直营', 'CSCK2');

-- --------------------------------------------------------

--
-- 表的结构 `tb_to_give_receive`
--

CREATE TABLE `tb_to_give_receive` (
  `customer_id` char(10) NOT NULL,
  `give` double default '0',
  `receive` double default '0',
  PRIMARY KEY  (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_to_give_receive`
--

INSERT INTO `tb_to_give_receive` (`customer_id`, `give`, `receive`) VALUES
('CSKH10', 83, 64),
('KH2', 120, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 导出表中的数据 `tb_user`
--

INSERT INTO `tb_user` (`id`, `name`, `password`) VALUES
(1, '测试用户1', '123456'),
(2, '测试用户2', '123456'),
(3, 'test', '123456'),
(4, 'test1', '123'),
(5, 'test2', '123'),
(6, 'test3', '123');

-- --------------------------------------------------------

--
-- 表的结构 `tb_user_role`
--

CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- 导出表中的数据 `tb_user_role`
--

INSERT INTO `tb_user_role` (`id`, `userid`, `roleid`) VALUES
(1, 1, 2),
(2, 2, 4),
(3, 2, 1),
(4, 2, 2),
(5, 3, 1);

--
-- 限制导出的表
--

--
-- 限制表 `tb_goods_discount`
--
ALTER TABLE `tb_goods_discount`
  ADD CONSTRAINT `tb_goods_discount_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `tb_discount` (`customer_id`) ON DELETE CASCADE;

--
-- 限制表 `tb_outport`
--
ALTER TABLE `tb_outport`
  ADD CONSTRAINT `tb_outport_ibfk_1` FOREIGN KEY (`goodsid`) REFERENCES `tb_goods` (`id`);

--
-- 限制表 `tb_sales`
--
ALTER TABLE `tb_sales`
  ADD CONSTRAINT `tb_sales_ibfk_3` FOREIGN KEY (`customerid`) REFERENCES `tb_customer` (`id`);

--
-- 限制表 `tb_salesback`
--
ALTER TABLE `tb_salesback`
  ADD CONSTRAINT `tb_salesback_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `tb_customer` (`id`),
  ADD CONSTRAINT `tb_salesback_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `tb_goods` (`id`);

--
-- 限制表 `tb_storagecheck`
--
ALTER TABLE `tb_storagecheck`
  ADD CONSTRAINT `tb_storagecheck_ibfk_1` FOREIGN KEY (`goodsid`) REFERENCES `tb_goods` (`id`);
