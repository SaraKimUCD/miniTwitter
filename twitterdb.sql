-- MySQL dump 10.13  Distrib 5.7.23, for macos10.13 (x86_64)
--
-- Host: localhost    Database: twitterdb
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `twitterdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `twitterdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `twitterdb`;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `followID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `followedUserID` int(11) NOT NULL,
  `followDate` datetime NOT NULL,
  PRIMARY KEY (`followID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hashtag` (
  `hashtagID` int(11) NOT NULL AUTO_INCREMENT,
  `hashtagText` varchar(100) NOT NULL,
  `hashtagCount` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`hashtagID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mention`
--

DROP TABLE IF EXISTS `mention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mention` (
  `mentionID` int(11) NOT NULL AUTO_INCREMENT,
  `tweetID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`mentionID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tweet`
--

DROP TABLE IF EXISTS `tweet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tweet` (
  `tweetID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `twit` varchar(280) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`tweetID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tweetHashtag`
--

DROP TABLE IF EXISTS `tweetHashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tweetHashtag` (
  `tweetHashtagID` int(11) NOT NULL AUTO_INCREMENT,
  `tweetID` int(11) NOT NULL,
  `hashtagID` int(11) NOT NULL,
  PRIMARY KEY (`tweetHashtagID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `answer` varchar(45) NOT NULL,
  `profileURL` varchar(500) DEFAULT 'https://res.cloudinary.com/minitwitter/image/upload/v1541485112/profile/default_profile.png',
  `coverURL` varchar(500) DEFAULT 'https://res.cloudinary.com/minitwitter/image/upload/v1541485580/cover/default_cover.jpg',
  `lastVisit` datetime DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `view_hashtag_tweet`
--

DROP TABLE IF EXISTS `view_hashtag_tweet`;
/*!50001 DROP VIEW IF EXISTS `view_hashtag_tweet`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_hashtag_tweet` AS SELECT 
 1 AS `tweetID`,
 1 AS `userID`,
 1 AS `twit`,
 1 AS `time`,
 1 AS `username`,
 1 AS `fullname`,
 1 AS `profileURL`,
 1 AS `hashtagID`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view_mention_tweet`
--

DROP TABLE IF EXISTS `view_mention_tweet`;
/*!50001 DROP VIEW IF EXISTS `view_mention_tweet`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_mention_tweet` AS SELECT 
 1 AS `tweetID`,
 1 AS `tweet_userID`,
 1 AS `mention_userID`,
 1 AS `twit`,
 1 AS `time`,
 1 AS `username`,
 1 AS `fullname`,
 1 AS `profileURL`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view_notification`
--

DROP TABLE IF EXISTS `view_notification`;
/*!50001 DROP VIEW IF EXISTS `view_notification`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_notification` AS SELECT 
 1 AS `table_name`,
 1 AS `tweet_userID`,
 1 AS `mention_userID`,
 1 AS `twit`,
 1 AS `username`,
 1 AS `fullname`,
 1 AS `profileURL`,
 1 AS `userID`,
 1 AS `followedUserID`,
 1 AS `follow_fullname`,
 1 AS `follow_username`,
 1 AS `follow_profileURL`,
 1 AS `date`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view_tweets`
--

DROP TABLE IF EXISTS `view_tweets`;
/*!50001 DROP VIEW IF EXISTS `view_tweets`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_tweets` AS SELECT 
 1 AS `tweetID`,
 1 AS `tweet_userID`,
 1 AS `mention_userID`,
 1 AS `follow_userID`,
 1 AS `twit`,
 1 AS `time`,
 1 AS `username`,
 1 AS `fullname`,
 1 AS `profileURL`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `twitterdb`
--

USE `twitterdb`;

--
-- Final view structure for view `view_hashtag_tweet`
--

/*!50001 DROP VIEW IF EXISTS `view_hashtag_tweet`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_hashtag_tweet` AS select `tweet`.`tweetID` AS `tweetID`,`tweet`.`userID` AS `userID`,`tweet`.`twit` AS `twit`,`tweet`.`time` AS `time`,`user`.`username` AS `username`,`user`.`fullname` AS `fullname`,`user`.`profileURL` AS `profileURL`,`tweethashtag`.`hashtagID` AS `hashtagID` from ((`tweet` join `user` on((`tweet`.`userID` = `user`.`userID`))) left join `tweethashtag` on((`tweet`.`tweetID` = `tweethashtag`.`tweetID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_mention_tweet`
--

/*!50001 DROP VIEW IF EXISTS `view_mention_tweet`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_mention_tweet` AS select `tweet`.`tweetID` AS `tweetID`,`tweet`.`userID` AS `tweet_userID`,`mention`.`userID` AS `mention_userID`,`tweet`.`twit` AS `twit`,`tweet`.`time` AS `time`,`user`.`username` AS `username`,`user`.`fullname` AS `fullname`,`user`.`profileURL` AS `profileURL` from (`mention` left join (`tweet` join `user` on((`user`.`userID` = `tweet`.`userID`))) on((`tweet`.`tweetID` = `mention`.`tweetID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_notification`
--

/*!50001 DROP VIEW IF EXISTS `view_notification`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_notification` AS select `results`.`table_name` AS `table_name`,`results`.`tweet_userID` AS `tweet_userID`,`results`.`mention_userID` AS `mention_userID`,`results`.`twit` AS `twit`,`results`.`username` AS `username`,`results`.`fullname` AS `fullname`,`results`.`profileURL` AS `profileURL`,`results`.`userID` AS `userID`,`results`.`followedUserID` AS `followedUserID`,`results`.`follow_fullname` AS `follow_fullname`,`results`.`follow_username` AS `follow_username`,`results`.`follow_profileURL` AS `follow_profileURL`,`results`.`date` AS `date` from ((select 'mention' AS `table_name`,`view_mention_tweet`.`tweet_userID` AS `tweet_userID`,`view_mention_tweet`.`mention_userID` AS `mention_userID`,`view_mention_tweet`.`twit` AS `twit`,`view_mention_tweet`.`username` AS `username`,`view_mention_tweet`.`fullname` AS `fullname`,`view_mention_tweet`.`profileURL` AS `profileURL`,NULL AS `userID`,NULL AS `followedUserID`,NULL AS `follow_fullname`,NULL AS `follow_username`,NULL AS `follow_profileURL`,`view_mention_tweet`.`time` AS `date` from `twitterdb`.`view_mention_tweet`) union all (select 'follow' AS `table_name`,NULL AS `tweet_userID`,NULL AS `mention_userID`,NULL AS `twit`,NULL AS `username`,NULL AS `fullname`,NULL AS `profileURL`,`twitterdb`.`follow`.`userID` AS `userID`,`twitterdb`.`follow`.`followedUserID` AS `followedUserID`,`twitterdb`.`user`.`fullname` AS `follow_fullname`,`twitterdb`.`user`.`username` AS `follow_username`,`twitterdb`.`user`.`profileURL` AS `follow_profileURL`,`twitterdb`.`follow`.`followDate` AS `date` from (`twitterdb`.`follow` join `twitterdb`.`user` on((`twitterdb`.`user`.`userID` = `twitterdb`.`follow`.`userID`))))) `results` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_tweets`
--

/*!50001 DROP VIEW IF EXISTS `view_tweets`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_tweets` AS select `tweet`.`tweetID` AS `tweetID`,`tweet`.`userID` AS `tweet_userID`,`mention`.`userID` AS `mention_userID`,`follow`.`userID` AS `follow_userID`,`tweet`.`twit` AS `twit`,`tweet`.`time` AS `time`,`user`.`username` AS `username`,`user`.`fullname` AS `fullname`,`user`.`profileURL` AS `profileURL` from (((`tweet` join `user` on((`user`.`userID` = `tweet`.`userID`))) left join `follow` on((`follow`.`followedUserID` = `user`.`userID`))) left join `mention` on((`tweet`.`tweetID` = `mention`.`tweetID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-01 15:52:58
