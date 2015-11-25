
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `TB_TEST`
-- ----------------------------
DROP TABLE IF EXISTS `TB_TEST`;
CREATE TABLE `TB_TEST` (
 		`TEST_ID` varchar(100) NOT NULL,
		`AER1` varchar(255) DEFAULT NULL COMMENT '测试',
  		PRIMARY KEY (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
