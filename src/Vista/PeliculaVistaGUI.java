/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class PeliculaVistaGUI extends JFrame {
    private JTextField tituloField;
    private JTextField directorField;
    private JTextField anioField;
    private JTextField generoField;
    private JTextField buscarField;
    
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JButton buscarButton;

    private JTextArea resultadoArea;

    private Image fondo; // Variable para almacenar la imagen de fondo

    public PeliculaVistaGUI() {
        setTitle("Gestión de Películas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar la imagen de fondo
        fondo = new ImageIcon("C:\\Users\\User\\Pictures\\Saved Pictures\\imagen.jpg").getImage(); 

        // Panel personalizado con imagen de fondo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); 
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel); 

        // Panel de Entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false); // Para que el fondo sea visible
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        inputPanel.add(tituloField);

        inputPanel.add(new JLabel("Director:"));
        directorField = new JTextField();
        inputPanel.add(directorField);

        inputPanel.add(new JLabel("Año:"));
        anioField = new JTextField();
        inputPanel.add(anioField);

        inputPanel.add(new JLabel("Género:"));
        generoField = new JTextField();
        inputPanel.add(generoField);

        inputPanel.add(new JLabel("Buscar por título:"));
        buscarField = new JTextField();
        inputPanel.add(buscarField);

        backgroundPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel de Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");
        buscarButton = new JButton("Buscar");
        
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(buscarButton);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Área de Resultado
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        backgroundPanel.add(new JScrollPane(resultadoArea), BorderLayout.SOUTH);
    }

    // Getters para obtener los datos de los campos
    public String getTitulo() {
        return tituloField.getText();
    }

    public String getDirector() {
        return directorField.getText();
    }

    public int getAnio() {
        try {
            return Integer.parseInt(anioField.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }

    public String getGenero() {
        return generoField.getText();
    }

    public String getBuscarTitulo() {
        return buscarField.getText();
    }

    // Método para mostrar resultados en el JTextArea
    public void setResultadoArea(String text) {
        resultadoArea.setText(text);
    }

    // Listeners para los botones
    public void agregarListenerAgregar(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void agregarListenerEditar(ActionListener listener) {
        editarButton.addActionListener(listener);
    }

    public void agregarListenerEliminar(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }

    public void agregarListenerBuscar(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }
}