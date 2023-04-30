export default {
    template: `
        <div class="flex flex-col lg:flex-row w-full h-full max-h-full items-center justify-center text-[#f6f6f6] text-justify lg:space-x-7 space-y-10"> 
            <div class="flex flex-col w-full h-max justify-center items-center space-y-10">
                <!-- Card 1 -->
                <div class="flex flex-col w-5/6 h-full max-h-96 card-borders rounded-lg p-0.5">
                    <div class="flex flex-col w-full h-full max-h-96 bg-padrao rounded-lg m-auto items-center p-3">
                        <h3 class="text-lg md:text-2xl m-5"><strong>Um pouco de nossa história</strong></h3>
                        <div class="w-4/5 w-full mx-auto text-ellipsis overflow-hidden indent-12">
                            <p class="text-md sm:text-lg">O Spotyfall é um site desenvolvimento por 5 estudantes com o fim de estudar e entregar um trabalho pra faculdade.</p>
                            <p class="text-md sm:text-lg ">Decidimos nomear de Spotyfall, pois esse site possui inspirações em players de música já existente, dessa forma, ele se tornou um "Frankstein", logo usamos a junção do nome de 2 players populares. Spotify e tidal.</p>
                        </div>
                    </div>
                </div>
                
                 <!-- Card 2 -->
                <div class="flex flex-col w-5/6 h-full max-h-96 card-borders rounded-lg p-0.5">
                    <div class="flex flex-col w-full h-full max-h-96 bg-padrao rounded-lg m-auto items-center p-3">
                        <h3 class="text-lg md:text-2xl m-5"><strong>Politica de privacidade do Spotyfall</strong></h3>
                        <div class="w-4/5 w-full mx-auto text-ellipsis overflow-hidden indent-12">
                            <p class="text-md sm:text-lg">É política do Spotyfall respeitar a sua privacidade em relação a qualquer informação sua que possamos coletar no site Spotyfall, e outros sites que possuímos e operamos. Soliciamos informações pessoais apenas quando realmente precisamos delas para lhe fornecer um serviço.</p>
                            <p class="text-md sm:text-lg">Este site foi feito por meios justos e legais, com o seu conhecimento e consentimento.</p>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Card 3 -->
            <div class="flex flex-col w-5/6 h-full max-h-96 card-borders rounded-lg p-0.5">
                <div class="flex flex-col w-full h-full max-h-96 bg-padrao rounded-lg m-auto items-center">
                    <h3 class="text-lg md:text-2xl my-5"><strong>Informações</strong></h3>
                    <div class="flex flex-col w-4/5 h-fit mx-auto p-2 space-y-10">
                        <button class="w-full border rounded-xl underline underline-offset-4 text-md sm:text-lg text-left p-1">1. Quem Somos?</button>
                        <button class="w-full border rounded-xl underline underline-offset-4 text-md sm:text-lg text-left p-1">2. Como usamos seus dados pessoais?</button>
                        <button class="w-full border rounded-xl underline underline-offset-4 text-md sm:text-lg text-left p-1">3. Informações que você escolhe tornar pública?</button>
                    </div>
                </div>
            </div>
        </div>
    `,
}