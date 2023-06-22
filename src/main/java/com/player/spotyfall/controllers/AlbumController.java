package com.player.spotyfall.controllers;

import com.player.spotyfall.modules.Utils;
import com.player.spotyfall.modules.database.DatabaseFault;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.spotyfall.modules.custom.AlbumMethods;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "albumController", value = "/albumController")
@MultipartConfig
public class AlbumController extends HttpServlet {

    AlbumMethods methods;
    ObjectMapper objectMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        methods = new AlbumMethods();

        try{
            String id_artist = req.getParameter("id_artist");
            String id_album = req.getParameter("id_album");
            String result;

            if(Utils.validateString(id_artist)) {
                result = methods.getAllAlbunsByArtistId(id_artist);
            }else if(Utils.validateString(id_album)){
                result = methods.getAlbumById(id_album);
            } else{
                throw new DatabaseFault("Você precisa passar um parâmetro valido");
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(result);
        } catch (DatabaseFault | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while Fetching Albums: " + e;
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper = new ObjectMapper();
        methods = new AlbumMethods();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            Map<String, Object> payloadMap = new HashMap<>();

            String fileName = req.getParameter("albumImage");
            if(fileName != null && !fileName.equals("{}")){
                fileName = req.getParameter("albumImage");
                payloadMap.put("albumImage", fileName);

            } else if (req.getPart("albumImage") != null) {
                // Salvando o arquivo
                String savePath = getServletContext().getRealPath("/WEB-INF/public/albumImages");
                fileName = Utils.saveFile(req, savePath, "albumImage");
                payloadMap.put("albumImage", fileName);
            }

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        methods = new AlbumMethods();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try{
            String id_album = req.getParameter("id_album");

            if(!Utils.validateString(id_album)){
                throw new DatabaseFault("Você precisa definir um id para deletar");
            }

            methods.deleteAlbumById(id_album);
            resp.setStatus(HttpServletResponse.SC_OK);

            String successMessage = "Album deleted successfully";
            String successJson = "{\"message\":\"" + successMessage + "\"}";
            resp.getWriter().write(successJson);
        }catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while saving Album: " + e;
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }
}