export default {
    template: `
    <template v-if="$root.user">
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
                <template v-for="item in 13">
                    <div class="w-full my-3 h-max card-borders p-0.5 rounded-3xl">
                        <div class="bg-padrao mx-auto h-16 flex flex-row text-white rounded-3xl space-x-10 items-center">
                            <p class="ml-3 w-12 text-center truncate">{{item}}</p>
                            <div class="w-16 ml-3 h-full p-1">
                                <img class="ml-3 mr-auto h-full text-center"/>
                            </div>
                            <p class="mx-auto w-full truncate text-center">teste </p>
                            <input class="hidden lg:inline bg-transparent text-center w-full" readonly type="text" value="13/03/2023"/>
                            <div class="w-full mx-auto flex flex-row justify-center align-center">
                                <button class="mx-auto" @click="changeVisibility(item)"><i style="color:#ffffff" class="w-6 fas fa-eye-slash"></i></button>
                                <button class="mx-auto" @click="openModal(item, 'modal-edit')"><i style="color:#f6f6f6" class="w-6 fas fa-edit"></i></button>
                                <button class="mx-auto" @click="delete(item)"><i style="color:#f6f6f6" class="w-6 fas fa-trash-can"></i></button>
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
    created() {
        if(!this.$root.user)
            this.$root.$router.replace("/login")
    },
    methods:{
        changeVisibility(item){
            console.log(item)
        },
        openModal(item, id){
            let modal = document.getElementById(id)
            modal.classList.add("flex")
            modal.classList.add("flex-col")
            modal.classList.remove("hidden")
        },
        delete(item){

        },

    },
    mounted(){
        //this.AllMusics = this.$root.request("musicsController", "get", this.filters)
    }
}