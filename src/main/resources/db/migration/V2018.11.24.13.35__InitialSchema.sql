CREATE DATABASE  IF NOT EXISTS `health` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `health`;
-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: health
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `Grupy_JGP`
--

DROP TABLE IF EXISTS `Grupy_JGP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Grupy_JGP` (
  `ID_GrupyJGP` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod_katalogu_JGP` text,
  `Typ_kodu` text,
  `Kod` text,
  `Liczba_pacjentow` int(11) DEFAULT NULL,
  `Liczba_hospitalizacji` int(11) DEFAULT NULL,
  `Wspolczynnik_rehospitalizacji` text,
  `Udzial_hospitalizacji` text,
  `Udzial_hospitalizacji_w_sekcji` text,
  `Dlugosc_hosp_mediana` int(11) DEFAULT NULL,
  `Dlugosc_hosp_dominanta` int(11) DEFAULT NULL,
  `Srednia_wartosc_grupy` text,
  `Srednia_wartosc_hospitalizacji` text,
  `Srednia_wartosc_jednostek_grupy` text,
  `Srednia_wartosc_jednostek_hospitalizacji` text,
  `Produkty_ID_Produkty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_plec`
--

DROP TABLE IF EXISTS `Hosp_plec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_plec` (
  `ID_HospPlec` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `ID_Plec` int(11) DEFAULT NULL,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_produkt`
--

DROP TABLE IF EXISTS `Hosp_produkt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_produkt` (
  `ID_HospProdukt` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod_Produktu` text,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Produkty_ID_Produkty` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_tryb_przyjecie`
--

DROP TABLE IF EXISTS `Hosp_tryb_przyjecie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_tryb_przyjecie` (
  `ID_HospTrybPrzyjecie` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `ID_Tryb` int(11) DEFAULT NULL,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Tryb_przyjecia_ID_TrybPrzyjecia` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_tryb_wypis`
--

DROP TABLE IF EXISTS `Hosp_tryb_wypis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_tryb_wypis` (
  `ID_HospTrybWypis` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `ID_Tryb` int(11) DEFAULT NULL,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `ID_TrybWypis` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_wiek`
--

DROP TABLE IF EXISTS `Hosp_wiek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_wiek` (
  `ID_HospWiek` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `ID_Przedzialu` int(11) DEFAULT NULL,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hosp_zakres`
--

DROP TABLE IF EXISTS `Hosp_zakres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hosp_zakres` (
  `ID_HospZakres` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod_zakresu` text,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Zakresy_ID_Zakresy` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Kategorie_swiadczeniodawcow`
--

DROP TABLE IF EXISTS `Kategorie_swiadczeniodawcow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Kategorie_swiadczeniodawcow` (
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Nazwa` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OWNFZ`
--

DROP TABLE IF EXISTS `OWNFZ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OWNFZ` (
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `Kod` int(11) DEFAULT NULL,
  `Nazwa` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Plec`
--

DROP TABLE IF EXISTS `Plec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plec` (
  `ID_Plec` int(11) DEFAULT NULL,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Procedury`
--

DROP TABLE IF EXISTS `Procedury`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Procedury` (
  `﻿Kod` text,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Procedury_ICD9`
--

DROP TABLE IF EXISTS `Procedury_ICD9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Procedury_ICD9` (
  `ID_ProceduryICD9` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `Kierunkowa` text,
  `Kod_listy` text,
  `Kod` text,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Produkt_sum_JGP`
--

DROP TABLE IF EXISTS `Produkt_sum_JGP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Produkt_sum_JGP` (
  `ID_SUMJGP` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `ID_OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod` int(11) DEFAULT NULL,
  `LB_Hosp` int(11) DEFAULT NULL,
  `Udzial_w_lb_hospitalizacji` text,
  `Wartosc` int(11) DEFAULT NULL,
  `Udzial_w_wart_prod_dosumowywanych` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Produkty`
--

DROP TABLE IF EXISTS `Produkty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Produkty` (
  `﻿ID_Produkty` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod_Produktu` text,
  `Nazwa` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Produkty_dosumowywalne`
--

DROP TABLE IF EXISTS `Produkty_dosumowywalne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Produkty_dosumowywalne` (
  `﻿Kod` int(11) DEFAULT NULL,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Przedzialy_wiekowe`
--

DROP TABLE IF EXISTS `Przedzialy_wiekowe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Przedzialy_wiekowe` (
  `﻿ID_Przedzialu` int(11) DEFAULT NULL,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rozpoznania`
--

DROP TABLE IF EXISTS `Rozpoznania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rozpoznania` (
  `﻿Kod` text,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rozpoznania_ICD10`
--

DROP TABLE IF EXISTS `Rozpoznania_ICD10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rozpoznania_ICD10` (
  `ID_RozpoznaniaICD10` int(11) DEFAULT NULL,
  `ID_Zestawienia` int(11) DEFAULT NULL,
  `Kierunkowa` text,
  `Kod_listy` text,
  `Kod` text,
  `Udzial` text,
  `Mediana` int(11) DEFAULT NULL,
  `Grupy_JGP_ID_GrupyJGP` int(11) DEFAULT NULL,
  `OWNFZ` int(11) DEFAULT NULL,
  `ID_Kategorii` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tryb_przyjecia`
--

DROP TABLE IF EXISTS `Tryb_przyjecia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tryb_przyjecia` (
  `ď»żID_TrybPrzyjecia` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `ID_Tryb` int(11) DEFAULT NULL,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tryb_wypisu`
--

DROP TABLE IF EXISTS `Tryb_wypisu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tryb_wypisu` (
  `ď»żID_TrybWypis` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `ID_Tryb` int(11) DEFAULT NULL,
  `Opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Zakresy`
--

DROP TABLE IF EXISTS `Zakresy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Zakresy` (
  `ID_Zakresy` int(11) DEFAULT NULL,
  `Rok` int(11) DEFAULT NULL,
  `Kod_Zakresu` text,
  `Nazwa` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;