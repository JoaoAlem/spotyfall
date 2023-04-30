export default {
    template: `
        <div class="flex justify-center items-center h-screen">
            <div class="w-11/12 md:w-8/12 xl:w-1/2 h-auto p-1 rounded-3xl bg-search flex flex-col"> 
                <!-- First section (search bar container) -->
                <section class="w-fit mx-auto h-10 flex items-center">
                    <!-- Input -->
                    <div class="flex bg-sidebar rounded-3xl p-2">
                        <label for="input-search"><i style="color:#f6f6f6" class="h-full w-6 fas fa-search"></i></label>
                        <input type="text" class="w-full h-full rounded-3xl text-white font-medium md:pl-2 focus:outline-none searchInput bg-sidebar" id="input-search" placeholder="Pesquisar">
                    </div>
                    
                    <!-- Search button -->
                    <button class="w-28 h-full bg-button flex justify-center items-center rounded-xl text-white font-medium">Search</button>
                </section>
            </div>
        </div>
            
        <div>
            <h4 class="text-2xl text-white">Busca recentes</h4>
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 max-h-full gap-y-5">
                <!-- Card -->
                <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                    <img src="./public/images/mbr.jpg"> Master Boot Record 
                </div>
            </div>
             
            <div class="my-4">
                <h4 class="text-2xl text-white my-2">Navegar por toda seções</h4>
                <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 max-h-full gap-y-5 ">
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Em alta 
                    </div>
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Sertanejo
                    </div>
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Pop
                    </div>
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Pop Rock
                    </div>
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Rock
                    </div>
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./public/images/placeholder.png"> Eletronica
                    </div>         
                </div>
            </div> 
        </div>
    `,
    data() {
        return {

        }
    }
}