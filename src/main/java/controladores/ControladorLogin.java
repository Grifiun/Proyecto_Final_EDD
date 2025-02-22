/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este servlet recibe el codigo de usuario y la password
 * ingresada por el usuario, y se verifica si los datos son 
 * correctos.
 * @author grifiun
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

    /**
     * Metodo dePost, usada para recibir los datos
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo, password, usuarioRol = "";
        codigo = request.getParameter("codigo");
        password = request.getParameter("pass");
        
        if(codigo.equals("admin") && password.equals("admin")){
            //ADMINISTRADOR
            usuarioRol = "admin";
        }else{//revisamos usuarios
            if(codigo.equals("colaborador") && password.equals("colaborador")){
                //COLABORADOR
                usuarioRol = "colaborador";
            }
        }
        
        request.getSession().setAttribute("rol", usuarioRol);
        System.out.println(usuarioRol);
        request.getSession().setAttribute("codigo", codigo);
        request.getSession().setAttribute("mensaje", "Bienvenido");
        
        String direccion = "";
        switch(usuarioRol){
            case "":                 //no encuentra al usuario           
                direccion = "jsp/usuario-no-encontrado.jsp";
                response.sendRedirect(direccion);
                break;
            default:
                //direccion = "jsp/home-"+usuarioRol+".jsp";
                direccion = "jsp/home.jsp";
                response.sendRedirect(direccion);
                break;
        }
    }

    /**
     * El servlet "ControladorLogin" tiene como funcion 
     * manejar el ingreso de los usuarios dentro del sistema.
     * 
     * Se encarga de verificar la existencia de los usuario dentro
     * de la base de datos así como revisar la contrasena     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
