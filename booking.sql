-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 17 avr. 2025 à 20:30
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
  `idLogement` int DEFAULT NULL,
  `dateDisponible` date DEFAULT NULL,
  `estDisponible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idLogement` (`idLogement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `logements`
--

DROP TABLE IF EXISTS `logements`;
CREATE TABLE IF NOT EXISTS `logements` (
  `id_logements` int NOT NULL AUTO_INCREMENT,
  `id_site` int DEFAULT NULL,
  `description` text,
  `prixParNuit` decimal(10,2) DEFAULT NULL,
  `wifi` tinyint(1) DEFAULT '0',
  `climatisation` tinyint(1) DEFAULT '0',
  `fumeur` tinyint(1) DEFAULT '0',
  `petitDejeuner` tinyint(1) DEFAULT '0',
  `vueMer` tinyint(1) DEFAULT '0',
  `minibar` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_logements`),
  KEY `id_site` (`id_site`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `logements`
--

INSERT INTO `logements` (`id_logements`, `id_site`, `description`, `prixParNuit`, `wifi`, `climatisation`, `fumeur`, `petitDejeuner`, `vueMer`, `minibar`) VALUES
(1, 1, 'Studio moderne avec WiFi et minibar', 95.00, 1, 1, 0, 1, 0, 1),
(2, 2, 'Petit studio confortable à Lyon', 70.00, 1, 1, 0, 0, 0, 0),
(3, 3, 'Chambre vue mer avec piscine privée', 250.00, 1, 1, 0, 1, 1, 1),
(4, 4, 'Chambre économique pour une personne', 55.00, 1, 0, 1, 0, 0, 0),
(5, 5, 'Maison avec jardin, parfaite pour familles', 130.00, 1, 1, 1, 1, 0, 1),
(6, 6, 'Appartement climatisé au centre-ville', 110.00, 1, 1, 0, 1, 0, 0),
(7, 7, 'Tente équipée dans la nature', 65.00, 0, 0, 0, 1, 0, 0),
(8, 8, 'Loft spacieux avec terrasse', 145.00, 1, 1, 0, 1, 0, 1),
(9, 9, 'Chambre privative en colocation', 40.00, 1, 0, 1, 0, 0, 0),
(10, 10, 'Suite luxueuse avec minibar et climatisation', 175.00, 1, 1, 0, 1, 0, 1);

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
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(10, 'Suite Strasbourg Minibar', 'Suite avec minibar à Strasbourg.', 1, '10 rue de la République', 'Strasbourg', 5, 175.00, 'suiteStrasbourg.jpg', 1, 1, 1, 1);

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
