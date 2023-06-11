// realizando o import do models e utils
import { userModel, albumModel } from './models.js'
import request from './utils.js'

// Criação dos componentes com conteúdo
const home = () => import('../views/home.js')
const search = () => import('../views/search.js')
const liked = () => import('../views/liked.js')
const artists = () => import('../views/artists.js')
const playlistsView = () => import('../views/playlists-view.js')
const privacy = () => import('../views/privacy.js')
const technologies = () => import('../views/technologies.js')
const user = () => import('../views/user.js')
const login = () => import('../views/login.js')
const signin = () => import('../views/signin.js')

/* criação dos componentes */
import success from "../components/success.js";
import error from "../components/error.js";

// Criação das rotas do router
const routes = [
    { path: '/', redirect: "/home" },
    { path: '/home', name: "home", component: home },
    { path: "/search", name: "search", component: search },
    { path: '/liked', name: "liked", component: liked },
    { path: "/artists", name: "artists", component: artists },
    { path: "/playlists-view/:id", name: "view playlist", component: playlistsView },
    { path: "/technologies", name: "used technologies", component: technologies },
    { path: "/privacy", name: "privacy", component: privacy },
    { path: "/user", name: "user page", component: user, meta: { requiresUser: true } },
    { path: "/login", name: "login", component: login },
    { path: "/signin", name: "create account", component: signin },
]

// Criação do Router
const router = VueRouter.createRouter({
    mode: "history",
    history: VueRouter.createWebHistory("/spotyfall_war_exploded/"),
    routes: routes
})

// Criação do VueApp
const app = Vue.createApp({
    data() {
        return {
            iconsColor: "#f6f6f6",
            user: null,
            loading: true,
            message: null,
            successComponent: null,
            errorComponent: null
        }
    },
    methods: {
        /* Utils */
        request(route, method, data) {
            return request(route, method, data)
        },
        setMessage(message) {
            this.message = message
        },
        hideModal(component, time) {
            setTimeout(() => {
                component.classList.remove("show")
                component.classList.remove("shake")
            }, time)
        },
        showSuccess(message) {
            this.setMessage(message)
            this.successComponent.classList.add("show")

            this.hideModal(this.successComponent, 5000)
        },
        showError(message) {
            this.setMessage(message)
            this.errorComponent.classList.add("show")
            this.errorComponent.classList.add("shake")

            this.hideModal(this.errorComponent, 5000)
        },

        /* Models */
        UserModel(data) {
            return new userModel(data)
        },
        AlbunsModel(data) {
            return new albumModel(data)
        }
    },
    mounted() {
        this.loading = false
        this.successComponent = document.getElementById("balaoSucesso")
        this.errorComponent = document.getElementById("balaoErro")
    }
})

// Navigation guard to check if user exists before accessing the "user" page
router.beforeEach((to, from, next) => {
    if (to.meta.requiresUser) {
        const user = app.user;
        if (!user || !user.id_user) {
            next('/login');
        } else {
            next();
        }
    } else {
        next();
    }
})

app.use(router)
app
    .component("success", success)
    .component("error", error)

app.mount("#main")