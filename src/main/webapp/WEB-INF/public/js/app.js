// realizando o import do models e utils
import { userModel } from './models.js'
import request from './utils.js'

// Criação dos componentes com conteúdo
const home  = () => import('../views/home.js')
const search = () => import('../views/search.js')
const liked = () => import('../views/liked.js')
const artists = () => import('../views/artists.js')
const playlistsView = () => import('../views/playlists-view.js')
const privacy = () => import('../views/privacy.js')
const technologies = () => import('../views/technologies.js')
const user = () => import('../views/user.js')


// Criação das rotas do router
const routes =[
    { path: '/', redirect: "/home"},
    { path: '/home', name: "home", component: home},
    { path: "/search", name: "search", component: search},
    { path: '/liked', name: "liked", component: liked},
    { path: "/artists", name: "artists", component: artists},
    { path: "/playlists-view/:id", name: "view playlist", component: playlistsView},
    { path: "/technologies", name: "used technologies", component: technologies},
    { path: "/privacy", name: "privacy", component: privacy},
    { path: "/user", name: "user page", component: user},
]

// Criação do Router
const router = VueRouter.createRouter({
    mode: "history",
    history: VueRouter.createWebHistory("/spotyfall_war_exploded/"),
    routes: routes
})

// Criação do VueApp
const app = Vue.createApp({
    data(){
        return {
            iconsColor: "#f6f6f6",
            user: null,
            loading: true
        }
    },
    methods:{
        fetchUser: async () => {
            const response = await request("userController");
            const user = new userModel(response.data);
            return user;
        }
    },
    async mounted(){
        this.user = await this.fetchUser()
        this.loading = false
    }
})
.use(router)
.mount("#main")
