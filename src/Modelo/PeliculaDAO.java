/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    private Connection conn;

    // Constructor
    public PeliculaDAO() throws SQLException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
            return;
        }
        
        // Establecer la conexión
        String url = "jdbc:mysql://localhost:3306/cine_db?useSSL=false&serverTimezone=UTC"; 
        String user = "root"; 
        String pass = "admin";      
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; 
        }
    }

    // Método para agregar una película
    public void agregarPelicula(Pelicula pelicula) throws SQLException {
        String query = "INSERT INTO pelicula (titulo, director, anio, genero) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAnio());
            stmt.setString(4, pelicula.getGenero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar la película: " + e.getMessage());
            throw e; 
        }
    }

    // Método para actualizar una película
    public void actualizarPelicula(Pelicula pelicula) throws SQLException {
        String query = "UPDATE pelicula SET titulo = ?, director = ?, anio = ?, genero = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAnio());
            stmt.setString(4, pelicula.getGenero());
            stmt.setInt(5, pelicula.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la película: " + e.getMessage());
            throw e; 
        }
    }

    // Método para eliminar una película
    public void eliminarPelicula(int id) throws SQLException {
        String query = "DELETE FROM pelicula WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la película: " + e.getMessage());
            throw e; 
        }
    }

    // Método para buscar películas por título
    public List<Pelicula> buscarPeliculas(String titulo) throws SQLException {
        String query = "SELECT * FROM pelicula WHERE titulo LIKE ?";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(rs.getString("titulo"), rs.getString("director"), rs.getInt("anio"), rs.getString("genero"));
                pelicula.setId(rs.getInt("id"));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar las películas: " + e.getMessage());
            throw e; 
        }
        return peliculas;
    }

    // Método para listar todas las películas
    public List<Pelicula> listarPeliculas() throws SQLException {
        String query = "SELECT * FROM pelicula";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(rs.getString("titulo"), rs.getString("director"), rs.getInt("anio"), rs.getString("genero"));
                pelicula.setId(rs.getInt("id"));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las películas: " + e.getMessage());
            throw e; 
        }
        return peliculas;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

