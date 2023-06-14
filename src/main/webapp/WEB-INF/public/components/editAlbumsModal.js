export default {
    props: ['album'],
    template: `
    <div style="margin: 0 !important;" id="modal-edit" class="fixed h-screen w-screen top-2/4 left-2/4 -translate-y-1/2 -translate-x-1/2 hidden z-10 rounded-lg">
        <!-- background -->
        <div class="opacity-25 bg-black h-screen w-screen absolute top-0 left-0 z-0"></div>
        
        <!-- form -->
        <form @submit.prevent style="background-color: var(--sidebar)" class="flex flex-col z-10 m-auto text-white">
            <!-- Form Title -->
            <div class="flex mx-auto mb-4">
                <h2 class="mr-auto ml-2 text-lg" v-if="album.id_album">Editando {{item.title}}</h2>
                <h2 class="mr-auto ml-2 text-lg" v-else>Adicionar Album</h2>
            </div>
            
            <!-- Form body -->
            <div class="flex flex-col mx-auto whitespace-nowrap mb-2 w-3/4">
                <label for="form-title">Nome do album: </label>
                <input class="rounded-lg w-full whitespace-nowrap border-white border-2" style="background-color: var(--fundo)" v-model="album.albumName" id="form-title" type="text">
            </div>
            
             <div class="flex flex-col mx-auto mb-2 w-3/4">
                <label for="form-type">Tipo do album</label>
                <select id="form-type" class="rounded-lg border-white border-2" style="background-color: var(--fundo)" v-model="album.tipo">
                   <option value="single">Single</option>
                   <option value="album">Album</option>
                </select>
            </div>
            
            <div class="flex flex-col mx-auto mb-2 w-3/4">
                <label for="">Imagem do album: </label>
                <input ref="inputFile" @change="handleUpload" type="file" accept=".webp, .jpg, .jpeg, .png, .gif" class="hidden">
                <div class="h-20 w-20 mx-auto rounded-lg flex flex-col" style="background-color: var(--sobretomRoxo)">
                    <button type="button" @click="$refs.inputFile.click()" class="w-full h-full border-0 bg-transparent mx-auto">
                        <i class="fa-solid fa-upload" style="color: #ffffff;"></i>
                    </button>
                </div>
                <p>{{selectedFile?.name ?? "Nenhum arquivo selecionado"}}</p>
            </div>
           
            <!-- Form footer -->
            <div class="flex flex-row ml-auto justify-between">
                <button @click="this.$root.closeModal('modal-edit')" class="w-10 h-7 bg-gray-500"><i class="fa fa-close"></i></button>
                <button @click="save" class="w-10 h-7 bg-green-400"><i class="fa fa-check"></i></button>
            </div>
        </form>
    </div>
    `,
    data(){
        return{
            selectedFile: null,
        }
    },
    methods: {
        mounted(){

        },
        handleUpload(){
            this.selectedFile = this.$refs.inputFile.files[0]
        },
        save(){
            let data = new FormData();

            data.append("id_album", this.album.id_album)
            data.append("albumName", this.album.albumName)
            data.append("tipo", this.album.tipo)
            data.append("id_artist", "0201f2c1-0979-11ee-9e3e-0242ac110002")

            if(this.selectedFile !== null){
                data.append("albumImage", this.selectedFile)
            }


            this.$root.request("albumController", "put", data)
                .then((response) => {
                    this.$root.closeModal("modal-edit")
                    this.$root.showSuccess("Album cadastrado")

                    // Limpar
                    this.album = this.$root.AlbunsModel()
                    this.selectedFile = null
                })
                .catch(() => this.$root.showError("Não foi possivel cadastrar o album, tente novamente mais tarde"))
        }
    }
}