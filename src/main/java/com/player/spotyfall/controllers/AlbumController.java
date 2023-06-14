package com.player.spotyfall.controllers;

import com.player.spotyfall.modules.Utils;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.spotyfall.modules.custom.AlbumMethods;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "albumController", value = "/albumController")
@MultipartConfig
public class AlbumController extends HttpServlet {

    AlbumMethods methods;
    ObjectMapper objectMapper;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper = new ObjectMapper();
        methods = new AlbumMethods();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            Map<String, Object> payloadMap = new HashMap<>();

            // Salvando o arquivo
            String savePath = getServletContext().getRealPath("/WEB-INF/public/albumImages");
            String fileName = Utils.saveFile(req, savePath, "albumImage");
            payloadMap.put("albumImage", fileName);

            // Pegar o titulo de todos os campos e os valores
            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);
                payloadMap.put(paramName, paramValue);
            }

            methods.SaveAlbum(payloadMap);

            resp.setStatus(HttpServletResponse.SC_OK);

            String successMessage = "Album saved successfully";
            String successJson = "{\"message\":\"" + successMessage + "\"}";
            resp.getWriter().write(successJson);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while saving Album: " + e;
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }
}