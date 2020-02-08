DROP DATABASE IF EXISTS DVDTHEQUE;
CREATE DATABASE DVDTHEQUE;

CREATE TABLE DVDTHEQUE.Realisateur(
        Id_Realisateur   Int Auto_increment NOT NULL PRIMARY KEY,
        Nom_Realisateur      Varchar (50) NOT NULL,
        Prenom_Realisateur   Varchar (50) NOT NULL
);

CREATE TABLE DVDTHEQUE.Nationnalite(
        Id_Nationnalite   Int Auto_increment NOT NULL PRIMARY KEY,
        Libelle_Nationnalite      Varchar (250) NOT NULL
);

CREATE TABLE DVDTHEQUE.Film(
        Id_Film     Int  Auto_increment  NOT NULL PRIMARY KEY,
        Nom_Film    Varchar (50) NOT NULL,
        Annee_Film  Int (250) NOT NULL,
        Note_Film   Int (250) NOT NULL,
        Resume_Film Varchar (500) NOT NULL,
        Image_Film  Varchar (250) NOT NULL,
        Realisateur_id int,
        Nationnalite_id int,
        CONSTRAINT fkRealFilm FOREIGN KEY (Realisateur_id) REFERENCES Realisateur(Id_Realisateur),
        CONSTRAINT fkNationnaliteFilm FOREIGN KEY (Nationnalite_id) REFERENCES Nationnalite(Id_Nationnalite)
);

CREATE TABLE DVDTHEQUE.Acteur(
        Id_Acteur       Int Auto_increment NOT NULL PRIMARY KEY,
        Nom_Acteur      Varchar (50) NOT NULL,
        Prenom_Acteur   Varchar (50) NOT NULL
);


CREATE TABLE DVDTHEQUE.Genre(
        Id_Genre        Int Auto_increment NOT NULL PRIMARY KEY,
        Libelle_Genre   Varchar (50) NOT NULL
);

CREATE TABLE DVDTHEQUE.Film_Acteur(
        Film_id    Int,
        Acteur_id   Int,
        PRIMARY KEY(Film_id,Acteur_id),
        CONSTRAINT fkFilmActeur1 FOREIGN KEY (Film_id) REFERENCES Film(Id_Film),
        CONSTRAINT fkFilmActeur2 FOREIGN KEY (Acteur_id) REFERENCES Acteur(Id_Acteur)
);

CREATE TABLE DVDTHEQUE.Film_Genre(
        Film_id    Int,
        Genre_id   Int,
        PRIMARY KEY(Film_id,Genre_id),
        CONSTRAINT fkFilmGenre1 FOREIGN KEY (Film_id) REFERENCES Film(Id_Film),
        CONSTRAINT fkFilmGenre2 FOREIGN KEY (Genre_id) REFERENCES Genre(Id_Genre)
);

INSERT INTO DVDTHEQUE.Realisateur VALUES (1, 'pablo', 'juju');
INSERT INTO DVDTHEQUE.Nationnalite VALUES (1, 'Francaise');
INSERT INTO DVDTHEQUE.Film  VALUES (1,'CInquieme Element', 4444, 5, 'ferofk_elrfkeref', 'ijfokreferf', 1, 1);


