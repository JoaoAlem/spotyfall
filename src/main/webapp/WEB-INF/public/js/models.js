import { define } from './utils.js'
export class userModel {
    constructor(i){
        define(this, i,
        {
                id_user: null,
                name: null,
                surname: null,
                username: null,
                email: null,
                password: null,
                phone: null,
                userImage: null,
            }
        )
    }
}

export class artistsModel{
    constructor(i) {
        define(this, i, {
            id_artist: null,
            tradeName: null
        })
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

export class albumModel {
    constructor(i){
        define(this, i, {
            id_album: null,
            albumName: null,
            albumImage: null,
            tipo: null,
            createDate: null,
            id_artist: null,
        })
    }

    getImageURL(){
        const {origin} = window.location
        return `${origin}/spotyfall_war_exploded/public/albumImages/${this.albumImage}`
    }
}