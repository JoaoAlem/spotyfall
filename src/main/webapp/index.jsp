<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="head.jsp" />

<!-- Container lado a lado -->
<div class="flex flex-row w-full h-5/6">

    <!-- navbar -->
    <nav class="navbar-nav flex flex-col w-40 h-full bg-sidebar overflow-y-auto basis-auto shrink-0">
        <!-- Links Home, Search e Liked -->
        <div class="mb-auto space-y-5">
            <router-link to="/home" class="flex m-auto items-center space-x-3 w-28"><i class="fa-house fa-solid h-6" :style="{color: iconsColor}"></i><p class="text-white text-xl">Home</p></router-link>
            <router-link to="/search" class="flex m-auto items-center space-x-3 w-28"><i class="fa-magnifying-glass fa-solid h-6" :style="{color: iconsColor}"></i><p class="text-white text-xl">Search</p></router-link>
            <router-link to="/liked" class="flex m-auto items-center space-x-3 w-28"><i class="fa-heart fa-solid h-6" :style="{color: iconsColor}"></i><p class="text-white text-xl">Liked</p></router-link>
            <router-link to="/artists" class="flex m-auto items-center space-x-3 w-28"><i class="fa-microphone fa-solid h-6" :style="{color: iconsColor}"></i><p class="text-white text-xl">Artists</p></router-link>
        </div>

        <!-- Playlists container -->
        <div class="my-auto w-40">
            <!-- Playlists -->
            <div class="flex m-auto items-center space-x-3 w-28">
                <i class="fa-solid fa-music h-6" :style="{color: iconsColor}"></i>
                <p class="text-white text-xl border-b ">Playlists</p>
            </div>
            <!-- Itens -->
            <div class="flex flex-col m-auto items-center max-h-24 overflow-y-auto overflow-x-visible space-x-3 max-w-32">
                <template v-for="n in 5">
                    <router-link :to="'/playlists-view/' + n" class="flex m-auto items-center space-x-3 w-28">
                        <i class="fa-solid fa-headphones h-4" :style="{color: iconsColor}"></i>
                        <p class="text-white text-md">Playlist {{n}}</p>
                    </router-link>
                </template>
                <button onclick="alert('Não implementado, ainda')" class="flex m-auto items-center space-x-3 w-28">
                    <i class="fa-solid fa-plus h-4" :style="{color: iconsColor}"></i>
                    <p class="text-white text-md">playlists</p>
                </button>
            </div>
        </div>

        <!-- Usuarios container -->
        <div class="mt-auto mb-28 md:mb-16 w-40">
            <!-- Login -->
            <router-link v-if="user" to="/user" class="flex m-auto items-center space-x-3 w-28" >
                <i class="fa-solid fa-user h-6" :style="{color: iconsColor}"></i>
                <p class="text-white text-xl">{{user.name}} {{user.surname}}</p>
            </router-link>
            <router-link v-else to="/login" class="flex m-auto items-center space-x-3 w-28" >
                <i class="fa-solid fa-user h-6" :style="{color: iconsColor}"></i>
                <p class="text-white text-xl">Login</p>
            </router-link>
        </div>
    </nav>
    <!-- Fim navbar -->

    <!-- container conteúdo dinâmico e navegação -->
    <div class="w-full h-full flex flex-col">
        <div class="w-40 h-7 p-1 space-x-2 m-2">
            <button class="mx-auto" @click="this.$router.go(-1)"><i class="h-6 fa-solid fa-chevron-circle-left" :style="{color: iconsColor}"></i></button>
            <button class="mx-auto" @click="this.$router.go(1)"><i class="h-6  fa-solid fa-chevron-circle-right" :style="{color: iconsColor}"></i></button>
        </div>
        <!-- Coloque o conteúdo dinâmico dentro de main -->
        <main id='appConteudo' class="max-h-fit h-full flex flex-col space-y-10 p-7 basis-full shrink overflow-y-auto">
            <router-view></router-view>
        </main>
        <!-- Fim do conteúdo dinâmico-->
    </div>
</div>
<!-- Fim container lado a lado -->

<!-- Container geral do player -->
<div class="relative w-full h-fit max-h-40 md:h-1/6 bg-sidebar">
    <!-- Máscara Imagem -->
    <div class="absolute bottom-0 h-full w-full max-h-40 max-w-40 flex bg-black opacity-50 z-10"></div>

    <!-- Imagem -->
    <div class="absolute bg-white bottom-0 h-40 w-40 max-h-40 max-w-40 flex">
        <!-- <img class="mx-auto mb-0 mt-auto max-w-full" src="" alt=""> -->
    </div>

    <!-- Container player -->
    <div class="flex w-full h-full items-center md:flex-row">
        <!-- Informações sobre a música -->
        <div class="z-20 flex flex-col ml-3 mr-3 w-fit md:max-w-48 md:w-48 space-y-1">
            <p class="text-white text-md sm:text-xl truncate">Nome da música</p>
            <p class="text-white text-sm sm:text-md">Nome do Autor</p>
            <p class="text-white text-xs sm:text-xs">Tocando de:&lt;Nome da playlist &gt;</p>
        </div>

        <!-- Coração gostei -->
        <button class="flex ml-2 mr-auto items-center w-fit">
            <i class="fa-solid fa-heart h-4" :style="{color: iconsColor}"></i>
        </button>

        <!-- Controles Música -->
        <div class="flex flex-col m-auto items-center space-y-3 sm:mr-auto w-fit">
            <!-- Parte de cima -->
            <div class="flex flex-row space-x-3 sm:space-x-5 md:space-x-10">
                <button class="bg-transparent border-0 h-fit">
                    <i class="fa-solid fa-random h-5" :style="{color: iconsColor}"></i>
                </button>
                <button class="bg-transparent border-0 h-fit">
                    <i class="fa-solid fa-backward-step h-5" :style='{color: iconsColor}'></i>
                </button>
                <button class="bg-transparent border-0 h-fit">
                    <i class="fa-solid fa-play h-5" :style='{color: iconsColor}'></i>
                </button>
                <button class="bg-transparent border-0 h-fit">
                    <i class="fa-solid fa-forward-step h-5" :style='{color: iconsColor}'></i>
                </button>
                <button class="bg-transparent border-0 h-fit">
                    <i class="fa-solid fa-repeat h-5" :style='{color: iconsColor}'></i>
                </button>
            </div>

            <!-- Parte de baixo -->
            <div class="flex flex-row items-center space-x-2">
                <p class="text-xs text-white">1:20</p>
                <div class="w-32 sm:w-60 md:w-96 h-2 bg-white rounded"></div>
                <p class="text-xs text-white">3:40</p>
            </div>
        </div>

        <!-- Controles Audio -->
        <div class=" hidden sm:flex flex-col sm:flex-row ml-auto mr-5 items-center w-fit space-y-2 sm:space-y-0 sm:space-x-2">
            <i class="fa-solid fa-volume-high h-4" :style='{color: iconsColor}'></i>
            <div class="max-w-40 w-12 sm:w-16 md:w-24 h-2 bg-white rounded"></div>
        </div>
    </div>
</div>
<!-- Fim container geral player-->

<jsp:include page="foot.jsp" />