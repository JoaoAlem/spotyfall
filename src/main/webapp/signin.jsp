<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="head.jsp" />

<div class="flex flex-col justify-center items-center h-screen max-h-screen max-w-screen bg-padrao box-border">
    <div class="rounded-lg w-fit h-fit card-borders p-1 m-3">
        <form method="get" class="flex flex-col bg-padrao w-full h-full">
            <div class="flex items-center justify-center m-auto space-x-2">
                <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" id="screenshot-b29842a9-a4ba-8069-8002-3ba70d30e27c" viewBox="747 129 111 99" style="-webkit-print-color-adjust: exact;" fill="none" version="1.1">
                    <g id="shape-b29842a9-a4ba-8069-8002-3ba70d30e27c">
                        <defs>
                            <linearGradient id="fill-color-gradient_rumext-id-1_0" x1="0.5" y1="0" x2="0.5" y2="1" gradientTransform="matrix(1.000000, 0.000000, 0.000000, 1.000000, 0.000000, 0.000000)">
                                <stop offset="0" stop-color="#2e0dff" stop-opacity="1"/><stop offset="1" stop-color="#ae00ff" stop-opacity="1"/>
                            </linearGradient>
                            <pattern patternUnits="userSpaceOnUse" x="747" y="129" height="99" width="111" data-loading="false" patternTransform="matrix(1.000000, 0.000000, 0.000000, 1.000000, 0.000000, 0.000000)" id="fill-0-rumext-id-1">
                                <g>
                                    <rect width="111" height="99" style="fill: url(&quot;#fill-color-gradient_rumext-id-1_0&quot;);"/>
                                </g>
                            </pattern>
                        </defs>
                        <g class="fills" id="fills-b29842a9-a4ba-8069-8002-3ba70d30e27c">
                            <path rx="0" ry="0" d="M811.750,153.750L811.750,145.668L844.125,140.660L844.125,152.551L811.750,157.559L811.750,153.750ZZM797.875,169.219L797.875,199.828C792.643,198.087,786.255,197.062,779.375,197.062C761.482,197.062,747.000,203.985,747.000,212.531C747.000,221.078,761.482,228.000,779.375,228.000C797.268,228.000,811.750,221.078,811.750,212.531L811.750,167.072L858.000,159.937L858.000,138.533L858.000,129.000L844.125,131.146L797.875,138.281L797.875,153.750L797.875,159.686L797.875,169.219ZZM794.204,209.147C797.470,210.714,797.875,212.029,797.875,212.531C797.875,213.034,797.470,214.349,794.204,215.915C790.966,217.462,785.763,218.719,779.375,218.719C772.987,218.719,767.784,217.462,764.546,215.915C761.280,214.349,760.875,213.034,760.875,212.531C760.875,212.029,761.280,210.714,764.546,209.147C767.784,207.601,772.987,206.344,779.375,206.344C785.763,206.344,790.966,207.601,794.204,209.147ZZ" fill="url(#fill-0-rumext-id-1)"/>
                        </g>
                    </g>
                </svg>
                <h1 class="text-2xl text-white">Spotyfall</h1>
            </div>

            <div class="flex items-center m-auto">
                <h2 class="text-lg text-white">Sign-in</h2>
            </div>
            <div class="m-auto">
                <div class="flex items-center m-auto p-3 space-x-3">
                    <label  for="inputNome" class="text-sm text-white">Name:</label>
                    <input id="inputNome" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="Name" type="text"/>
                </div>
                <div class="flex items-center m-auto p-3 space-x-3">
                    <label  for="inputSurname" class="text-sm text-white">Surname:</label>
                    <input id="inputSurname" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="Surname" type="text"/>
                </div>
                <div class="flex items-center m-auto p-3 space-x-3">
                    <label  for="inputUsername" class="text-sm text-white">Username:</label>
                    <input id="inputUsername" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="Username" type="text"/>
                </div>
                <div class="flex items-center m-auto p-3 space-x-3">
                    <label  for="inputEmail" class="text-sm text-white">E-mail:</label>
                    <input id="inputEmail" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="E-mail" type="text"/>
                </div>
                <div class="flex items-center m-auto p-3 space-x-3">
                    <label  for="inputPhone" class="text-sm text-white">Phone:</label>
                    <input id="inputPhone" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="Phone Number" type="text"/>
                </div>
                <div class="flex items-center m-auto p-3 space-x-3 border-b-white border-b-2">
                    <label for="inputPassword" class="text-sm text-white">Password:</label>
                    <input id="inputPassword" class="w-3/4 h-full rounded-2xl bg-transparent" placeholder="Password" type="text"/>
                </div>
                <a href="${pageContext.request.contextPath}/Login.jsp"><span class="ml-3 text-white text-xs">Already have account?</span></a>
                <div class="flex items-center m-auto p-3 space-x-3">
                    <button type="submit" class="bg-transparent border mr-0 ml-auto my-auto text-md p-2 rounded-xl text-white">Sign-in</button>
                </div>
            </div>

        </form>
    </div>
</div>

<jsp:include page="foot.jsp" />