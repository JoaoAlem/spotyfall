<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="head.jsp" />
<main>
    <!-- Container página -->
    <div class="flex flex-col h-screen max-h-screen max-w-screen bg-padrao box-border justify-center items-center">
        <!-- Box- -->
        <div class="flex relative w-[380px] h-[760px] bg-box rounded-lg overflow-hidden ">

            <!-- Box before -->
            <div class="content-none absolute -top[50px] -left[50px] w-[380px] h-[760px] bg-gradient-to-b from-transparent to-purple-900 origin-bottom-right ">

                <!-- Box after -->
                <div class="content-none absolute -top[50px] -left[50px] w-[380px] h-[760px] bg-gradient-to-b from-transparent to-purple-900 origin-bottom-right">

                    <!-- Form box  -->
                    <form class="flex flex-col absolute inset-0.5 rounded-lg bg-form z-10 padding-[50px 40px] ">

                        <!-- Label - Titulo-->
                        <label class="block text-[#45f3ff] text-2xl	text-center tracking-widest p-4" for="Sign In">
                            Sign In
                        </label>

                        <!--InputBox-->
                        <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                            <!-- Input Usuário -->
                            <form>
                                <input type="text" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Nome de Usuário" style=" margin-top: -90%;">
                            </form>

                            <!--InputBox E-mail -->
                            <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                <!-- Input E-mail -->
                                <form>
                                    <input type="email" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="E-mail" style=" margin-top: -90%; margin-left: -6%">
                                </form>

                                <!--InputBox Telefone -->
                                <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                    <!-- Input Telefone -->
                                    <form>
                                        <input type="tel" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Telefone" style=" margin-top: -90%; margin-left: -11%;">
                                    </form>

                                    <!--InputBox Password -->
                                    <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                        <!-- Input Password -->
                                        <form>
                                            <input type="password" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Senha" style=" margin-top: -90%; margin-left: -16%;">
                                        </form>


                                        <!--InputBox Confirm Password -->
                                        <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                            <!--Input Confirm Password -->
                                            <form>
                                                <input type="password" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Confirmar senha" style=" margin-top: -90%; margin-left: -21%;">
                                            </form>

                                            <!--InputBox Nome -->
                                            <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                                <!--Input Confirm Password -->
                                                <form>
                                                    <input type="text" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Nome" style=" margin-top: -90%; margin-left: -26%;">
                                                </form>

                                                <!--InputBox Sobrenome -->
                                                <div class="relative w-[350px]  mt-[25px] items-center h-screen p-4">

                                                    <!--Input Confirm Password -->
                                                    <form>
                                                        <input type="text" required="required "class="relative w-full p-5 pt-2.5 pb-2.5 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter z-10  text-[#f6f6f6] " placeholder="Sobrenome" style=" margin-top: -90%; margin-left: -31%;">
                                                    </form>


                                                    <!--Link acesso rápido "Login" -->
                                                    <div name="Links" class="relative flex  justify-start my-0.5 m-0 mt-[10px] text-xs text-[#f6f6f6]">
                                                        <a class="ml-[-30%] mt-2.5"  href="#">Já possui cadastro? Faça login aqui</a>

                                                        <!--Botão Login-->
                                                        <div class="w-[100px] h-[40px]" >
                                                            <a class = "" href = "#"> </a>
                                                            <button  type="submit" class="relative border-2 border-[#45f3ff] rounded-xl bg-[#45f3ff] w-[100px] h-[40px] ml-[20%] mt-[60%] pointer-events-auto	text-black ">Cadastro

                                                            </button>
                                                        </div> <!--Botão cadastro -->
                                                    </div> <!--Link acesso rápido-->
                                                </div> <!--InputBox Nome -->
                                            </div> <!--InputBox Confirm Password-->
                                        </div> <!--InputBox Password -->
                                    </div> <!-- InputBox Telefone -->
                                </div> <!--InputBox Email -->
                            </div><!--InputBox Usuário--
         </form> <!--Form referente ao form box-->
                        </div> <!--Div referente à Box after-->
                </div> <!--Div referente à Box before-->
            </div> <!--Div referente à-->
        </div> <!--Div referente ao container pagina-->
</main>

<jsp:include page="foot.jsp" />