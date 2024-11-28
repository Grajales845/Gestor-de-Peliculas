/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Vista.PeliculaVistaGUI;
import Controlador.PeliculaControladorGUI;
import Modelo.PeliculaDAO;

import javax.swing.*;
import java.sql.SQLException;

public class MainGUI {
    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    PeliculaDAO peliculaDAO = new PeliculaDAO();  
                    PeliculaVistaGUI vista = new PeliculaVistaGUI();
                    PeliculaControladorGUI controlador = new PeliculaControladorGUI(peliculaDAO, vista);

                    // Hacer visible la ventana de la aplicación
                    vista.setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}