export default {
    template: `
    <div style="margin: 0 !important;" id="modal-edit" class="fixed  h-screen w-screen top-2/4 left-2/4 -translate-y-1/2 -translate-x-1/2 hidden z-10">
        <!-- background -->
        <div class="opacity-25 bg-black h-screen w-screen absolute top-0 left-0 z-0"></div>
        
        <!-- form -->
        <form @submit.prevent style="background-color: var(--sobretomRoxo)" class="flex flex-col z-10 m-auto text-white">
            <!-- Form Title -->
            <div class="flex">
                <h2 class="mr-auto ml-2" v-if="false">Editando {{item.title}}</h2>
                <h2 class="mr-auto ml-2" v-else>Adicionar item</h2>
                <i class="fa fa-close w-6 ml-auto mr-2"></i>
            </div>
            
            <!-- Form body -->
            <div class="flex flex-col">
                <label for="form-title">Titulo da música</label>
                <input id="form-title" type="text">
            </div>
            
            <input type="file" name="file" id="file">
            <imput for="input-search"><i style="color:#f6f6f6" class="w-6 fas fa-search"></i></imput>
            <button class="w-6 fas fa-search" name="submit">Upload File</button> 
            
            
            <!-- Form footer -->
            <div class="flex flex-row">
                <button @click="this.$root.closeModal('modal-edit')" class="w-10 h-7 bg-gray-500"><i class="fa fa-close"></i></button>
                <button @click="save" class="w-10 h-7 bg-green-400"><i class="fa fa-check"></i></button>
            </div>
        </form>
    </div>
    `,
    methods: {
        save(){

        }
    }
}