-------------------------------------------------------


--
-- Dumping data for table `ACADEMIE`
--

INSERT INTO `ACADEMIE` (`ID_ACADEMIE`, `LIB_ACADEMIE`) VALUES
(0, 'ETRANGERE'),
(1, 'OUED EDDHAB-LAGOUIRA'),
(2, 'LAAYOUNE-BOUJDOUR-SKIA LHAMRAA'),
(3, 'GUELMIM-ESSMARA'),
(4, 'AGADIR SOUSS-MASSA-DRAA'),
(5, 'GHARB-CHRARDA-BENI HSSEN'),
(6, 'CHAOUIA-OURDIGHA'),
(7, 'MARRAKECH-TANSIFT-ALHAOUZ'),
(8, 'L\'ORIENTALE-OUJDA'),
(9, 'GRAND CASABLANCA'),
(10, 'RABAT- SALE-ZEMMOUR-ZAIR'),
(11, 'DOUKALA-ABDA'),
(12, 'TADLA-AZILAL'),
(13, 'MEKNES-TAFILALT'),
(14, 'FES-BOULMANE'),
(15, 'TAZA-AL HOUCEIMA-TAOUNATE'),
(16, 'TANGER-TETOUAN'),
(17, 'MILITAIRE'),
(18, 'MAROC'),
(19, 'AUTRE'),
(20, 'CASA-BEN M\'SIK');

-- --------------------------------------------------------


--
-- Dumping data for table `PAYS`
--

