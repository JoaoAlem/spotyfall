package com.player.spotyfall.controllers;

import com.player.spotyfall.modules.custom.ArtistsMethods;
import com.player.spotyfall.modules.database.DatabaseFault;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "artistsController", value = "/artistsController")
@MultipartConfig
public class ArtistsController extends HttpServlet {
    ArtistsMethods artists;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        artists = new ArtistsMethods();

        try{
            String id_user = req.getParameter("id_user");
            String result = artists.getArtistByUserId(id_user);

            // Retornando o conteúdo
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(result);
        }catch (DatabaseFault | SQLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while Searching artist";
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        artists = new ArtistsMethods();
        try {
            Map<String, Object> payloadMap = new HashMap<>();
            String id_user = req.getParameter("id_user");
            payloadMap.put("id_user", id_user);

            artists.SaveArtist(payloadMap);

            resp.setStatus(HttpServletResponse.SC_OK);
            String successMessage = "Artist saved successfully";
            String successJson = "{\"message\":\"" + successMessage + "\"}";
            resp.getWriter().write(successJson);
        } catch (DatabaseFault | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while saving user";
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }
}