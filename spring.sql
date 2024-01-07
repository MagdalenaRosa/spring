-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sty 07, 2024 at 09:06 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `city`, `street`) VALUES
(1, 'Kraków', 'Tyszki 4'),
(2, 'Olkusz', 'Poznańska 3'),
(3, 'Kraków', 'Długa 42');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `phone_number`
--

CREATE TABLE `phone_number` (
  `phone_id` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phone_number`
--

INSERT INTO `phone_number` (`phone_id`, `number`, `prefix`, `user_id`) VALUES
(1, '3534535', '+48', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(1023) DEFAULT NULL,
  `img_uri` varchar(255) DEFAULT NULL,
  `ingredients` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `img_uri`, `ingredients`, `name`, `price`, `category_id`) VALUES
(1, 'Veggie pizza ', 'https://www.pizzeria-prosciutto.pl/wp-content/uploads/2017/03/Margherita-scaled.jpg', 'Red onion\r\nPeppers\r\nLemon basil\r\nOlive oil\r\nPizza sauce\r\nBroccoli\r\nCarrots\r\nMushrooms\r\nOlives\r\n', 'Veggie', 34.00, 1),
(2, 'a seasonal sandwich (available in Winter) exclusive to the Polish Hamburglar menu. It has many variants, such as:\r\n\r\nClassic\r\nSpicy\r\nVegan\r\nBeef\r\nChicken,', 'https://cdn.mcdonalds.pl/uploads/20231102114929/352978-drwal-2023-na-www-1090x664px-72dpi-2-burger-drwala-klasyczny.png', NULL, 'The Lumberjack Burger', 30.00, 2),
(3, 'Pepperoni is essentially an American version of salami, something close to what Italians might call salame piccante, a generic term that means “spicy salami.”', 'https://i1.wp.com/onmykidsplate.com/wp-content/uploads/2022/01/Homemade-pepperoni-pizza-sliced.jpg', ' pepperoni ', 'Pepperoni', 38.00, 1),
(5, ' is a typical Neapolitan pizza, made with tomatoes, mozzarella cheese, fresh basil, salt, and olive oil', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Pizza_quasi_Margherita.jpg/800px-Pizza_quasi_Margherita.jpg', 'tomatoes, mozzarella cheese, fresh basil, salt, and olive oil.', 'Margherita ', 34.00, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product_category`
--

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `img_uri` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `description`, `img_uri`, `name`) VALUES
(1, 'Pizza is a dish of Italian origin.', 'https://upload.wikimedia.org/wikipedia/commons/9/91/Pizza-3007395.jpg', 'Pizza'),
(2, 'A hamburger, or simply burger, is a food consisting of fillings—usually a patty of ground meat, typically beef—placed inside a sliced bun or bread roll.', 'https://t2.gstatic.com/licensed-image?q=tbn:ANd9GcRRto3IlY56MlAIOAvXHvPEVxBDVzG1uz1zULEBYdJ-I4Aa-xOyPEVvv7fmIjLnxaOz', 'Hamburger');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `detail_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `first_name`, `last_name`, `password`, `detail_id`) VALUES
(1, 'Silverhand@gmail.com', 'John', 'Silverhand', 'secretPass', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_address_pivot`
--

CREATE TABLE `user_address_pivot` (
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_address_pivot`
--

INSERT INTO `user_address_pivot` (`user_id`, `address_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_details`
--

CREATE TABLE `user_details` (
  `user_details_id` int(11) NOT NULL,
  `personal_number` varchar(255) DEFAULT NULL,
  `tax_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_details_id`, `personal_number`, `tax_number`) VALUES
(1, '999999999', '459999999');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Indeksy dla tabeli `phone_number`
--
ALTER TABLE `phone_number`
  ADD PRIMARY KEY (`phone_id`),
  ADD KEY `FKb609grkur7fch5if2c0nrcujh` (`user_id`);

--
-- Indeksy dla tabeli `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5cypb0k23bovo3rn1a5jqs6j4` (`category_id`);

--
-- Indeksy dla tabeli `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sxbg3lobs13macl3hfeockc5` (`detail_id`);

--
-- Indeksy dla tabeli `user_address_pivot`
--
ALTER TABLE `user_address_pivot`
  ADD KEY `FKi9hwq399pfgao7n85o2k4voih` (`address_id`),
  ADD KEY `FKeolgj5sb45sgbqwrixtq4rc3m` (`user_id`);

--
-- Indeksy dla tabeli `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`user_details_id`),
  ADD UNIQUE KEY `UK_mcb54744wfbrhgmrumk7cwutv` (`tax_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phone_number`
--
ALTER TABLE `phone_number`
  MODIFY `phone_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `user_details_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `phone_number`
--
ALTER TABLE `phone_number`
  ADD CONSTRAINT `FKb609grkur7fch5if2c0nrcujh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK5cypb0k23bovo3rn1a5jqs6j4` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKeqemn6gi006xt7kup6m5ib20t` FOREIGN KEY (`detail_id`) REFERENCES `user_details` (`user_details_id`);

--
-- Constraints for table `user_address_pivot`
--
ALTER TABLE `user_address_pivot`
  ADD CONSTRAINT `FKeolgj5sb45sgbqwrixtq4rc3m` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKi9hwq399pfgao7n85o2k4voih` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
