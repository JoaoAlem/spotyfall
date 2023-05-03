<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="head.jsp" />
<div class="flex flex-col h-screen max-h-screen max-w-screen bg-padrao box-border justify-center items-center">
    <div class="h-fit w-fit max-w-max max-h-max bg-gradient-to-b from-purple-600 to-purple-900 rounded-lg m-5">
        <div class="h-full w-full bg-gradient-to-b from-purple-600 to-purple-900  p-0.5 rounded-lg">
            <form style="height: inherit" class="flex flex-col rounded-lg bg-sidebar">
                <h2 class="block text-[#45f3ff] text-2xl text-center tracking-widest p-4" for="Sign In">Sign Up</h2>
                <!-- container form -->
                <div class="flex flex-col items-center h-max p-4 space-y-5">

                    <!-- input senha e nome -->
                    <div class="flex flex-col space-y-4">
                        <input type="text" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Nome"  required>
                        <input type="text" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Sobrenome"  required>
                        <input type="text" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Username"  required>
                        <input type="email" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff] rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="E-mail" required>
                        <input type="tel" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff]  rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Telefone"  required>
                        <input type="password" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff] rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Senha" required>
                        <input type="password" class="w-40 sm:w-full h-12 bg-transparent border-2 border-[#45f3ff] rounded-md text-base tracking-tighter text-[#f6f6f6]" placeholder="Confirmar Senha" required>
                    </div>

                    <!-- links de auxilio -->
                    <div class="flex justify-between text-xs text-[#f6f6f6]">
                        <a href="./signin.jsp">Já possui cadastro ? Faça login aqui</a>
                    </div>

                    <button type="submit" class="border-2 border-[#45f3ff] rounded-xl bg-[#45f3ff] w-[100px] h-[40px] sm:ml-auto">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="foot.jsp" />