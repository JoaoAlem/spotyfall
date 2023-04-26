package com.player.spotyfall.servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PublicServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter o caminho do recurso solicitado
        String resourcePath = request.getPathInfo();

        // Se o caminho estiver vazio, redirecionar para a página inicial
        if (resourcePath == null || resourcePath.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Obter o arquivo do recurso solicitado
        String realPath = getServletContext().getRealPath("/WEB-INF/public" + resourcePath);
        File file = new File(realPath);

        // Se o arquivo não existir, retornar 404
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Configurar o tipo de conteúdo apropriado
        String extension = resourcePath.substring(resourcePath.lastIndexOf('.') + 1);
        String mimeType = getMimeType(extension);
        response.setContentType(mimeType);

        // Escrever o conteúdo do arquivo na resposta
        OutputStream out = response.getOutputStream();
        InputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    private String getMimeType(String extension) {
        switch (extension) {
            case "js":
                return "application/javascript";
            case "css":
                return "text/css";
            case "html":
            case "htm":
                return "text/html";
            default:
                return "application/octet-stream";
        }
    }
}
