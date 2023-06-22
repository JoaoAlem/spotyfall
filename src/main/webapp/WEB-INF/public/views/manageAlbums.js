export default {
    template: `
    <template v-if="$root.user && $root.artist?.id_artist && AllAlbuns">
        <div>
            <div class="w-fit mx-auto h-fit card-borders p-0.5 rounded-3xl mb-10">
                <div class="p-3 sm:p-0 bg-padrao flex flex-col sm:flex-row items-center rounded-3xl">
                    <div class="p-3 my-auto text-white flex flex-col">
                        <h1 class="font-bold text-md xs:text-xl sm:text-2xl"><i class="fa-solid fa-book"></i> Meus albuns</h1>
                        <button @click="$root.openModal('modal-edit')"><i class="fa-solid fa-plus"></i> Adicionar</button>
                    </div>
                </div>
            </div>
            
             <div class="m-auto w-full h-full rounded-xl p-3">
                <!-- cabecalho tabela -->
                <div class="mx-auto flex flex-row text-white space-x-10 items-center">
                    <h4 class="ml-3 w-12 text-center">#</h4>
                    <h4 class="mx-auto w-16 text-center"></h4>
                    <h4 class="mx-auto w-full text-center">Titulo</h4>
                    <h4 class="hidden lg:inline w-full mx-auto text-center">Data de adição</h4>
                    <h4 class="hidden lg:inline w-full mx-auto text-center">Ações</h4>
                </div>
                <!-- celula -->
                <template v-for="album in getAlbum">
                    <div class="w-full my-3 h-max card-borders p-0.5 rounded-3xl">
                        <div class="bg-padrao mx-auto h-16 flex flex-row text-white rounded-3xl space-x-10 items-center">
                            <p class="ml-3 w-12 text-center">{{album.id_album}}</p>
                            <div class="w-16 ml-3 h-full p-1 basis-2/6">
                                <img :src="album.getImageURL()" class="w-16 object-contain ml-3 mr-auto h-full text-center"/>
                            </div>
                            <p class="mx-auto w-full truncate text-center">{{album.albumName}} </p>
                            <input class="hidden lg:inline bg-transparent text-center w-full" readonly type="text" v-model="album.createDate"/>
                            <div class="w-full mx-auto flex flex-row justify-center align-center">
                                <button class="mx-auto" @click="edit(album.id_album)" key='edit'><i style="color:#f6f6f6" class="w-6 fas fa-edit"></i></button>
                                <button class="mx-auto" @click="deleteAlbum(album.id_album)" key="delete"><i style="color:#f6f6f6" class="w-6 fas fa-trash-can"></i></button>
                            </div>
                        </div>
                    </div>
                </template>
            </div>
        </div>
        
        <edit-albums :album="AlbumEditing"></edit-albums>
    </template>
    `,
    data(){
        return{
            AllAlbuns: null,
            AlbumEditing: this.$root.AlbunsModel()
        }
    },
    computed:{
        getAlbum(){
            if(!this.AllAlbuns)
                return this.$root.AlbunsModel()

            return this.AllAlbuns.map(item => {
                let album = this.$root.AlbunsModel(item)
                album.createDate = this.getData(album.createDate)

                return album
            })
        }
    },
    created() {
        if(!this.$root.user)
             return this.$root.$router.replace("/login")

        if(!this.$root.artist?.id_artist){
            this.$root.showError("Para acessar essa pagina você precisa ser um artista")
            return this.$root.$router.replace("/user")
        }

        this.$root.request("albumController", "post", {id_artist: this.$root.artist.id_artist})
            .then( resp => this.AllAlbuns = resp.data )
    },
    methods:{
        reload(){
            this.$root.request("albumController", "post", {id_artist: this.$root.artist.id_artist})
                .then( resp => this.AllAlbuns = resp.data )
        },
        getData(createDate){
            const createDateArray = JSON.parse(createDate);
            const [year, month, day] = createDateArray;
            const _createDate = new Date(year, month - 1, day);

            const options = { day: 'numeric', month: 'numeric', year: 'numeric' };
            return _createDate.toLocaleDateString('pt-BR', options);
        },
        changeVisibility(item){
            console.log(item)
        },
        openModal(item, id){
            let modal = document.getElementById(id)
            modal.classList.add("flex")
            modal.classList.add("flex-col")
            modal.classList.remove("hidden")
        },
        edit(id){
            this.$root.request("albumController", "post", {id_album: id})
                .then( resp => {
                    let album = this.$root.AlbunsModel(resp.data)
                    album.createDate = this.getData(album.createDate)

                    this.AlbumEditing = album
                    this.$nextTick(() => {
                        this.$root.openModal('modal-edit')
                    })
                })
        },
        deleteAlbum(id){
            let data = new FormData();
            data.append("id_album", id);

            console.log(id)
            this.$root.request("albumController", "delete", data)
                .then(() => {
                    this.$root.showSuccess("Album deletado com sucesso")
                    this.reload()
                })
                .catch(error => this.$root.showError("Erro ao deletar o album"))
        }
    }
}