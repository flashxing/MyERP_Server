-- phpMyAdmin SQL Dump
-- version 2.11.2.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2013 年 11 月 10 日 08:32
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 导出表中的数据 `tb_category`
--

INSERT INTO `tb_category` (`cate_id`, `cate_name`, `prefer_id`) VALUES
(1, '测试分类1级1', 0),
(2, '测试分类1级2', 0),
(3, '测试分类1级3', 0),
(4, '测试分类1级1-2级', 1),
(5, '测试分类1级2-2级1', 2),
(6, '测试分类1级2-2级1-3级1', 5);

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
('CSKH1', '测试客户1', '', '', '', '', '', '', '', '', '', 0, 3),
('KH2', '客户2', '', '', '', '', '', '', '', '', '', 3, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_customer_category`
--

CREATE TABLE `tb_customer_category` (
  `cate_id` int(11) NOT NULL auto_increment,
  `cate_name` varchar(20) NOT NULL,
  `prefer_id` int(11) NOT NULL,
  PRIMARY KEY  (`cate_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 导出表中的数据 `tb_customer_category`
--

INSERT INTO `tb_customer_category` (`cate_id`, `cate_name`, `prefer_id`) VALUES
(2, '客户分类测试1级2', 0),
(3, '客户分类测试2级1', 2);

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
('KH2', 0.85);

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
('10d8f0df7a5ae74c626af4860089d9fd', '商品2', '', '', '', 'SP2', '', '1.4m', 12, '', 4, 10.2, 13.3, 38),
('4c477c3ee49682dab11510e8e72fccdd', '商品1', '', '', '', 'SP1', '', '1m', 15, '', 6, 0, 0, 0),
('b91c80671861a851f8c9f0ae1c1d51ec', '商品3', '', '', '', 'SP3', '', '1.1m', 111, '', 6, 0, 13, 300);

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
('KH2', 'b91c80671861a851f8c9f0ae1c1d51ec', 0.9);

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


-- --------------------------------------------------------

--
-- 表的结构 `tb_inport`
--

CREATE TABLE `tb_inport` (
  `id` char(20) NOT NULL,
  `customer_id` varchar(10) NOT NULL,
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
('PI20131106212043', '0', 2, '2013-11-06 21:20:43', 'admin', 300, 13, '', 'b91c80671861a851f8c9f0ae1c1d51ec');

-- --------------------------------------------------------

--
-- 表的结构 `tb_module`
--

CREATE TABLE `tb_module` (
  `module_id` int(11) NOT NULL auto_increment,
  `module_name` char(10) NOT NULL,
  PRIMARY KEY  (`module_id`),
  UNIQUE KEY `module_name` (`module_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 导出表中的数据 `tb_module`
--

INSERT INTO `tb_module` (`module_id`, `module_name`) VALUES
(4, '基础信息管理'),
(1, '系统管理'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- 导出表中的数据 `tb_permission`
--

INSERT INTO `tb_permission` (`id`, `module`, `roleid`) VALUES
(16, '销售管理', 4),
(17, '进货管理', 4),
(19, '销售管理', 2),
(20, '系统管理', 2),
(21, '进货管理', 2),
(23, '销售管理', 1),
(24, '系统管理', 1),
(25, '基础信息管理', 1),
(26, '进货管理', 1);

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
  KEY `customerid` (`customerid`),
  KEY `goodsid` (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_sales`
--

INSERT INTO `tb_sales` (`id`, `customerid`, `discount`, `time`, `operateperson`, `number`, `price`, `comment`, `goodsid`, `storehouseid`, `money`) VALUES
('PS20131106211507', 'KH2', 0.85, '2013-11-06 21:15:07', 'admin', 21, 10.2, '', '10d8f0df7a5ae74c626af4860089d9fd', 1, 214.2),
('PS20131106211658', 'KH2', 0.85, '2013-11-06 21:16:58', 'admin', 1, 10.2, '', '10d8f0df7a5ae74c626af4860089d9fd', 1, 10.2);

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
-- 表的结构 `tb_stock`
--

CREATE TABLE `tb_stock` (
  `id` int(11) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `sh_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 导出表中的数据 `tb_stock`
--

INSERT INTO `tb_stock` (`id`, `goods_id`, `number`, `sh_id`) VALUES
(1, '10d8f0df7a5ae74c626af4860089d9fd', 38, 1),
(2, 'b91c80671861a851f8c9f0ae1c1d51ec', 300, 2);

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
-- 表的结构 `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 导出表中的数据 `tb_user`
--

INSERT INTO `tb_user` (`id`, `name`, `password`) VALUES
(1, '测试用户1', '123456'),
(2, '测试用户2', '123456'),
(3, 'test', '123456');

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
  ADD CONSTRAINT `tb_sales_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `tb_customer` (`id`),
  ADD CONSTRAINT `tb_sales_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `tb_goods` (`id`);

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
