-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: tm_library
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `students_grade_12`
--

DROP TABLE IF EXISTS `students_grade_12`;
/*!50001 DROP VIEW IF EXISTS `students_grade_12`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_12` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `sd_curr_borr`
--

DROP TABLE IF EXISTS `sd_curr_borr`;
/*!50001 DROP VIEW IF EXISTS `sd_curr_borr`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `sd_curr_borr` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `students_grade_9`
--

DROP TABLE IF EXISTS `students_grade_9`;
/*!50001 DROP VIEW IF EXISTS `students_grade_9`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_9` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `students_grade_11`
--

DROP TABLE IF EXISTS `students_grade_11`;
/*!50001 DROP VIEW IF EXISTS `students_grade_11`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_11` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `students_grade_7`
--

DROP TABLE IF EXISTS `students_grade_7`;
/*!50001 DROP VIEW IF EXISTS `students_grade_7`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_7` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `students_grade_10`
--

DROP TABLE IF EXISTS `students_grade_10`;
/*!50001 DROP VIEW IF EXISTS `students_grade_10`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_10` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `students_grade_8`
--

DROP TABLE IF EXISTS `students_grade_8`;
/*!50001 DROP VIEW IF EXISTS `students_grade_8`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `students_grade_8` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `sd_haveborr`
--

DROP TABLE IF EXISTS `sd_haveborr`;
/*!50001 DROP VIEW IF EXISTS `sd_haveborr`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `sd_haveborr` AS SELECT 
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `grade`,
 1 AS `dob`,
 1 AS `contact_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `students_grade_12`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_12`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_12` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '12') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `sd_curr_borr`
--

/*!50001 DROP VIEW IF EXISTS `sd_curr_borr`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sd_curr_borr` AS select distinct `s`.`student_id` AS `student_id`,`s`.`student_name` AS `student_name`,`s`.`grade` AS `grade`,`s`.`dob` AS `dob`,`s`.`contact_number` AS `contact_number` from (`students` `s` join `borrowings` `b` on((`s`.`student_id` = `b`.`student_id`))) where (`b`.`return_date` is null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `students_grade_9`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_9`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_9` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '9') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `students_grade_11`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_11`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_11` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '11') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `students_grade_7`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_7`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_7` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '7') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `students_grade_10`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_10`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_10` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '10') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `students_grade_8`
--

/*!50001 DROP VIEW IF EXISTS `students_grade_8`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `students_grade_8` AS select `students`.`student_id` AS `student_id`,`students`.`student_name` AS `student_name`,`students`.`grade` AS `grade`,`students`.`dob` AS `dob`,`students`.`contact_number` AS `contact_number` from `students` where (`students`.`grade` = '8') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `sd_haveborr`
--

/*!50001 DROP VIEW IF EXISTS `sd_haveborr`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sd_haveborr` AS select distinct `s`.`student_id` AS `student_id`,`s`.`student_name` AS `student_name`,`s`.`grade` AS `grade`,`s`.`dob` AS `dob`,`s`.`contact_number` AS `contact_number` from (`students` `s` join `borrowings` `b` on((`s`.`student_id` = `b`.`student_id`))) */;
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

-- Dump completed on 2024-01-29 22:03:36
