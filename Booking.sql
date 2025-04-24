-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 23 avr. 2025 à 17:32
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `booking`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

DROP TABLE IF EXISTS `administrateurs`;
CREATE TABLE IF NOT EXISTS `administrateurs` (
  `id_administrateurs` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_administrateurs`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id_categories` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_categories`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id_categories`, `nom`) VALUES
(1, 'Hôtel'),
(2, 'Villa'),
(3, 'Chalet'),
(4, 'Colocation / Loft'),
(6, 'Gîte'),
(7, 'Chambre'),
(8, 'Loft'),
(9, 'Colocation'),
(10, 'Suite'),
(5, 'Maison');

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

DROP TABLE IF EXISTS `commentaires`;
CREATE TABLE IF NOT EXISTS `commentaires` (
  `id_commentaires` int NOT NULL AUTO_INCREMENT,
  `id_utilisateurs` int DEFAULT NULL,
  `id_site` int DEFAULT NULL,
  `note` int DEFAULT NULL,
  `commentaire` text,
  `dateCommentaire` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_commentaires`),
  KEY `idUtilisateur` (`id_utilisateurs`),
  KEY `idHebergement` (`id_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `disponibilites`
--

DROP TABLE IF EXISTS `disponibilites`;
CREATE TABLE IF NOT EXISTS `disponibilites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_logements` int NOT NULL,
  `dateDisponible` date NOT NULL,
  `estDisponible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `id_logements` (`id_logements`)
) ENGINE=MyISAM AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `disponibilites`
--

INSERT INTO `disponibilites` (`id`, `id_logements`, `dateDisponible`, `estDisponible`) VALUES
(1, 1, '2025-04-24', 1),
(2, 1, '2025-04-25', 1),
(3, 1, '2025-04-26', 1),
(4, 1, '2025-04-27', 1),
(5, 1, '2025-04-28', 1),
(6, 2, '2025-04-24', 1),
(7, 2, '2025-04-25', 1),
(8, 2, '2025-04-26', 1),
(9, 2, '2025-04-27', 1),
(10, 2, '2025-04-28', 1),
(11, 3, '2025-04-24', 1),
(12, 3, '2025-04-25', 1),
(13, 3, '2025-04-26', 1),
(14, 3, '2025-04-27', 1),
(15, 3, '2025-04-28', 1),
(16, 4, '2025-04-24', 1),
(17, 4, '2025-04-25', 1),
(18, 4, '2025-04-26', 1),
(19, 4, '2025-04-27', 1),
(20, 4, '2025-04-28', 1),
(21, 5, '2025-04-24', 1),
(22, 5, '2025-04-25', 1),
(23, 5, '2025-04-26', 1),
(24, 5, '2025-04-27', 1),
(25, 5, '2025-04-28', 1),
(26, 6, '2025-04-27', 1),
(27, 6, '2025-04-28', 1),
(28, 6, '2025-04-29', 1),
(29, 6, '2025-04-30', 1),
(30, 6, '2025-05-01', 1),
(31, 7, '2025-04-27', 1),
(32, 7, '2025-04-28', 1),
(33, 7, '2025-04-29', 1),
(34, 7, '2025-04-30', 1),
(35, 7, '2025-05-01', 1),
(36, 8, '2025-04-27', 1),
(37, 8, '2025-04-28', 1),
(38, 8, '2025-04-29', 1),
(39, 8, '2025-04-30', 1),
(40, 8, '2025-05-01', 1),
(41, 9, '2025-04-27', 1),
(42, 9, '2025-04-28', 1),
(43, 9, '2025-04-29', 1),
(44, 9, '2025-04-30', 1),
(45, 9, '2025-05-01', 1),
(46, 10, '2025-04-27', 1),
(47, 10, '2025-04-28', 1),
(48, 10, '2025-04-29', 1),
(49, 10, '2025-04-30', 1),
(50, 10, '2025-05-01', 1),
(51, 11, '2025-05-02', 1),
(52, 11, '2025-05-03', 1),
(53, 11, '2025-05-04', 1),
(54, 11, '2025-05-05', 1),
(55, 11, '2025-05-06', 1),
(56, 12, '2025-05-02', 1),
(57, 12, '2025-05-03', 1),
(58, 12, '2025-05-04', 1),
(59, 12, '2025-05-05', 1),
(60, 12, '2025-05-06', 1),
(61, 13, '2025-05-02', 1),
(62, 13, '2025-05-03', 1),
(63, 13, '2025-05-04', 1),
(64, 13, '2025-05-05', 1),
(65, 13, '2025-05-06', 1),
(66, 14, '2025-05-02', 1),
(67, 14, '2025-05-03', 1),
(68, 14, '2025-05-04', 1),
(69, 14, '2025-05-05', 1),
(70, 14, '2025-05-06', 1),
(71, 15, '2025-05-02', 1),
(72, 15, '2025-05-03', 1),
(73, 15, '2025-05-04', 1),
(74, 15, '2025-05-05', 1),
(75, 15, '2025-05-06', 1),
(76, 16, '2025-04-29', 1),
(77, 16, '2025-04-30', 1),
(78, 16, '2025-05-01', 1),
(79, 16, '2025-05-02', 1),
(80, 16, '2025-05-03', 1),
(81, 17, '2025-04-29', 1),
(82, 17, '2025-04-30', 1),
(83, 17, '2025-05-01', 1),
(84, 17, '2025-05-02', 1),
(85, 17, '2025-05-03', 1),
(86, 18, '2025-04-29', 1),
(87, 18, '2025-04-30', 1),
(88, 18, '2025-05-01', 1),
(89, 18, '2025-05-02', 1),
(90, 18, '2025-05-03', 1),
(91, 19, '2025-04-29', 1),
(92, 19, '2025-04-30', 1),
(93, 19, '2025-05-01', 1),
(94, 19, '2025-05-02', 1),
(95, 19, '2025-05-03', 1),
(96, 20, '2025-04-29', 1),
(97, 20, '2025-04-30', 1),
(98, 20, '2025-05-01', 1),
(99, 20, '2025-05-02', 1),
(100, 20, '2025-05-03', 1),
(101, 21, '2025-04-24', 1),
(102, 21, '2025-04-25', 1),
(103, 22, '2025-04-24', 1),
(104, 22, '2025-04-25', 1),
(105, 23, '2025-04-24', 1),
(106, 23, '2025-04-25', 1),
(107, 24, '2025-04-24', 1),
(108, 24, '2025-04-25', 1);

-- --------------------------------------------------------

--
-- Structure de la table `logements`
--

DROP TABLE IF EXISTS `logements`;
CREATE TABLE IF NOT EXISTS `logements` (
  `id_logements` int NOT NULL AUTO_INCREMENT,
  `id_site` int NOT NULL,
  `description` text NOT NULL,
  `prixParNuit` decimal(7,2) NOT NULL,
  `wifi` tinyint(1) DEFAULT '0',
  `climatisation` tinyint(1) DEFAULT '0',
  `fumeur` tinyint(1) DEFAULT '0',
  `petitDejeuner` tinyint(1) DEFAULT '0',
  `vueMer` tinyint(1) DEFAULT '0',
  `minibar` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_logements`),
  KEY `id_site` (`id_site`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `logements`
--

INSERT INTO `logements` (`id_logements`, `id_site`, `description`, `prixParNuit`, `wifi`, `climatisation`, `fumeur`, `petitDejeuner`, `vueMer`, `minibar`) VALUES
(1, 1, 'Studio moderne à Paris avec WiFi et climatisation, proche du métro.', 95.00, 1, 1, 0, 1, 0, 1),
(2, 2, 'Chalet typique avec cheminée, 2 chambres, ambiance chaleureuse.', 180.00, 1, 0, 0, 1, 0, 0),
(3, 3, 'Villa de luxe avec piscine privée et vue sur la mer à Nice.', 250.00, 1, 1, 0, 1, 1, 1),
(4, 4, 'Chambre économique dans le centre de Paris, pratique et abordable.', 55.00, 1, 0, 1, 1, 0, 0),
(5, 5, 'Maison familiale spacieuse avec jardin privé à Bordeaux.', 130.00, 1, 1, 0, 1, 0, 1),
(6, 6, 'Appartement avec balcon vue mer à Marseille, climatisé.', 140.00, 1, 1, 0, 1, 1, 0),
(7, 7, 'Hôtel 4 étoiles à Paris, chambre avec petit déjeuner et WiFi.', 160.00, 1, 1, 0, 1, 0, 1),
(8, 8, 'Loft avec terrasse à Marseille et coin bureau design.', 145.00, 1, 1, 0, 1, 1, 1),
(9, 9, 'Chambre privative en colocation à Lille avec WiFi.', 40.00, 1, 0, 1, 0, 0, 0),
(10, 10, 'Suite avec minibar et climatisation au cœur de Strasbourg.', 175.00, 1, 1, 0, 1, 1, 1),
(11, 11, 'Chambre rénovée à Paris avec vue sur cour intérieure et calme.', 150.00, 1, 0, 0, 1, 0, 1),
(12, 12, 'Hôtel confortable à Paris, idéal pour les séjours d’affaires.', 110.00, 1, 0, 0, 1, 0, 1),
(13, 13, 'Hôtel chic à Montmartre avec chambres rénovées.', 135.00, 1, 1, 0, 1, 0, 1),
(14, 14, 'Hôtel design avec coworking à Bastille et terrasse.', 145.00, 1, 1, 0, 1, 0, 1),
(15, 15, 'Hôtel luxe Champs-Élysées avec spa, restaurant, vue.', 260.00, 1, 1, 0, 1, 1, 1),
(16, 16, 'Maison spacieuse dans le centre de Bordeaux avec patio.', 140.00, 1, 1, 0, 1, 0, 0),
(17, 17, 'Maison calme proche Jardin Public avec cuisine équipée.', 120.00, 1, 0, 0, 1, 0, 0),
(18, 18, 'Maison en pierre typique de Bordeaux, 3 chambres, salon.', 150.00, 1, 1, 0, 1, 0, 1),
(19, 19, 'Chalet avec sauna, 3 chambres et vue sur les montagnes.', 300.00, 1, 1, 0, 1, 0, 1),
(20, 20, 'Chalet authentique avec terrasse boisée, ambiance chaleureuse.', 220.00, 1, 0, 1, 1, 0, 0),
(21, 21, 'Chalet haut de gamme avec jacuzzi extérieur et cheminée.', 350.00, 1, 1, 0, 1, 0, 1),
(22, 22, 'Appartement lumineux au Vieux-Port à Marseille.', 90.00, 1, 0, 0, 0, 1, 0),
(23, 23, 'Appartement haut de gamme à Marseille Prado, climatisé.', 110.00, 1, 1, 0, 1, 0, 1),
(24, 24, 'Studio moderne à la Canebière, proche commerces et transports.', 75.00, 1, 0, 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idReservation` int DEFAULT NULL,
  `montantTotal` decimal(10,2) DEFAULT NULL,
  `reductionAppliquee` decimal(10,2) DEFAULT NULL,
  `methodePaiement` enum('CB','PayPal','Virement','Autre') DEFAULT NULL,
  `statutPaiement` enum('en_attente','effectue','echec') DEFAULT NULL,
  `datePaiement` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idReservation` (`idReservation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reductions`
--

DROP TABLE IF EXISTS `reductions`;
CREATE TABLE IF NOT EXISTS `reductions` (
  `id_reductions` int NOT NULL AUTO_INCREMENT,
  `description` text,
  `pourcentage` decimal(5,2) DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id_reductions`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `id_reservations` int NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int DEFAULT NULL,
  `id_logements` int DEFAULT NULL,
  `dateArrivee` date DEFAULT NULL,
  `dateDepart` date DEFAULT NULL,
  `nbAdultes` int DEFAULT NULL,
  `nbEnfants` int DEFAULT NULL,
  `nbChambres` int DEFAULT NULL,
  `idReduction` int DEFAULT NULL,
  `prixPayé` decimal(10,2) DEFAULT NULL,
  `dateReservation` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_reservations`),
  KEY `id_utilisateur` (`id_utilisateur`),
  KEY `id_logements` (`id_logements`),
  KEY `idReduction` (`idReduction`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `id_site` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) DEFAULT NULL,
  `description` text,
  `id_categorie` int DEFAULT NULL,
  `adresse` text,
  `ville` varchar(100) DEFAULT NULL,
  `nbEtoiles` int DEFAULT NULL,
  `prixParNuit` decimal(10,2) DEFAULT NULL,
  `photo` text,
  `petitDejeuner` tinyint(1) DEFAULT '0',
  `parking` tinyint(1) DEFAULT '0',
  `piscine` tinyint(1) DEFAULT '0',
  `transportProche` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_site`),
  KEY `id_categorie` (`id_categorie`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id_site`, `nom`, `description`, `id_categorie`, `adresse`, `ville`, `nbEtoiles`, `prixParNuit`, `photo`, `petitDejeuner`, `parking`, `piscine`, `transportProche`) VALUES
(1, 'Appartement Paris Métro', 'Appartement moderne à Paris, proche du métro.', 5, '123 rue Lafayette', 'Paris', 3, 95.00, 'appartParis.jpg', 1, 0, 0, 1),
(2, 'Chalet Alpes', 'Chalet chaleureux dans les Alpes, idéal pour les vacances d’hiver.', 3, 'Route des Neiges', 'Chamonix', 4, 180.00, 'chaletAlpes.jpg', 1, 1, 0, 1),
(3, 'Villa Mer Nice', 'Villa en bord de mer à Nice avec vue imprenable.', 2, '7 avenue de la Plage', 'Nice', 5, 250.00, 'villaNice.jpg', 1, 1, 1, 1),
(4, 'Chambre Paris Éco', 'Chambre simple à Paris, pratique et économique.', 4, '12 rue du Faubourg', 'Paris', 2, 55.00, 'chambreParis.jpg', 1, 0, 0, 1),
(5, 'Maison Bordeaux Jardin', 'Maison spacieuse à Bordeaux avec jardin.', 4, '8 chemin des Vignes', 'Bordeaux', 4, 130.00, 'maisonBordeaux.jpg', 1, 1, 0, 1),
(6, 'Hôtel Marseille Plage', 'Hôtel avec vue mer à Marseille, proche du Vieux-Port.', 1, '45 Boulevard du Littoral', 'Marseille', 4, 140.00, 'hotelMarseille.jpg', 1, 1, 1, 1),
(7, 'Hôtel Paris Centre', 'Hôtel moderne situé en plein cœur de Paris, proche de toutes commodités.', 1, '10 rue de Rivoli', 'Paris', 4, 160.00, 'hotelParis.jpg', 1, 1, 1, 1),
(8, 'Loft Marseille Terrasse', 'Loft design à Marseille avec terrasse.', 4, '2 rue des Catalans', 'Marseille', 4, 145.00, 'loftMarseille.jpg', 1, 1, 0, 1),
(9, 'Colocation Lille', 'Appartement en colocation à Lille, chambre privée.', 4, '56 rue Nationale', 'Lille', 2, 40.00, 'colocLille.jpg', 0, 0, 0, 1),
(10, 'Suite Strasbourg Minibar', 'Suite avec minibar à Strasbourg.', 1, '10 rue de la République', 'Strasbourg', 5, 175.00, 'suiteStrasbourg.jpg', 1, 1, 1, 1),
(11, 'Hôtel Opéra', 'Situé dans le quartier cosmopolite du 9e arrondissement, cet hôtel de style décontracté se trouve à 6 minutes à pied de        la station de métro Poissonnière, à 2 km du palais Garnier et à 6 km de la tour Eiffel.\r\n     Les chambres chaleureuses au mobilier simple sont équipées du Wi-Fi gratuit. Celles de catégorie supérieure offrent une        vue sur la tour Eiffel.', 1, '37 rue de Maubeuge', 'Paris', 4, 150.00, 'hotelParis2.jpg', 1, 1, 0, 1),
(12, 'Hôtel Saint-Michel', 'Hôtel 3 étoiles confortable situé à deux pas de Notre-Dame, parfait pour découvrir le quartier Latin.', 1, '20 rue Saint-Jacques', 'Paris', 3, 110.00, 'hotelSaintMichel.jpg', 1, 0, 0, 1),
(13, 'Hôtel Montmartre Chic', 'Hôtel cosy au cœur de Montmartre avec chambres rénovées et vue sur le Sacré-Cœur.', 1, '9 rue Lepic', 'Paris', 4, 135.00, 'hotelMontmartre.jpg', 1, 1, 0, 1),
(14, 'Hôtel Bastille Design', 'Établissement design à Bastille avec espace coworking, idéal pour les voyageurs connectés.', 1, '12 rue de la Roquette', 'Paris', 4, 145.00, 'hotelBastille.jpg', 1, 1, 1, 1),
(15, 'Hôtel Champs-Elysées Luxe', 'Hôtel de luxe avec spa, restaurant gastronomique et service 5 étoiles à 200m des Champs-Elysées.', 1, '3 avenue George V', 'Paris', 5, 260.00, 'hotelChampsElysees.jpg', 1, 1, 1, 1),
(16, 'Maison Bordeaux Centre', 'Maison familiale spacieuse en plein centre, idéale pour les séjours en groupe.', 5, '12 rue Sainte-Catherine', 'Bordeaux', 4, 140.00, 'maisonCentreBdx.jpg', 1, 1, 0, 1),
(17, 'Maison Jardin Botanique', 'Belle maison avec jardin fleuri, proche du Jardin Botanique et des quais.', 5, '21 rue du Jardin Public', 'Bordeaux', 3, 120.00, 'maisonJardinBdx.jpg', 1, 1, 0, 1),
(18, 'Maison Pierre Bordeaux', 'Maison typique en pierre de taille, calme et élégante avec patio.', 5, '10 rue Fondaudège', 'Bordeaux', 4, 150.00, 'maisonPierreBdx.jpg', 1, 1, 1, 1),
(19, 'Chalet Mont-Blanc', 'Chalet avec vue imprenable sur le Mont-Blanc, sauna privé et cheminée.', 3, '48 Route des Pèlerins', 'Chamonix', 5, 300.00, 'chaletMontBlanc.jpg', 1, 1, 1, 0),
(20, 'Chalet Bois Charmant', 'Chalet authentique en bois avec terrasse panoramique, parfait pour les familles.', 3, '22 chemin du Cry', 'Chamonix', 4, 220.00, 'chaletCharmant.jpg', 1, 1, 0, 0),
(21, 'Chalet Alpin Luxe', 'Chalet luxueux avec jacuzzi extérieur, cuisine équipée et 4 chambres.', 3, '67 route des Gaillands', 'Chamonix', 5, 350.00, 'chaletAlpin.jpg', 1, 1, 1, 0),
(22, 'Appartement Vieux-Port', 'Appartement lumineux avec balcon, à 2 minutes à pied du Vieux-Port.', 5, '3 quai du Port', 'Marseille', 3, 90.00, 'appartVieuxPort.jpg', 0, 0, 0, 1),
(23, 'Appartement Prado Chic', 'Appartement haut de gamme près du rond-point du Prado, parfait pour les couples.', 5, '5 avenue du Prado', 'Marseille', 4, 110.00, 'appartPrado.jpg', 1, 1, 0, 1),
(24, 'Appartement Canebière Studio', 'Studio moderne et pratique sur la célèbre Canebière, proche métro.', 5, '89 La Canebière', 'Marseille', 3, 75.00, 'appartCanebiere.jpg', 1, 0, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id_utilisateurs` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `ancienClient` tinyint(1) DEFAULT '0',
  `dateInscription` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_utilisateurs`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id_utilisateurs`, `nom`, `prenom`, `email`, `motDePasse`, `ancienClient`, `dateInscription`) VALUES
(1, 'Celles', 'Alex', 'acelles27@gmail.com', 'mdp', 0, '2025-04-15 19:27:11'),
(2, 'Smith', 'Tom', 'tom.smith@gmail.com', 'ki', 0, '2025-04-15 19:46:29');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
