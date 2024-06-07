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

public class ListeVolsFrame extends JFrame {
    
    public ListeVolsFrame(Object[][] data) {
        // Configuration de la JFrame
        setTitle("Tableau des vols");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        // Création d'un panel pour contenir le tableau
        JPanel panel = new JPanel(new BorderLayout());

        // Noms des colonnes
        String[] columnNames = {"Nom", "Départ", "Destination", "Heure de depart", "Heure d'arrivée","durée","couleur"};
        
        // Création du tableau
        JTable table = new JTable(data, columnNames);

        // Ajout du tableau dans un JScrollPane pour le défilement
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Ajout du panel à la JFrame
        add(panel);
    }
    
    
}
