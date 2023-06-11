export default {
    template: `
    <div class="flex flex-col relative text-white">
         <div id="menu" @click="toggleMenu" class="flex flex-col items-center justify-center w-20 h-10 text-white cursor-pointer bg-sidebar" :class="{'rounded-lg': !isMenuActive, 'rounded-t-lg': isMenuActive}">
          <div class="flex items-center justify-around w-full">
            <i :class="isMenuActive ? 'fa-solid fa-close' : 'fa-solid fa-bars'" :style="{ color: $root.iconsColor }"></i>
            <p>Menu</p>
          </div>
          
        </div>
        <div class="bg-sidebar absolute right-0 top-9 pt-3" :class="{ show: isMenuActive, hidden: !isMenuActive }">
            <template v-if="$root.user">
                <ul class="w-full h-full" :class="{ show: isMenuActive, hidden: !isMenuActive }">
                    <li class=""><router-link to="/musics-management">Gerenciar Músicas</router-link></li>        
                    <li class=""><router-link to="/musics-management">Gerenciar Playlists</router-link></li>                  
                    <li class=""><router-link to="/musics-management">Gerenciar Albuns</router-link></li>                  
          
                    <button @click="logout" class="border-none">Logout</button>
                </ul>
              </template>
              <template v-else>
                <ul class="w-full h-full">
                  <li>
                    <router-link to="/login">Login</router-link>
                  </li>
                  <li>
                    <router-link to="/signin">Cadastro</router-link>
                  </li>
                </ul>
              </template>
        </div>
    </div>
  `,
    data() {
        return {
            isMenuActive: false
        };
    },
    methods: {
        toggleMenu() {
            this.isMenuActive = !this.isMenuActive
        },
        logout(){
          this.$root.user = null
        }
    }
}