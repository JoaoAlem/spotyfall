package com.player.spotyfall.controllers;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.player.spotyfall.modules.custom.UserMethods;
import com.player.spotyfall.modules.database.DatabaseFault;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "userController", value = "/userController")
@MultipartConfig
public class UserController extends HttpServlet {

    // Objetos de acesso
    UserMethods methods;
    ObjectMapper objectMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        methods = new UserMethods();
        try {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            /* Recolhendo os dados do banco de dados */
            String result = methods.GetUser(username, password);

            // Retornando o conteúdo
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(result);
            }
        catch (DatabaseFault | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorMessage = "Error occurred while searching user";
            String errorJson = "{\"error\":\"" + errorMessage + "\"}";
            resp.getWriter().write(errorJson);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        objectMapper = new ObjectMapper();
        methods = new UserMethods();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try{
            Map<String, Object> payloadMap = new HashMap<>();

            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);

                payloadMap.put(paramName, paramValue);
            }

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