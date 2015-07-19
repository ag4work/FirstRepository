
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `books` (`id`, `title`) VALUES
(1, 'Book number 1'),
(2, 'Book number 2');
