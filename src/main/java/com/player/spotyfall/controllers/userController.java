package com.player.spotyfall.controllers;

import java.io.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.player.spotyfall.models.UserModel;
import com.player.spotyfall.modules.custom.UserMethods;
import com.player.spotyfall.modules.database.databaseFault;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "userController", value = "/userController")
public class userController extends HttpServlet {

    // Objetos de acesso
    UserModel user = new UserModel();
    UserMethods methods;
    ResultSet rs;
    ObjectMapper objectMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper = new ObjectMapper();
        methods = new UserMethods();
        try {
            Map<String, Object> payloadMap = objectMapper.readValue(req.getReader(), new TypeReference<>() {});

            String username = (String) payloadMap.get("username");
            String password = (String) payloadMap.get("password");

            /* Recolhendo os dados do banco de dados */
            String result = methods.GetUser(username, password);

            // Retornando o conteúdo
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(result);
            }
        catch (databaseFault | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper = new ObjectMapper();
        methods = new UserMethods();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try{
            Map<String, Object> payloadMap = objectMapper.readValue(req.getReader(), new TypeReference<>() {});
            methods.SaveUser(payloadMap);

            resp.setStatus(HttpServletResponse.SC_OK);

            String successMessage = "User saved successfully";
            String successJson = "{\"message\":\"" + successMessage + "\"}";
            resp.getWriter().write(successJson);
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while saving user";
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }
}