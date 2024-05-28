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

/**
 * La classe StatisticsFrame représente une fenêtre affichant diverses statistiques.
 */
public class StatisticsFrame extends JFrame {

    /**
     * Constructeur par défaut de la classe StatisticsFrame.
     * Initialise les composants de l'interface utilisateur et configure la fenêtre.
     */
    public StatisticsFrame() {
        setTitle("Statistiques");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ajout d'une couleur de fond
        Color bgColor = Color.decode("#283C4F");
        getContentPane().setBackground(bgColor);

        // Ajout du bouton quitter "Exit"
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.decode("#007BFF"));
        exitButton.setForeground(Color.WHITE);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(e -> dispose());
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(exitButton, gbc);

        // Ajout du label du titre
        JLabel titleLabel = new JLabel("Statistiques");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Labels et panneaux des statistiques
        String[] labels = {"Degré Moyen", "Composantes", "Nœuds", "Arrêtes", "Diamètre"};
        int labelCount = labels.length;

        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int i = 0; i < labelCount; i++) {
            gbc.gridx = i % 3;
            gbc.gridy = 1 + (i / 3) * 2;

            JLabel statLabel = new JLabel(labels[i], SwingConstants.CENTER);
            statLabel.setForeground(Color.WHITE);
            add(statLabel, gbc);

            gbc.gridy = 2 + (i / 3) * 2;
            JPanel statPanel = new JPanel();
            statPanel.setPreferredSize(new Dimension(150, 150));
            statPanel.setBackground(Color.LIGHT_GRAY);
            add(statPanel, gbc);
        }
    }

    


    /**
     * Méthode principale pour exécuter l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StatisticsFrame frame = new StatisticsFrame();
            frame.setVisible(true);
        });
    }
}
