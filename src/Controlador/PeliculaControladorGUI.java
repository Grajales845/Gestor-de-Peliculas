/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pelicula;
import Modelo.PeliculaDAO;
import Vista.PeliculaVistaGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class PeliculaControladorGUI {
    
    private PeliculaDAO peliculaDAO;
    private PeliculaVistaGUI vista;

    public PeliculaControladorGUI(PeliculaDAO peliculaDAO, PeliculaVistaGUI vista) {
        this.peliculaDAO = peliculaDAO;
        this.vista = vista;

        this.vista.agregarListenerAgregar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    agregarPelicula();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.vista.agregarListenerEditar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarPelicula();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.vista.agregarListenerEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarPelicula();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.vista.agregarListenerBuscar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarPeliculas();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void agregarPelicula() throws SQLException {
        String titulo = vista.getTitulo();
        String director = vista.getDirector();
        int anio = vista.getAnio();
        String genero = vista.getGenero();
        Pelicula pelicula = new Pelicula(titulo, director, anio, genero);
        peliculaDAO.agregarPelicula(pelicula);
        vista.setResultadoArea("Película agregada con éxito.\n");
    }

    public void actualizarPelicula() throws SQLException {
        String titulo = vista.getTitulo();
        String director = vista.getDirector();
        int anio = vista.getAnio();
        String genero = vista.getGenero();
        Pelicula pelicula = new Pelicula(titulo, director, anio, genero);
        peliculaDAO.actualizarPelicula(pelicula);
        vista.setResultadoArea("Película actualizada con éxito.\n");
    }

    public void eliminarPelicula() throws SQLException {
        String titulo = vista.getBuscarTitulo();
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculas(titulo);
        if (!peliculas.isEmpty()) {
            peliculaDAO.eliminarPelicula(peliculas.get(0).getId());
            vista.setResultadoArea("Película eliminada con éxito.\n");
        } else {
            vista.setResultadoArea("Película no encontrada.\n");
        }
    }

    public void buscarPeliculas() throws SQLException {
        String titulo = vista.getBuscarTitulo();
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculas(titulo);
        StringBuilder resultado = new StringBuilder();
        for (Pelicula pelicula : peliculas) {
            resultado.append("ID: ").append(pelicula.getId()).append("\n");
            resultado.append("Título: ").append(pelicula.getTitulo()).append("\n");
            resultado.append("Director: ").append(pelicula.getDirector()).append("\n");
            resultado.append("Año: ").append(pelicula.getAnio()).append("\n");
            resultado.append("Género: ").append(pelicula.getGenero()).append("\n\n");
        }
        vista.setResultadoArea(resultado.toString());
    }
}
