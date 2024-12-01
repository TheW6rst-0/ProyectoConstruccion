-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2024 a las 00:45:22
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `claveEmp` int(10) NOT NULL,
  `nombreEmp` varchar(50) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `contrasenia` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`claveEmp`, `nombreEmp`, `usuario`, `contrasenia`) VALUES
(1, 'Emiliano Yamamoto', 'Bruhpepo', 'Chivas23'),
(2, 'Alexkibidi Canto', 'CP7', 'so'),
(23, 'asdfssdf', 'efsafdfs', 'mjaja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `idHabitacion` int(10) NOT NULL,
  `tipoHabitacion` varchar(20) NOT NULL,
  `capacidad` varchar(20) NOT NULL,
  `precio` double NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`idHabitacion`, `tipoHabitacion`, `capacidad`, `precio`, `disponibilidad`) VALUES
(64, 'Pendejo', '230', 50000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huesped`
--

CREATE TABLE `huesped` (
  `idHuesped` int(10) NOT NULL,
  `nombreHuesped` varchar(50) NOT NULL,
  `emailHuesped` varchar(50) NOT NULL,
  `idTarjeta` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `huesped`
--

INSERT INTO `huesped` (`idHuesped`, `nombreHuesped`, `emailHuesped`, `idTarjeta`) VALUES
(2, 'Skibidi', 'eresputo@gmail.com', 235);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservaciones`
--

CREATE TABLE `reservaciones` (
  `idReservacion` int(10) NOT NULL,
  `idHuesped` int(10) NOT NULL,
  `idHabitacion` int(10) NOT NULL,
  `fechaLlegada` date NOT NULL,
  `fechaSalida` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservaciones`
--

INSERT INTO `reservaciones` (`idReservacion`, `idHuesped`, `idHabitacion`, `fechaLlegada`, `fechaSalida`) VALUES
(21, 2, 64, '2024-12-01', '2024-12-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `idTarjeta` int(10) NOT NULL,
  `nombreTitular` varchar(40) NOT NULL,
  `numeroTarjeta` varchar(18) NOT NULL,
  `nip` varchar(4) NOT NULL,
  `vencimiento` date NOT NULL,
  `saldo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`idTarjeta`, `nombreTitular`, `numeroTarjeta`, `nip`, `vencimiento`, `saldo`) VALUES
(235, 'skibidi', '983245359694', '241', '2024-12-01', 14000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`claveEmp`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`idHabitacion`);

--
-- Indices de la tabla `huesped`
--
ALTER TABLE `huesped`
  ADD PRIMARY KEY (`idHuesped`),
  ADD KEY `id_tarjeta` (`idTarjeta`);

--
-- Indices de la tabla `reservaciones`
--
ALTER TABLE `reservaciones`
  ADD PRIMARY KEY (`idReservacion`),
  ADD KEY `Reserva_huesped` (`idHuesped`),
  ADD KEY `Reserva_habitacion` (`idHabitacion`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`idTarjeta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `claveEmp` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `idHabitacion` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `huesped`
--
ALTER TABLE `huesped`
  MODIFY `idHuesped` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `reservaciones`
--
ALTER TABLE `reservaciones`
  MODIFY `idReservacion` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `idTarjeta` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=236;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `huesped`
--
ALTER TABLE `huesped`
  ADD CONSTRAINT `id_tarjeta` FOREIGN KEY (`idTarjeta`) REFERENCES `tarjeta` (`idTarjeta`);

--
-- Filtros para la tabla `reservaciones`
--
ALTER TABLE `reservaciones`
  ADD CONSTRAINT `Reserva_habitacion` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  ADD CONSTRAINT `Reserva_huesped` FOREIGN KEY (`idHuesped`) REFERENCES `huesped` (`idHuesped`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
