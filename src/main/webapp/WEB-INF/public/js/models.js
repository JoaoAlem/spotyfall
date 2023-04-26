import { define } from './utils.js'
export class userModel {
    constructor(i){
        define(this, i,
        {
                id: null,
                name: null,
                surname: null,
                username: null,
                email: null,
                phone: null,
                userImage: null,
            }
        )
    }
}

export class musicModel {
    constructor(i){
        define(this, i, {
            id: null,
            musicName: null,
            musicImage: null,
            albumId: null
        })
    }
}

export class playlistModel {
    constructor(i){
        define(this, i, {
            id: null,
            playlistName: null,
            playlistImage: null,
            playlistDesc: null,
            creationDate: null,
            userId: null
        })
    }
}

export class AlbumModel {
    constructor(i){
        define(this, i, {
            id: null,
            albumName: null,
            albumImage: null,
            creationDate: null,
            idArtist: null,
            idUser: null,
            tipo: null,
        })
    }
}
