/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListeVolsFrame extends JFrame {

    public ListeVolsFrame(Object[][] data, String code) {
        // Configuration de la JFrame
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/graphe/logo.png"));
        setIconImage(logoIcon.getImage());
        setTitle("Tableau des vols de l'aéroport "+code);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        // Filtrer les lignes vides
        Object[][] filteredData = filterEmptyRows(data);

        // Création d'un panel pour contenir le tableau
        JPanel panel = new JPanel(new BorderLayout());

        // Noms des colonnes
        String[] columnNames = {"Nom", "Départ", "Destination", "Heure de depart", "Heure d'arrivée", "Durée", "Couleur/ Niveau de vol"};

        // Création du tableau
        JTable table = new JTable(filteredData, columnNames);

        // Ajout du tableau dans un JScrollPane pour le défilement
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Ajout du panel à la JFrame
        add(panel);
        pack();
       
    }
    
    private Object[][] filterEmptyRows(Object[][] data) {
        List<Object[]> filteredList = new ArrayList<>();

        for (Object[] row : data) {
            boolean isEmpty = true;
            for (Object cell : row) {
                if (cell != null && !cell.toString().trim().isEmpty()) {
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                filteredList.add(row);
            }
        }

        return filteredList.toArray(new Object[0][]);
    }
    
    public static void main(String[] args) {
        Object[][] data = {
            {"Vol 1", "Paris", "Londres", "08:00", "09:00", "1h", "Bleu"},
            {"Vol 2", "Paris", "", "10:00", "11:00", "1h", "Rouge"},
            {"", "", "", "", "", "", ""},
            {"Vol 4", "Paris", "Berlin", "12:00", "14:00", "2h", "Vert"}
        };

        ListeVolsFrame frame = new ListeVolsFrame(data,"ee");
        frame.setVisible(true);
    }
}

