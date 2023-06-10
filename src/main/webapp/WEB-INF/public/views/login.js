export default {
    template: `
         <!-- Container página -->
        <div class="flex flex-col h-screen max-h-screen max-w-screen bg-padrao box-border justify-center items-center">
            <div class="h-fit w-fit max-w-max max-h-max bg-gradient-to-b from-transparent to-purple-900 origin-bottom-right rounded-lg m-5">
                <div class="h-full w-full bg-gradient-to-b from-transparent to-purple-900 origin-bottom-right p-0.5 rounded-lg">
                    <form @submit.prevent="Login" style="height: inherit" class="flex flex-col rounded-lg bg-sidebar">
                        <h2 class="block text-[#45f3ff] text-2xl text-center tracking-widest p-4" for="Sign In">Sign Up</h2>
                        <!-- container form -->
                        <div class="items-center h-max p-4 space-y-5">
                            <!-- input senha e nome -->
                            <div class="flex flex-col space-y-4">
                                <input v-model="editing.login" type="text" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="E-mail, username or phone"  required>
                                <input v-model="editing.password" type="password" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff] rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Senha" required>
                            </div>
        
                            <!-- links de auxilio -->
                            <div class="flex justify-between text-xs text-[#f6f6f6]">
                                <a href="#">Esqueceu sua senha</a>
                                <router-link to="signin">Não possui uma conta?</router-link>
                            </div>
        
                            <!-- manter conectado e botão login -->
                            <div class="flex flex-col sm:flex-row space-y-4">
                                <div class="flex items-center sm:mr-auto">
                                    <label for="lembrar-senha" class="text-[#45f3ff] text-xs ">Manter Conectado</label>
                                    <input type="checkbox" id="lembrar-senha" name="lembrar-senha">
                                </div>
        
                                <button class="border-2 border-[#45f3ff] rounded-xl bg-[#45f3ff] w-[100px] h-[40px] sm:ml-auto">Login</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    `,
    data(){
        return{
            editing: {
                login: null,
                password: null
            }
        }
    },
    methods:{
        Login(){
            let data = new FormData()
            data.append("username", this.editing.login)
            data.append("password", this.editing.password)

            this.$root.request("userController", "post", data)
                .then((response) =>{
                    const [ user ] = response.data

                    if(user.id_user) {
                        this.$root.user = this.$root.UserModel(user)
                        this.$root.showSuccess(user)
                    }
                })
                .catch(() =>{
                    this.$root.showError("Nome ou senha incorretos")
                })
        }
    }
}