INSERT INTO `PAYS` (`ID_PAYS`, `LIB_PAYS`) VALUES
(100, 'EXFRANCE'),
(101, 'DANEMARK'),
(102, 'ISLANDE'),
(103, 'NORVEGE'),
(104, 'SUEDE'),
(105, 'FINLANDE'),
(106, 'ESTONIE'),
(107, 'LETTONIE'),
(108, 'LITHUANIE'),
(109, 'ALLEMAGNE'),
(110, 'AUTRICHE'),
(111, 'BULGARIE'),
(112, 'HONGRIE'),
(113, 'LIECHTENSTEIN'),
(114, 'ROUMANIE'),
(116, 'REPUBLIQUE TCHEQUE'),
(117, 'SLOVAQUIE'),
(118, 'BOSNIE HERZEGOVINE'),
(119, 'CROATIE'),
(121, 'YOUGOSLAVIE'),
(122, 'POLOGNE'),
(123, 'RUSSIE'),
(125, 'ALBANIE'),
(126, 'GRECE'),
(127, 'ITALIE'),
(128, 'SAINT MARIN'),
(129, 'VATICAN'),
(130, 'ANDORRE'),
(131, 'BELGIQUE'),
(132, 'GRANDE BRETAGNE'),
(133, 'GIBRALTAR'),
(134, 'ESPAGNE'),
(135, 'PAY BAS'),
(136, 'IRLANDE'),
(137, 'LUXEMBOURG'),
(138, 'MONACO'),
(139, 'PORTUGAL'),
(140, 'SUISSE'),
(144, 'MALTE'),
(145, 'SLOVENIE'),
(148, 'BIELORUSSIE'),
(151, 'MOLDAVIE'),
(155, 'UKRAINE'),
(201, 'ARABIE SAOUDITE'),
(203, 'IRAK'),
(204, 'IRAN'),
(205, 'LIBAN'),
(206, 'SYRIE'),
(207, 'ISRAEL'),
(208, 'TURQUIE'),
(212, 'AFGHANISTAN'),
(213, 'PAKISTAN'),
(214, 'BHOUTAN'),
(215, 'NEPAL'),
(216, 'CHINE POPULAIRE'),
(217, 'JAPON'),
(219, 'THAILANDE'),
(220, 'PHILIPPINES'),
(222, 'JORDANIE'),
(223, 'INDE'),
(224, 'MYANMAR'),
(225, 'BRUNEI'),
(226, 'SINGAPOUR'),
(227, 'MALAISIE'),
(229, 'MALDIVES'),
(230, 'HONG KONG'),
(231, 'INDONESIE'),
(232, 'MACAO'),
(234, 'CAMBODGE'),
(235, 'SRI LANKA'),
(236, 'TAIWAN'),
(238, 'COREE DU NORD'),
(239, 'COREE DU SUD'),
(240, 'KOWEIT'),
(241, 'LAOS'),
(242, 'MONGOLIE'),
(243, 'VIETNAM'),
(246, 'BANGLADESH'),
(247, 'EMIRATS ARABES UNIS'),
(248, 'QATAR'),
(249, 'BAHREIN'),
(250, 'OMAN'),
(251, 'YEMEN'),
(252, 'ARMENIE'),
(253, 'AZERBAIDJAN'),
(254, 'CHYPRE'),
(255, 'GEORGIE'),
(256, 'KAZAKHSTAN'),
(257, 'KIRGHISTAN'),
(258, 'OUZBEKISTAN'),
(259, 'TADJIKISTAN'),
(260, 'TURKMENISTAN'),
(261, 'PALESTINE'),
(301, 'EGYPTE'),
(302, 'LIBERIA'),
(303, 'AFRIQUE DU SUD'),
(304, 'GAMBIE'),
(306, 'STE HELENE'),
(308, 'CHAGOS(ARCHIPEL)'),
(309, 'TANZANIE'),
(310, 'ZIMBABWE'),
(311, 'NAMIBIE'),
(312, 'ZAIRE'),
(314, 'GUINEE EQUATORIALE'),
(315, 'ETHIOPIE'),
(316, 'LIBYE'),
(317, 'ERYTHREE'),
(318, 'SOMALIE'),
(321, 'BURUNDI'),
(322, 'CAMEROUN'),
(323, 'CENTRAFRIQUE'),
(324, 'CONGO'),
(326, 'COTE D"IVOIRE'),
(327, 'BENIN'),
(328, 'GABON'),
(329, 'GHANA'),
(330, 'GUINEE'),
(331, 'BURKINA FASO'),
(332, 'KENYA'),
(333, 'MADAGASCAR'),
(334, 'MALAWI'),
(335, 'MALI'),
(336, 'MAURITANIE'),
(337, 'NIGER'),
(338, 'NIGERIA'),
(339, 'OUGANDA'),
(340, 'RWANDA'),
(341, 'SENEGAL'),
(342, 'SIERRA LEONE'),
(343, 'SOUDAN'),
(344, 'TCHAD'),
(345, 'TOGO'),
(346, 'ZAMBIE'),
(347, 'BOTSWANA'),
(348, 'LESOTHO'),
(350, 'MAROC'),
(351, 'TUNISIE'),
(352, 'ALGERIE'),
(390, 'ILE MAURICE'),
(391, 'SWAZILAND'),
(392, 'GUINEE BISSAU'),
(393, 'MOZAMBIQUE'),
(394, 'SAO TOME ET PRINCIPE'),
(395, 'ANGOLA'),
(396, 'CAP VERT'),
(397, 'COMORES'),
(398, 'SEYCHELLES'),
(399, 'DJIBOUTI'),
(401, 'CANADA'),
(404, 'ETATS UNIS'),
(405, 'MEXIQUE'),
(406, 'COSTA RICA'),
(407, 'CUBA'),
(408, 'SAINT DOMINGUE'),
(409, 'GUATEMALA'),
(410, 'HAITI'),
(411, 'HONDURAS'),
(412, 'NICARAGUA'),
(413, 'PANAMA'),
(414, 'EL SALVADOR'),
(415, 'ARGENTINE'),
(416, 'BRESIL'),
(417, 'CHILI'),
(418, 'BOLIVIE'),
(419, 'COLOMBIE'),
(420, 'EQUATEUR'),
(421, 'PARAGUAY'),
(422, 'PEROU'),
(423, 'URUGUAY'),
(424, 'VENEZUELA'),
(426, 'JAMAIQUE'),
(427, 'ILES MALOUINES'),
(428, 'GUYANE'),
(429, 'BELIZE'),
(431, 'ANTILLES NEERLANDAISES'),
(432, 'PORTO RICO'),
(433, 'TRINITE ET TOBAGO'),
(434, 'BARBADE'),
(435, 'GRENADE ETGRENADINES'),
(436, 'BAHAMAS'),
(437, 'SURINAM'),
(438, 'DOMINIQUE'),
(439, 'SAINTE LUCIE'),
(440, 'SAINT VINCENT'),
(441, 'ANTIGUA ET BARBUDA'),
(442, 'ST CHRISTOPHE NIEVES'),
(501, 'AUSTRALIE'),
(502, 'NOUVELLE ZELANDE'),
(505, 'ILES DU PACIFIQUE'),
(506, 'SAMOA OCCIDENTALES'),
(507, 'NAURU'),
(508, 'FIDJI'),
(509, 'TONGA OU FRIENDLY'),
(510, 'PAPOUASIE NOUVELLE GUINEE'),
(511, 'TUVALU'),
(512, 'SALOMON'),
(513, 'KIRIBATI'),
(514, 'VANUATU'),
(515, 'ILE MARSHALL'),
(516, 'MICRONESIE'),
(517, 'REPUBLIQUE DES ILES PALAOS'),
(519, 'ARUBA'),
(521, 'BERMUDES'),
(522, 'GUADELOUPE'),
(523, 'GUAM'),
(524, 'GUYANA'),
(525, 'ILES CAIMAN'),
(526, 'ILES COOK'),
(527, 'ILES VIERGES (ETATS UNIS)'),
(528, 'ILES VIERGES (ROYAUME UNIS)'),
(529, 'LA REUNION'),
(530, 'MARTINIQUE'),
(532, 'NIOUE'),
(533, 'NOUVELLE CALEDONIE'),
(535, 'POLYNESIE FRANCAISE'),
(537, 'SAINT KITTS ET NEVIS'),
(538, 'SAINT SIEGE'),
(540, 'SAMOA AMERICAINES'),
(990, 'AUTRES pays'),
(996, 'FRANCE');

