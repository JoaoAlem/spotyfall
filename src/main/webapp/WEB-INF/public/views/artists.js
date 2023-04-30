export default {
    template: `
    <div class="w-fit mx-auto sm:w-full h-full card-borders p-0.5 rounded-3xl">
        <div class="p-3 sm:p-0 bg-padrao flex flex-col sm:flex-row h-full items-center rounded-3xl">
            <div class="w-fit my-auto mr-4 sm:h-3/4 ml-7">
                <img class="border rounded-3xl h-full" src="./public/images/images.png" alt="">
            </div>
            <div class="mx-auto my-auto md:ml-3 md:mr-auto text-white flex flex-col">
                <h2 class="font-bold text-md xs:text-xl sm:text-2xl">Seus Artistas</h2>
                <span class="font-medium text-sm xs:text-md sm:text-lg">Fique de olho nos Artista que você ama!</span>
            </div>
        </div>
    </div>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 2xl:grid-cols-6 max-h-full gap-y-5">
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>      
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>  
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>  
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>  
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>  
        <!-- Card -->
        <div class="flex flex-col rounded-lg w-44 h-52 mx-auto card-borders p-0.5">
            <div class="flex flex-col bg-padrao space-y-2 h-full w-full">
                <div class="mx-auto mt-2 h-fit">
                    <img style="max-height: 120px; max-width: 120px;" class="mx-auto" src="./public/images/placeholder.png" alt="Imagem do artista">
                </div>
                <div style="height: 100px" class="w-full px-2">
                    <p class="w-full text-white truncate">Nome artista</p>
                    <p style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;" class="w-full text-white text-xs overflow-hidden">Quantidade Seguidores</p>
                </div>
            </div>
        </div>       
    </div>
    `
}