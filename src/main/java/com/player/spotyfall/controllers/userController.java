package com.player.spotyfall.controllers;

import java.io.*;
import java.util.*;
import com.player.spotyfall.models.Usuarios;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "userController", value = "/userController")
public class userController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Criando o objeto e definindo seus atributos
        Usuarios user = new Usuarios();
        user.setId(1);
        user.setName("João Antonio");
        user.setSurname("Alem");
        user.setUsername("joao.aalem");
        user.setEmail("joão.aalem@spotyfall.com");
        user.setPhone("19 997630939");
        user.setPassword("teste123");
        user.setUserImage("teste.png");

        List<Usuarios> listUsers = new ArrayList<Usuarios>();
        listUsers.add(user);

        // Criando um objectMapper do Jackson e transformando em um JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);

        // Retornando o conteúdo
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }
}