-- --------------------------------------------------------
--
-- Dumping data for table `PROVINCE`
--

INSERT INTO `PROVINCE` (`ID_PROVINCE`, `LIB_PROVINCE`) VALUES
(1, 'RABAT'),
(2, 'SALE BOUKNADEL'),
(3, 'SKHIRATE-TEMARA'),
(4, 'CASABLANCA ANFA'),
(5, 'AL FIDA DERB SULTAN'),
(6, 'SIDI BENNOUR'),
(7, 'AIN SEBAA HAY MOHAMMEDI'),
(8, 'AIN CHOCK HAY HASSANI'),
(9, 'SIDI BERNOUSSI ZENATA'),
(10, 'BEN MSICK MEDIOUNA'),
(11, 'MOHAMMADIA'),
(12, 'FES-EL-JADID-DAR DBIBAGH'),
(13, 'FES-MEDINA'),
(14, 'ZOUAGHA - MOULAY-YACOUB'),
(15, 'SEFROU'),
(16, 'MARRAKECH-MENARA'),
(17, 'MARRAKECH-MEDINA'),
(18, 'SIDI YOUSSEF BEN ALI'),
(19, 'CHICHAOUA'),
(20, 'AL HAOUZ'),
(21, 'MEKNES-EL-MENZEH'),
(22, 'MEKNES-AL-ISMAILIA'),
(23, 'EL-HAJEB'),
(24, 'OUJDA-ANGAD'),
(25, 'BERKANE'),
(26, 'JERADA'),
(27, 'AGADIR IDA-TANANE'),
(28, 'INZEGANE-AIT-MELLOUL'),
(29, 'CHTOUKA-AIT-BAHA'),
(30, 'LARACHE'),
(31, 'CHEFCHAOUEN'),
(32, 'TETOUAN'),
(33, 'LAAYOUNE'),
(34, 'BOUJDOUR'),
(35, 'AL HOCEIMA'),
(36, 'ASSA-ZAG'),
(37, 'AZILAL'),
(38, 'BENI MELLAL'),
(39, 'BENSLIMANE'),
(40, 'BOULEMANE'),
(41, 'EL JADIDA'),
(42, 'KELAAT  ESSRAGHNA'),
(43, 'ERRACHIDIA'),
(44, 'ESSAOUIRA'),
(45, 'ES-SEMARA'),
(46, 'FIGUIG'),
(47, 'GUELMIM'),
(48, 'IFRANE'),
(49, 'KENITRA'),
(50, 'KHEMISSET'),
(51, 'KHENIFRA'),
(52, 'KHOURIBGA'),
(53, 'NADOR'),
(54, 'OUARZAZATE'),
(55, 'OUED ED DAHAB'),
(56, 'SAFI'),
(57, 'SETTAT'),
(58, 'SIDI KACEM'),
(59, 'TANGER'),
(60, 'TAN-TAN'),
(61, 'TAOUNATE'),
(62, 'TAROUDANNT'),
(63, 'TATA'),
(64, 'TAZA'),
(65, 'TIZNIT'),
(66, 'ZAGORA'),
(67, 'TAOURIRT'),
(68, 'MOULAY RCHID SIDI OTMANE'),
(69, 'FAHS BNI MAKADA'),
(71, 'SALA ALJADIDA'),
(72, 'MECHOUAR(CASA)'),
(73, 'ETRANGER'),
(74, 'MAROC'),
(76, 'MAROC MILITAIRE'),
(77, 'Sidi Slimane'),
(78, 'Sidi Yahya'),
(79, 'Ouezzane'),
(80, 'NOUASSER'),
(81, 'EL MADIEQ'),
(82, 'Etranger'),
(83, 'eddakhla'),
(84, 'Ouezzane'),
(85, 'Guercif'),
(86, 'Driouch'),
(87, 'Tarfaya'),
(88, 'Midelt'),
(89, 'Sidi Ifni'),
(90, 'Tinghir'),
(91, 'Hay Hassani'),
(92, 'youssoufia'),
(93, 'Berrechid'),
(997, 'AOUSSERD'),
(998, 'FKIH BEN SALEH'),
(999, 'BENGUERIR');

