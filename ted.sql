-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 29, 2016 at 04:19 AM
-- Server version: 5.6.30-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ted`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 NOT NULL,
  `currently` decimal(20,2) NOT NULL,
  `buy_price` decimal(20,2) DEFAULT NULL,
  `first_bid` decimal(20,2) NOT NULL,
  `country` varchar(45) CHARACTER SET utf8 NOT NULL,
  `location` varchar(45) CHARACTER SET utf8 NOT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `creation` datetime NOT NULL,
  `starts` datetime DEFAULT NULL,
  `ends` datetime NOT NULL,
  `seller` varchar(45) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `currently`, `buy_price`, `first_bid`, `country`, `location`, `latitude`, `longitude`, `creation`, `starts`, `ends`, `seller`, `description`, `state`) VALUES
(3, 'Καρέκλα γραφείου με ροδάκια', 0.01, 1.01, 0.01, 'Ελλάδα', 'Αθήνα', 0.550000, 0.015500, '2016-07-27 20:46:52', NULL, '2016-07-29 11:20:00', 'admin', 'ασδασδασδσαδασ', 0),
(4, 'τραπεζι', 0.01, 1.01, 0.01, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-27 21:05:06', NULL, '2016-07-29 23:59:00', 'admin', 'αΣΔΣΔΑΣΔΑΣΔΑΣΔΑΣ', 0),
(5, 'test15', 0.01, 0.01, 0.01, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-27 21:07:54', NULL, '2016-07-30 11:20:00', 'admin', 'test15', 0),
(7, 'asasasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 12.00, NULL, 12.00, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-28 03:05:14', NULL, '2016-09-17 03:05:00', 'p1ranhas', 'fsfsfs', 0),
(8, 'Nokia 1100s', 100.00, 6000.00, 100.00, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-28 03:09:54', NULL, '2016-09-17 03:05:00', 'p1ranhas', 'dsadada', 1),
(9, 'Nokia 1100ss', 100.00, NULL, 100.00, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-28 03:13:51', NULL, '2016-06-14 03:00:00', 'p1ranhas', 'sdadsada', 0),
(10, 'Nokia 1100d', 100.00, NULL, 100.00, 'Ελλάδα', 'Αθήνα', NULL, NULL, '2016-07-28 03:15:56', NULL, '2016-06-14 03:00:00', 'p1ranhas', 'dadada', -1),
(11, 'Skatoules', 100000.00, NULL, 100000.00, 'Elas', 'albania', NULL, NULL, '2016-07-29 02:52:59', NULL, '2017-04-27 02:52:00', 'p1ranhas', 'adadasad', 0);

-- --------------------------------------------------------

--
-- Table structure for table `item_categories`
--

CREATE TABLE IF NOT EXISTS `item_categories` (
  `id` int(11) NOT NULL,
  `category` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item_categories`
--

INSERT INTO `item_categories` (`id`, `category`) VALUES
(0, 'Ηλεκτρονικά'),
(7, 'Βιβλία'),
(7, 'Ηλεκτρονικά'),
(8, 'Ηλεκτρονικά'),
(9, 'Ηλεκτρονικά'),
(10, 'Ηλεκτρονικά'),
(11, 'Ηλεκτρονικά');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `afm` varchar(100) NOT NULL,
  `role` int(11) NOT NULL,
  `validated` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `email`, `phone`, `country`, `city`, `address`, `afm`, `role`, `validated`) VALUES
(1, 'Stefa', 'Ana', '123456', 'stefagmail.com', '6611333', 'Grecia', 'Athena', 'kokoblokou 45', '232322323', 1, 1),
(18, 'Stefas Anas', 'aa', 'c81e728d9d4c2f636f067f89cc14862c', 'aggicftw@hotmail.com', '6513985', 'Ελλάδα', 'Μοσχάτο', 'Κορόμπου 8', '222', 1, 0),
(19, 'Stefas Anas', 'Stefas Anas', '0cc175b9c0f1b6a831c399e269772661', 'aggicftw@hotmail.com', '6513985', 'Ελλάδα', 'Μοσχάτο', 'Κορόμπου 8', '43434343', 1, 0),
(34, 'Stefas Anas', 'p1ranhas', '00e14c6fc35219dd01bd92563a4ae645', 'aggicftw@hotmail.com', '234343', 'Ελλάδα', 'Μοσχάτο', 'Κορόμπου 8', '43434343', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `username_idx` (`seller`);

--
-- Indexes for table `item_categories`
--
ALTER TABLE `item_categories`
  ADD PRIMARY KEY (`id`,`category`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `username` FOREIGN KEY (`seller`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
