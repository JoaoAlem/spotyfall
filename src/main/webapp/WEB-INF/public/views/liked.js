export default {
    template: `
        <div class="w-fit mx-auto sm:w-full h-full card-borders p-0.5 rounded-3xl">
            <div class="p-3 sm:p-0 bg-padrao flex flex-col sm:flex-row h-full items-center rounded-3xl">
                <div class="w-fit my-auto mr-4 sm:h-3/4 ml-7">
                    <img class="border rounded-3xl h-full" src="./public/images/images.png" alt="">
                </div>
                <div class="mx-auto my-auto md:ml-3 md:mr-auto text-white flex flex-col">
                    <h2 class="font-bold text-md xs:text-xl sm:text-2xl">Músicas curtidas</h2>
                    <span class="font-medium text-sm xs:text-md sm:text-lg"></span>
                    <span class="text-sm sm:text-md">quantidade de músicas</span>
                </div>
                <div class="mx-auto my-auto space-x-10 flex">
                    <i class="h-10 md:h-12 lg:h-16 fa-solid fa-play-circle"></i>
                    <i class="h-10 md:h-12 lg:h-16 fa-solid fa-random"></i>
                </div>
            </div>
        </div>
    
        <div class="m-auto w-full h-full rounded-xl p-3">
            <!-- cabecalho tabela -->
            <div class="mx-auto flex flex-row text-white space-x-10 items-center">
                <h4 class="ml-3 w-12 text-center">#</h4>
                <h4 class="mx-auto w-16 text-center"></h4>
                <h4 class="mx-auto w-1/6 text-center">Titulo</h4>
                <h4 class="mx-auto w-1/6 text-center">Album</h4>
                <h4 class="hidden sm:inline w-1/6 mx-auto text-center">Artista</h4>
                <h4 class="hidden lg:inline w-1/6 mx-auto text-center">Adicionada em</h4>
            </div>
            
            <!-- celula -->
            <template v-for="n in 13">
                <div class="w-full my-3 h-max card-borders p-0.5 rounded-3xl">
                    <div class="bg-padrao mx-auto h-10 flex flex-row text-white rounded-3xl space-x-10 items-center">
                        <p class="ml-3 w-12 text-center truncate">{{n}}</p>
                        <div class="w-16 ml-3 h-full p-1">
                            <img src="./public/images/teste.png" class="ml-3 mr-auto h-full text-center"/>
                        </div>
                        <p class="mx-auto w-1/6 truncate text-center">teste </p>
                        <p class="mx-auto w-1/6 truncate text-center">Album</p>
                        <p class="hidden sm:inline w-1/6 truncate mx-auto text-center">Artista</p>
                        <input class="hidden lg:inline bg-transparent text-center w-1/6" readonly type="text" value="13/03/2023"/>
                    </div>
                </div>
            </template>
        </div>
    `
}