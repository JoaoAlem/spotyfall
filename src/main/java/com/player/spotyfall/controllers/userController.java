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
    UserMethods methods = new UserMethods();
    ResultSet rs;
    ObjectMapper objectMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            objectMapper = new ObjectMapper();
            Map<String, Object> payloadMap = objectMapper.readValue(req.getReader(), new TypeReference<>() {});

            String username = (String) payloadMap.get("username");
            String password = (String) payloadMap.get("password");

            /* Recolhendo os dados do banco de dados */
            rs = methods.GetUser(username, password);

            // se não existir dados, então retorna um eror
            if(rs == null || !rs.next()){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                String errorMessage = "User not found.";
                String errorJson = "{\"error\":\"" + errorMessage + "\"}";
                resp.getWriter().write(errorJson);
            }else{
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setUserImage(rs.getString("userImage"));

                // Criando um objectMapper do Jackson e transformando em um JSON
                objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(user);

                // Retornando o conteúdo
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonString);
            }

            rs.close();
        } catch (databaseFault | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}