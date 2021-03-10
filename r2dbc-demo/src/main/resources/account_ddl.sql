
USE `r2dbcs_account`;

DROP TABLE IF EXISTS `ACCOUNT`;
CREATE TABLE `ACCOUNT`(
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_CODE` varchar(100) NOT NULL,
  `ACCOUNT_NAME` varchar(100) NOT NULL,  
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `account` VALUES ('1', 'account1', 'name1');
INSERT INTO `account` VALUES ('2', 'account2', 'name2');