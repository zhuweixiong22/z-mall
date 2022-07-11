/*
 Navicat Premium Data Transfer

 Source Server         : 7yue
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : sleeve

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 17/02/2020 14:57:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
                            `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                            `title` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `start_time` datetime(3) NOT NULL,
                            `end_time` datetime(3) NOT NULL,
                            `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                            `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                            `delete_time` datetime(3) DEFAULT NULL,
                            `remark` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `online` tinyint(3) unsigned DEFAULT '1',
                            `entrance_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `internal_top_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for activity_category
-- ----------------------------
DROP TABLE IF EXISTS `activity_category`;
CREATE TABLE `activity_category` (
                                     `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                     `category_id` int(10) unsigned NOT NULL,
                                     `activity_id` int(11) NOT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for activity_coupon
-- ----------------------------
DROP TABLE IF EXISTS `activity_coupon`;
CREATE TABLE `activity_coupon` (
                                   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                   `coupon_id` int(10) unsigned NOT NULL,
                                   `activity_id` int(11) unsigned NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for activity_spu
-- ----------------------------
DROP TABLE IF EXISTS `activity_spu`;
CREATE TABLE `activity_spu` (
                                `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                `activity_id` int(10) unsigned NOT NULL,
                                `spu_id` int(10) unsigned NOT NULL,
                                `participation` tinyint(3) unsigned DEFAULT '0',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
-- l逻辑删除可以使用delete_time 或 status（0，1）字段 都差不多
-- 一组轮播图
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                          `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                          `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                          `delete_time` datetime(3) DEFAULT NULL,
                          `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部分banner可能有标题图片',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for banner_item
-- ----------------------------
-- 我们点击一个轮播图，可能是直接跳转到一个spu商品的详情链接，也可能是跳转到一个专题，包含多个商品，为了体现这种灵活性，所以我们需要设置一个type字段来标识该banner是何种情况
-- 如果type是一个商品 则keyword就是商品的id 如果type是一专题，则keyword就是专题的一个标识
DROP TABLE IF EXISTS `banner_item`;
CREATE TABLE `banner_item` (
                               `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                               `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `keyword` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `type` smallint(5) unsigned NOT NULL DEFAULT '0',
                               `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                               `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                               `delete_time` datetime(3) DEFAULT NULL,
                               `banner_id` int(10) unsigned NOT NULL,
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
                         `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                         `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `delete_time` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for category
-- 该项目只有两级分类
-- index（sort_order） : 分类可能也需要排序 运营手段
-- online：也可能用到下降整个分类
-- level：如果设计多级分类的话，可以标注分类级别 可以简化查询，没有level其实也能查出来，这个项目没有用到这个字段
-- is_root；是否冗余
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                            `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                            `delete_time` datetime(3) DEFAULT NULL,
                            `is_root` tinyint(3) unsigned NOT NULL DEFAULT '0',
                            `parent_id` int(10) unsigned DEFAULT NULL,
                            `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `sort_order` int(10) unsigned DEFAULT NULL,
                            `online` int(10) unsigned DEFAULT '1',
                            `level` int(10) unsigned DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `start_time` datetime DEFAULT NULL,
                          `end_time` datetime DEFAULT NULL,
                          `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `full_money` decimal(10,2) DEFAULT NULL,
                          `minus` decimal(10,2) DEFAULT NULL,
                          `rate` decimal(10,2) DEFAULT NULL,
                          `type` smallint(6) NOT NULL COMMENT '1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券',
                          `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                          `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                          `delete_time` datetime(3) DEFAULT NULL,
                          `activity_id` int(10) unsigned DEFAULT NULL,
                          `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `whole_store` tinyint(3) unsigned DEFAULT '0',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for coupon_category
-- ----------------------------
DROP TABLE IF EXISTS `coupon_category`;
CREATE TABLE `coupon_category` (
                                   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                   `category_id` int(10) unsigned NOT NULL,
                                   `coupon_id` int(11) unsigned NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for coupon_template
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
                                   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                   `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                   `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                   `full_money` decimal(10,2) DEFAULT NULL,
                                   `minus` decimal(10,2) DEFAULT NULL,
                                   `discount` decimal(10,2) DEFAULT NULL,
                                   `type` smallint(6) NOT NULL COMMENT '1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券',
                                   `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                                   `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                                   `delete_time` datetime(3) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for coupon_type
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
                               `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                               `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `code` int(11) NOT NULL,
                               `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `delete_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for grid_category
-- 宫格分类
-- 宫格的分类可以是二级也可以是二级的，如果是一级的则root_category_id不为null
-- ----------------------------
DROP TABLE IF EXISTS `grid_category`;
CREATE TABLE `grid_category` (
                                 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                 `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                 `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                 `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                                 `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                                 `delete_time` datetime(3) DEFAULT NULL,
                                 `category_id` int(11) DEFAULT NULL,
                                 `root_category_id` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                         `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `user_id` int(10) unsigned DEFAULT NULL COMMENT 'user表外键',
                         `total_price` decimal(10,2) DEFAULT '0.00',
                         `total_count` int(11) unsigned DEFAULT '0',
                         `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                         `delete_time` datetime(3) DEFAULT NULL,
                         `expired_time` datetime(3) DEFAULT NULL,
                         `placed_time` datetime(3) DEFAULT NULL,
                         `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                         `snap_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `snap_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `snap_items` json DEFAULT NULL,
                         `snap_address` json DEFAULT NULL,
                         `prepay_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `final_total_price` decimal(10,2) DEFAULT NULL,
                         `status` tinyint(3) unsigned DEFAULT '1',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE KEY `uni_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sale_explain
-- ----------------------------
DROP TABLE IF EXISTS `sale_explain`;
CREATE TABLE `sale_explain` (
                                `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                `fixed` tinyint(3) unsigned DEFAULT '0',
                                `text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                `spu_id` int(11) DEFAULT NULL,
                                `index` int(10) unsigned DEFAULT NULL,
                                `replace_id` int(10) unsigned DEFAULT NULL,
                                `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                                `delete_time` datetime(3) DEFAULT NULL,
                                `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sku
-- decimal(10,2) 总位数和：10 小数部分：2 精度到分
-- sku 的image只设置了一张
-- category ->(category_id、root_category_id) spu ->(spu_id) sku
-- 为什么这里sku也设置了category_id、root_category_id，理论上是多余的，主要是考虑查询性能减少join查询，某种情况下可能直接通过category查询spu
-- code: 一个sku的唯一标识（相当于一种协议，是由我们自定义的spu_id$spec_key-spec_value#spec_key-spec_value），用主键id标识也是一种方案，但是用code码会更方便，具有程序可解析性，对服务端id和code码没有什么影响，但是对于前段解析来说code码会更方便
-- stock：库存量，很多电商系统里，针对这个库存量还有一个单位（个、件、只）的概念，这个可以新建一张单位表、添加一个单位字段
-- specs：sku最复杂的字段是规格，这个字段也是冗余的，由于spu-sku、spu-spec_key、sku-spec_value之间的关系，我们是可以一路查询出一个sku的spec信息的、显然这样查询是非常麻烦的，所以我们可以提前将这个spec信息写在sku表里
-- 类是可以表达复杂结构的，在mysql里的体现就是json
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
                       `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                       `price` decimal(10,2) unsigned NOT NULL,
                       `discount_price` decimal(10,2) unsigned DEFAULT NULL,
                       `online` tinyint(3) unsigned NOT NULL DEFAULT '1',
                       `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `spu_id` int(10) unsigned NOT NULL,
                       `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                       `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                       `delete_time` datetime(3) DEFAULT NULL,
                       `specs` json DEFAULT NULL,
                       `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `stock` int(10) unsigned NOT NULL DEFAULT '0',
                       `category_id` int(10) unsigned DEFAULT NULL,
                       `root_category_id` int(10) unsigned DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sku_spec
-- sku - spec_value 的多对多关系
-- 该表相对于spu - spec_key的中间表多了几个字段，
-- 其实多出的几个字段spu_id、key_id也是有点冗余
-- ----------------------------
DROP TABLE IF EXISTS `sku_spec`;
CREATE TABLE `sku_spec` (
                            `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                            `spu_id` int(10) unsigned NOT NULL,
                            `sku_id` int(10) unsigned NOT NULL,
                            `key_id` int(10) unsigned NOT NULL,
                            `value_id` int(10) unsigned NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spec_key
-- 将规格名看做是一个实体
-- name: 规格名的名字
-- unit: 规格的单位，和sku表中stock中单位不是一个概念 对于手机来讲stock的单位是一台，而手机的规格有很多，比如手机尺寸就是一种规格，这时的规格单位就是尺寸的单位xx英寸
-- standard: 是否是一种标准的规格，是人为规定的，带有主观性
-- ----------------------------
DROP TABLE IF EXISTS `spec_key`;
CREATE TABLE `spec_key` (
                            `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                            `unit` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `standard` tinyint(3) unsigned NOT NULL DEFAULT '0',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `delete_time` datetime DEFAULT NULL,
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spec_value
-- spec_key : spec_value 一对多
-- spec_id: 外键 spec_key表的主键
-- value：具体规格的值
-- extend：拓展字段
-- ----------------------------
DROP TABLE IF EXISTS `spec_value`;
CREATE TABLE `spec_value` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                              `spec_id` int(10) unsigned NOT NULL,
                              `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                              `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                              `delete_time` datetime(3) DEFAULT NULL,
                              `extend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spu
-- spu的price并不参与数学计算，因为不是一个具体的sku，price一般用做前端展示，一般是有一个浮动是一个文本类型，例：2999~3100
-- sketch_spec_id:可视规格id值
-- default_sku_id：默认sku_id值
-- img: spu的图片
-- discount_price：折扣价格和price一样是折扣价格
-- tags: 小标签（可以设置成一对多）再提取出来一张表，这里选择只用一个字段使用一些符号拼接起来，优点：减少一次查询 缺点：更新麻烦，某些情况只能先查询再更改
-- test：无意义
-- 为什么会有两个category_id，一个是root_category_id，是因为业务需要，一个是需要根据一级分类查spu，一个是根据二级分类查spu
-- 如果只有二级分类id，我们要查出一级分类下的所有商品是非常麻烦的，要先查出所有该一级下的二级category_id，再根据二级去查询
-- ----------------------------
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
                       `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                       `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                       `subtitle` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `category_id` int(10) unsigned NOT NULL,
                       `root_category_id` int(11) DEFAULT NULL,
                       `online` tinyint(3) unsigned NOT NULL DEFAULT '1',
                       `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                       `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                       `delete_time` datetime(3) DEFAULT NULL,
                       `price` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文本型价格，有时候SPU需要展示的是一个范围，或者自定义平均价格',
                       `sketch_spec_id` int(10) unsigned DEFAULT NULL COMMENT '某种规格可以直接附加单品图片',
                       `default_sku_id` int(11) DEFAULT NULL COMMENT '默认选中的sku',
                       `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `discount_price` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `tags` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `sort_order` int(10) unsigned DEFAULT NULL,
                       `spu_theme_img` json DEFAULT NULL,
                       `for_theme_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spu_detail_img
-- spu详情页面下面的商品详细图 一个spu对多张详细图
-- index：排序字段 详情图是有顺序的
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail_img`;
CREATE TABLE `spu_detail_img` (
                                  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                                  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `spu_id` int(10) unsigned DEFAULT NULL,
                                  `sort_order` int(10) unsigned DEFAULT NULL,
                                  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                                  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                                  `delete_time` datetime(3) DEFAULT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spu_img
-- spu详情页面顶部的轮播图 一个spu对多张轮播图
-- ----------------------------
DROP TABLE IF EXISTS `spu_img`;
CREATE TABLE `spu_img` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `spu_id` int(10) unsigned DEFAULT NULL,
                           `delete_time` datetime(3) DEFAULT NULL,
                           `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                           `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spu_key
-- ----------------------------
DROP TABLE IF EXISTS `spu_key`;
CREATE TABLE `spu_key` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `spu_id` int(10) unsigned NOT NULL,
                           `spec_key_id` int(10) unsigned NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for spu_tag
-- ----------------------------
DROP TABLE IF EXISTS `spu_tag`;
CREATE TABLE `spu_tag` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `spu_id` int(10) unsigned NOT NULL,
                           `tag_id` int(10) unsigned NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
                       `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                       `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '中文限制6个，英文限制12个，由逻辑层控制',
                       `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                       `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                       `delete_time` datetime(3) DEFAULT NULL,
                       `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                       `highlight` tinyint(4) unsigned DEFAULT '0',
                       `type` tinyint(3) unsigned DEFAULT '1',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for theme
-- 一些节日主题 开学季等等
-- 这个项目里活动和主题不是一个概念，该项目的活动主要服务于优惠券
-- title和name的区别 建议：title一般显示给前端的主题名字 name更多时候像id这种性质 一般是作为一种具有文本性的标识
-- tpl_name 给前端定义的模板名字
-- entrance_img：主题入口，入口一般为一张图片
-- extend：无意义，以后可能作为扩展字段
-- internal_top_img：点击进入主题后，主题详情顶部的一张图片
-- online: 主题是否上线
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
                         `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `title` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                         `tpl_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                         `delete_time` datetime(3) DEFAULT NULL,
                         `entrance_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `extend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `internal_top_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `title_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `online` tinyint(3) unsigned DEFAULT '1',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for theme_spu
-- ----------------------------
DROP TABLE IF EXISTS `theme_spu`;
CREATE TABLE `theme_spu` (
                             `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                             `theme_id` int(10) unsigned NOT NULL,
                             `spu_id` int(10) unsigned NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                        `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `nickname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `unify_uid` int(10) DEFAULT NULL,
                        `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `wx_profile` json DEFAULT NULL,
                        `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                        `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                        `delete_time` datetime(3) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uni_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
                               `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                               `user_id` int(10) unsigned NOT NULL,
                               `coupon_id` int(10) unsigned NOT NULL,
                               `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '1:未使用，2：已使用， 3：已过期',
                               `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                               `order_id` int(10) unsigned DEFAULT NULL,
                               `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uni_user_coupon` (`user_id`,`coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
