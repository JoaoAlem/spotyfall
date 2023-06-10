drop database if exists spotyfall;
create database if not exists spotyfall;
use spotyfall;

/* Tabelas da aplicação */
CREATE TABLE IF NOT EXISTS users
(
    id_user    CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name       VARCHAR(50)  NOT NULL,
    surname    VARCHAR(50)  NOT NULL,
    username   VARCHAR(30)  NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    phone      VARCHAR(11)  NOT NULL UNIQUE,
    password   VARCHAR(30)  NOT NULL,
    userImage  VARCHAR(70)  NULL       DEFAULT 'userPlaceholder.webp',
    createDate DATETIME                DEFAULT NOW(),
    updateDate DATETIME                DEFAULT NOW() ON UPDATE NOW(),
    deleteDate DATETIME     NULL
);

CREATE TABLE IF NOT EXISTS artists
(
    id_artist  CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_user    VARCHAR(36) UNIQUE,
    tradeName  VARCHAR(50) NULL,
    createDate DATETIME                DEFAULT NOW(),
    updateDate DATETIME                DEFAULT NOW() ON UPDATE NOW(),
    deleteDate DATETIME    NULL,
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE IF NOT EXISTS albums
(
    id_album   INT AUTO_INCREMENT PRIMARY KEY,
    albumName  VARCHAR(50)              NOT NULL,
    albumImage VARCHAR(70)              NULL,
    tipo       ENUM ('single', 'album') not null default 'album',
    createDate DATETIME                          DEFAULT NOW(),
    updateDate DATETIME                          DEFAULT NOW() ON UPDATE NOW(),
    deleteDate DATETIME                 NULL,
    id_artist  VARCHAR(36)              NOT NULL,
    FOREIGN KEY (id_artist) REFERENCES artists (id_artist)
);

CREATE TABLE IF NOT EXISTS playlists
(
    id_playlist   INT AUTO_INCREMENT PRIMARY KEY,
    playlistName  VARCHAR(50)  NOT NULL,
    playlistImage VARCHAR(70)  NULL,
    playlistDesc  VARCHAR(255) NULL,
    createDate    DATETIME DEFAULT NOW(),
    updateDate    DATETIME DEFAULT NOW() ON UPDATE NOW(),
    deleteDate    DATETIME     NULL,
    id_user       VARCHAR(36)  NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE IF NOT EXISTS musics
(
    id_music   INT AUTO_INCREMENT PRIMARY KEY,
    musicName  VARCHAR(50) NOT NULL,
    musicImage VARCHAR(70) NULL,
    createDate DATETIME DEFAULT NOW(),
    updateDate DATETIME DEFAULT NOW() ON UPDATE NOW(),
    deleteDate DATETIME    NULL
);


/* Tabelas de relacionamentos */
-- Musicas de um album
CREATE TABLE IF NOT EXISTS albumMusics
(
    id_album   INT      NOT NULL,
    id_music   INT      NOT NULL,
    createDate DATETIME DEFAULT NOW(),
    deleteDate DATETIME NULL,
    PRIMARY KEY (id_album, id_music),
    FOREIGN KEY (id_album) REFERENCES albums (id_album),
    FOREIGN KEY (id_music) REFERENCES musics (id_music)
);

-- Musicas de uma playlist
CREATE TABLE IF NOT EXISTS playlistMusics
(
    id_music    INT NOT NULL,
    id_playlist INT NOT NULL,
    createDate  DATETIME DEFAULT NOW(),
    PRIMARY KEY (id_music, id_playlist),
    FOREIGN KEY (id_music) REFERENCES musics (id_music),
    FOREIGN KEY (id_playlist) REFERENCES playlists (id_playlist)
);

-- Musicas curtidas
CREATE TABLE IF NOT EXISTS liked
(
    id_music   INT NOT NULL,
    id_user    CHAR(36) NOT NULL,
    createDate DATETIME DEFAULT NOW(),
    PRIMARY KEY (id_music, id_user),
    FOREIGN KEY (id_music) REFERENCES musics (id_music),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

-- Autor da música
CREATE TABLE IF NOT EXISTS musicAuthor
(
    id_music   INT NOT NULL,
    id_artist  CHAR(36) NOT NULL,
    createDate DATETIME DEFAULT NOW(),
    PRIMARY KEY (id_music, id_artist),
    FOREIGN KEY (id_music) REFERENCES musics (id_music),
    FOREIGN KEY (id_artist) REFERENCES artists (id_artist)
);

-- Seguidores
CREATE TABLE IF NOT EXISTS artistsFollowed
(
    id_artist CHAR(36) NOT NULL,
    id_user   CHAR(36) NOT NULL,
    PRIMARY KEY (id_artist, id_user),
    FOREIGN KEY (id_artist) REFERENCES artists (id_artist),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);