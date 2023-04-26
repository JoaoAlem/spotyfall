// realizando o import do models e utils
import { userModel } from './models.js'
import request from './utils.js'

// Criação dos componentes com conteúdo
const Home  = () => import('../views/home.js')
const Search = () => import('../views/search.js')

const Liked = {template: '<div><p class="text-white">componente liked</p></div>'}
const Artists = {template: '<div><p class="text-white">componente artists</p></div>'}


// Criação das rotas do router
const routes =[
    { path: '/', redirect: "/home"},
    { path: '/home', name: "home", component: Home},
    { path: "/search", name: "search", component: Search},
    { path: '/liked', name: "liked", component: Liked},
    { path: "/artists", name: "artists", component: Artists}
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
            usuario: null
        }
    },
    methods:{
        fetchUser: function(){
            return request("userController", (data)=>{
                return new userModel(data.data)
            })
        }
    },
    async mounted(){
        this.usuario = console.log(await this.fetchUser())
    }
})
.use(router)
.mount("#main")
