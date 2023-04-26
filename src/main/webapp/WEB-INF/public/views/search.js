export default {
    template: `
        <div class="flex justify-center items-center h-screen">
            <div class="w-11/12 md:w-8/12 xl:w-1/2 h-auto p-1 rounded-3xl bg-search flex flex-col"> 
                <!-- First section (search bar container) -->
                <section class="w-full h-10 flex items-center">
                    <!-- Search icon container -->
                    <span class="w-10 h-full hidden md:flex items-center">
                        <i class="uil uil-search-alt text-xl text-blue-800"></i>
                    </span>
                    
                    <!-- Input -->
                    <input type="text" class="w-full h-full rounded-3xl  bg-search text-white font-medium md:pl-2 focus:outline-none searchInput"  placeholder="Pesquisar">
                
                    <!-- Search button -->
                    <button class="w-28 h-full bg-button flex justify-center items-center rounded-xl text-white font-medium">Search</button>
                </section>
            
                <!-- Second section (results container) -->
                <section class="w-full h-auto hidden flex-col gap-y-2 mt-8 resultsContainer"></section>  
            </div>
        </div>
            
        <div>
            <h4 class="text-2xl text-white">Busca recentes</h4> <br>
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 max-h-full gap-y-5">
                <!-- Card -->
                <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                    <img src="./imagens/mbr.jpeg"> Master Boot Record 
                </div>
            </div>
             
            <div>
                <h4 class="text-2xl text-white">Navegar por toda seções</h4> <br>
                <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 max-h-full gap-y-5 ">
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Em alta 
                    </div>
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Rock
                    </div>    
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Samba
                    </div>  
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Pop Rock
                    </div>  
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Eletrônica
                    </div>  
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Nacionais
                    </div>  
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Sertanejo 
                    </div>  
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Pop 
                    </div>  
            
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Internacional
                    </div>  
                    
                    <!-- Card -->
                    <div class="flex flex-col rounded-lg border w-44 h-52 mx-auto" style="color: rgb(246, 246, 246); padding: 20px;border-color: #7700a6">
                        <img src="./imagens/"> Clássicos
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