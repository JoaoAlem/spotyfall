package com.player.spotyfall.controllers;

import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            objectMapper = new ObjectMapper();
            user = objectMapper.readValue(req.getReader(), UserModel.class);

            rs = methods.GetUser(user.getUsername(), user.getPassword());

            while(rs.next()){
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setUserImage(rs.getString("userImage"));
            }

            // Criando um objectMapper do Jackson e transformando em um JSON
            objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(user);

            // Retornando o conteúdo
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonString);
        } catch (databaseFault e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}