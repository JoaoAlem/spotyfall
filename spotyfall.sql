create database if not exists spotyfall;
use spotyfall;

create table if not exists users(
    id char(36) primary key default uuid(),
    name varchar(50) not null,
    surname varchar(50) not null,
    username varchar(30) not null unique,
    email varchar(255) not null unique,
    phone varchar(11) not null unique,
    password varchar(30) not null,
    userImage varchar(50) null default 'placeholder.webp',
    createDate datetime default now(),
    updateDate datetime default now() ON UPDATE now(),
    deleteDate datetime null
);

CREATE TABLE playlists (
    id INT NOT NULL AUTO_INCREMENT,
    playlistName VARCHAR(50) NOT NULL,
    playlistsImage VARCHAR(50) NOT NULL,
    playlistsDescricao VARCHAR(50) NOT NULL,
    createDate DATETIME NOT NULL DEFAULT NOW(),
    updateDate DATETIME NOT NULL DEFAULT NOW() ON UPDATE now(),
    deleteDate DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX idplaylists_UNIQUE (id ASC) VISIBLE
);