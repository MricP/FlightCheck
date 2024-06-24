package graphe;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant une fenêtre pour afficher des statistiques.
 */
public class StatisticsFrame extends JFrame {
    
    private JLabel degreMoyenLabel;
    private JLabel nbComposantesLabel;
    private JLabel nbNoeudsLabel;
    private JLabel nbAretesLabel;
    private JLabel diametreLabel;
    private JLabel nbConflitLabel;
    
    /**
     * Constructeur pour initialiser la fenêtre des statistiques avec les données fournies.
     *
     * @param stats Les statistiques à afficher.
     */
    public StatisticsFrame(Statistiques stats, String Nom) {
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/graphe/logo.png"));
        setIconImage(logoIcon.getImage());
        setTitle("Statistiques "+Nom);
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        Color bgColor = Color.decode("#283C4F");
        getContentPane().setBackground(bgColor);
        
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
        
        JLabel titleLabel = new JLabel("Statistiques "+Nom);
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        String[] labels = {"Degré Moyen", "Composantes", "Nœuds", "Arrêtes", "Diamètre", "Conflits"};
        JLabel[] statLabels = {degreMoyenLabel, nbComposantesLabel, nbNoeudsLabel, nbAretesLabel, diametreLabel,nbConflitLabel};
        String[] values = {
            String.valueOf(stats.getDegreMoyen()),
            String.valueOf(stats.getNbComposantes()),
            String.valueOf(stats.getNbNoeuds()),
            String.valueOf(stats.getNbAretes()),
            String.valueOf(stats.getDiametre()),
            String.valueOf(stats.getConflit())
        };
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = i % 3;
            gbc.gridy = 1 + (i / 3) * 2;

            JLabel statLabel = new JLabel(labels[i], SwingConstants.CENTER);
            statLabel.setForeground(Color.WHITE);
            add(statLabel, gbc);

            gbc.gridy = 2 + (i / 3) * 2;
            JPanel statPanel = new JPanel();
            statPanel.setPreferredSize(new Dimension(150, 150));
            statPanel.setBackground(Color.LIGHT_GRAY);

            statLabels[i] = new JLabel(values[i], SwingConstants.CENTER);
            statPanel.add(statLabels[i]);
            add(statPanel, gbc);
        }
    }

    /**
     * Méthode principale pour lancer l'interface des statistiques.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Statistiques stats = new Statistiques(3.5, 2, 100, 150, 12.3, 4); // Exemple de statistiques
        SwingUtilities.invokeLater(() -> {
            StatisticsFrame frame = new StatisticsFrame(stats,"test");
            frame.setVisible(true);
        });
    }
}
