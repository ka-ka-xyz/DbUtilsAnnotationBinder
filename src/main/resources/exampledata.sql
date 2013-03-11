# Dumping structure for table example.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firsrt_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# Dumping data for table example.person: ~3 rows (approximately)
DELETE FROM `person`;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `firsrt_name`, `last_name`, `age`) VALUES
    (1, 'Howard', 'Lovecraft', 18),
    (2, 'August', 'Derleth', 18),
    (3, 'Robert', 'Bloch', 18);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

# Dumping structure for table example.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `person_id` int(10) NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  `division` varchar(50) NOT NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `FK__person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table example.employee: ~0 rows (approximately)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`person_id`, `employee_id`, `division`) VALUES
    (1, 'EMP001', 'Weird Tales Div.'),
    (2, 'EMP002', 'Arkham House Div.'),
    (3, 'EMP003', 'Weird Tales Div.');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


