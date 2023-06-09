export default {
    template: `
        <template v-if="$root.user">
            <div class="flex flex-col lg:flex-row w-full h-fit my-auto justify-center items-center text-[#f6f6f6] space-y-5 lg:space-x-5 lg:space-y-0">
                <div class="flex flex-col w-full xl:w-[30%] max-w-7xl lg:h-full max-h-fit rounded-2xl p-4 text-center space-y-5" style="background-color: #4b14a3;">
                    <h2 class="text-xl"><strong>Meu perfil</strong></h2>
                    <div class="w-24 h-24 md:w-40 md:h-40 bg-[#bbacf5] rounded-full mx-auto">
                        <img src="" alt=""/>
                    </div>
                    <div style="margin-block: auto !important;">
                        <div class="text-start space-y-1">
                            <label for="name-user">Nome</label>
                            <input :value="$root.user.name + ' ' + $root.user.surname" class="bg-[#bbacf5] border-none p-2 rounded-2xl w-full h-10 text-black" id="name-user" type="text" disabled/>
                        </div>
                        <div class="text-start space-y-1">
                            <label for="phone-user">Telefone</label>
                           <input v-model="$root.user.phone" class="bg-[#bbacf5] border-none p-2 rounded-2xl w-full h-10 text-black" id="phone-user" type="text" disabled/>
                        </div>
                        <div class="text-start space-y-1">
                            <label for="email-user">E-mail</label>
                            <input v-model="$root.user.email" class="bg-[#bbacf5] border-none p-2 rounded-2xl w-full h-10 text-black" id="email-user" type="text" disabled/>
                        </div>
                    </div>
                    <p>Caso queira saber mais sobre nós, leia as 
                        <router-link to="/privacy" class="underline decoration-wavy">Politicas de privacidade</router-link> ou 
                        <router-link to="/technologies" class="underline decoration-wavy">Tecnologias usadas</router-link>
                    </p>
                </div>
                <div class="flex flex-col w-full xl:w-[50%] max-w-7xl h-full rounded-2xl p-4 border text-xl indent-8 space-y-5" style="border-color: #7700a6">
                    <h2 class="text-center"><strong>Venha contribuir!</strong></h2>
                    <p>Um artista é uma pessoa soma com essa comunidade, oferecendo suas músicas.</p>
                    <p>Nós da Spotyfall gostamos muito de novos talentos contribuindo com essa comunidade, assim como gostamos de músicas novas todos os dias. Somos fascinados em música.</p>
                    <div class="space-y-5">
                        <p>Você pode ter acesso a algumas vantagens, como:</p>
                        <ul class="list-disc list-inside">
                            <li>Poder fazer uploads;</li>
                            <li>Manter albuns;</li>
                            <li>Compartilhar conteúdo na comunidade.</li>
                        </ul>
                    </div>
                    <button @click="saveArtist" class="ml-auto border rounded-xl p-2 mr-3 lg:mr-7" style="border-color: #7700a6; margin-top: auto !important;">Torne-se um artista</button>     
                </div>
            </div> 
        </template>
    `,
    created() {
        if(!this.$root.user)
            this.$root.$router.replace("/login")
    },
    methods:{
        saveArtist(){
            let data = new FormData()
            data.append("id_user", this.$root.user?.id_user)

            this.$root.request("artistsController", "put", data)
                .then( () => {
                    this.$root.showSuccess("Agora vocé um artista")
                    this.$root.setAppArtist()
                })
                .catch( error => this.$root.showError("Não foi possivel completar sua requisição" + error))
        }
    }
}