-- --------------------------------------------------------

--
-- Dumping data for table `SERIE_BAC`
--

INSERT INTO `SERIE_BAC` (`ID_SERIE_BAC`, `LIB_SERIE_BAC`) VALUES
(1, 'Agriculture'),
(2, 'Architecture'),
(3, 'Arts et Industrie Graphique'),
(4, 'Arts Plastiques'),
(5, 'Bac C'),
(6, 'Bac D'),
(7, 'Bac E'),
(8, 'Bac F'),
(9, 'Bac G'),
(10, 'Bac Mission'),
(11, 'Batiments et Travaux Publiques'),
(12, 'Capacité en Droi'),
(13, 'Chimie'),
(14, 'Chimie Industrielle'),
(15, 'Comptabilité'),
(16, 'Economie nouvelle'),
(17, 'Electrochimie'),
(18, 'Electronique'),
(19, 'Electrotechnique'),
(20, 'Fabrication Mécaniqu'),
(21, 'Fonderie'),
(22, 'Froid et Climatisation'),
(23, 'Réception Hôtelière'),
(24, 'Lettres'),
(25, 'Lettres Modernes Bilingues'),
(26, 'Lettres Originelles'),
(27, 'Lettres Originelles Arabisée'),
(28, 'Mathématiques-Technique'),
(29, 'Mécanique Aut'),
(30, 'Mécanique ingénier'),
(31, 'Micro-Mécaniqu'),
(32, 'Sciences Agronomiques'),
(33, 'Sciences de la Nature'),
(34, 'Sciences Economiques'),
(35, 'Sciences Expérimentales Arabisé'),
(36, 'Sciences Expérimentales Bilingue'),
(37, 'Sciences Mathématique'),
(38, 'Sciences Techniques'),
(39, 'Secrétaria'),
(40, 'Section Anglaise'),
(41, 'Section Espagnole'),
(42, 'Techniques Commerciales'),
(43, 'Techniques Industrielles'),
(44, 'Techniques Quantitatives de Gestion'),
(45, 'Autres'),
(46, 'Construction Mécaniqu'),
(47, 'Bac S'),
(48, 'Bac A'),
(49, 'Bac Technique'),
(50, 'Techniques de Gestion Administrative'),
(51, 'Techniques de Gestion Comptable'),
(52, 'Techniques Organisation Administrative'),
(53, 'Techniques Organisation Comptable'),
(54, 'Génie Mécaniq'),
(55, 'Génie Industrie'),
(56, 'Génie Chimiqu'),
(57, 'Informatique'),
(58, 'Gestion Bureau'),
(59, 'Beaux Arts'),
(98, 'Série de Bac Non Spécifi'),
(99, 'Bac Etranger'),
(100, 'Chariaâ Originell'),
(101, 'Lettres Modernes Arabisée'),
(102, 'Génie Civi'),
(103, 'Lettres Spécialité Lang'),
(104, 'Education physique'),
(105, 'Sciences'),
(106, 'Techniques'),
(107, 'Lettres Modernes'),
(109, 'Conception et batiment'),
(110, 'Lettres Modernes Spécialité Franç'),
(111, 'Lettres Modernes Spécialité Allman'),
(112, 'Sciences Expérimentale'),
(113, 'Sciences Expérimentales Spc Allman'),
(114, 'Sciences Expérimentales Spc Espango'),
(115, 'Sciences Expérimentales Spc Anglai'),
(116, 'Sciences Expérimentales Spc França'),
(117, 'Sciences Mathématiques (A'),
(118, 'Sciences Mathématiques (B'),
(119, 'Sciences Mathématiques SpcFrança'),
(120, 'Lettres Modernes Spécialité  Angla'),
(121, 'Lettres Modernes Spécialité Espagno'),
(201, 'Sciences et Técnologie Mécaniq'),
(202, 'Physique Chimie'),
(203, 'Sciences de gestion Comp'),
(204, 'Sciences de la Vie et de la Terre'),
(205, 'Langue Arabe'),
(206, 'Lettres Et SC Humaines'),
(207, 'Sciences Humaines'),
(208, 'Sc. Eco. Et Gestion'),
(209, 'Sc. Et Techno. Electri'),
(965, 'Systèmes Electroniques Numerique'),
(966, 'Services de Restauration'),
(967, 'PROF-Froid et Conditionnement d air'),
(969, 'FILIERE SC. ET TECHNO. MECANIQUE'),
(970, 'SVT -Option Anglais'),
(971, 'PROF -Electrotechnique,Equipts.Com'),
(972, 'PROF- Cond.exploitation agricole'),
(973, 'ATIQ'),
(974, 'FILIERE SCIENCES CHARIA'),
(975, 'PROF -Systèmes Electroniques Numériqu'),
(976, 'PROF -Comptabilité'),
(978, 'PROF -Gros Ouvres du batiment'),
(979, 'PROF -Stylisme Modélism'),
(980, 'PROF -logistique'),
(981, 'PROF -Maintenance Informatique et Résea'),
(982, 'PROF -Dessin de Batiment'),
(983, 'PROF -Construction Métalliqu'),
(984, 'PROF -Commerce'),
(985, 'PROF -Maint de Véhicules Auto -Voitures'),
(986, 'PROF -Arts Culinaires'),
(987, 'SVT - Option Françai'),
(988, 'Lettres - Option Françai'),
(989, 'Sc.Physiques - Option Françai'),
(990, 'Sc.Math A -Option Françai'),
(991, 'Sc.Math B -Option Françai'),
(992, 'Bac prof- Cond.exploitation agrico'),
(993, 'Bac Prof- Construction Aéronauti'),
(994, 'Bac Prof- Industrie Mécaniqu'),
(995, 'Bac Prof- Maintenace Industielle'),
(996, 'Sciences Physiques -Option Ang'),
(997, 'Sc.Math B -Option Anglais'),
(998, 'Sc.Math A -Option Anglais'),
(999, 'Arts Appliquée');

-- --------------------------------------------------------
--
-- Dumping data for table `ETAPE`
--
INSERT INTO `ETAPE` (`COD_ETP`, `LIB_ETP`) VALUES
('GITCT1', '1ère année Tronc Commun'),
('GITCT2', '2ème année Tronc Commun'),
('GITCT3', '3ème année Tronc Commun'),
('GITCC1', '4ème année Commerce'),
('GITCG1', '4ème année Gestion'),
('GIACG5', '5ème année ACG'),
('GIGFC5', '5ème année GFC'),
('GICI5', '5ème année CI'),
('GIMAC5', '5ème année MAC'),
('GIMRH5', '5ème année MRH');


--
-- Dumping data for table `role`
--

INSERT INTO `ROLE` (`ID_ROLE`, `LIB_ROLE`) VALUES
(1, 'ADMIN'),
(2, 'USER');






