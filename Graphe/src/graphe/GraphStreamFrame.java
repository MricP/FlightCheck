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
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import org.graphstream.ui.view.View;

/**
 * La classe GraphStreamFrame représente une fenêtre pour la visualisation d'un graphe
 * en utilisant la bibliothèque GraphStream.
 * <p>
 * Cette classe hérite de {@link JFrame} et configure une interface graphique pour afficher
 * un graphe avec un titre donné.
 * </p>
 */
public class GraphStreamFrame extends JFrame {

    /**
     * Constructeur de la classe GraphStreamFrame.
     * <p>
     * Ce constructeur initialise la fenêtre avec un titre, une taille et un comportement de fermeture par défaut.
     * Il configure également les composants Swing nécessaires pour afficher le graphe et organise
     * ces composants en utilisant un {@link GridBagLayout}.
     * </p>
     * 
     * @param graph le graphe à visualiser
     * @param nom le titre de la fenêtre et du graphe
     */
    public GraphStreamFrame(Graph graph, String nom) {
        // Configuration de la fenêtre principale
        setTitle("Visualisation de Graphe via GraphStream");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants Swing
        JLabel titleLabel = new JLabel(nom, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Configuration de la vue GraphStream
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);

        // Création d'un panel pour contenir la vue GraphStream
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.add((Component) view, BorderLayout.CENTER);

        // Utilisation de GridBagLayout pour une meilleure organisation
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuration des contraintes de GridBag
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ajout du titre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(titleLabel, gbc);

        // Ajout du panel de graphe
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(graphPanel, gbc);
    }

    /**
     * Méthode principale pour démarrer l'application de visualisation de graphe.
     * <p>
     * Cette méthode crée un exemple de graphe avec trois nœuds et trois arêtes,
     * puis affiche une fenêtre {@link GraphStreamFrame} pour visualiser ce graphe.
     * </p>
     * 
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Initialisation du graphe
        Graph graph = new SingleGraph("Graphe Exemple");
        graph.addNode("A").addAttribute("ui.label", "A");
        graph.addNode("B").addAttribute("ui.label", "B");
        graph.addNode("C").addAttribute("ui.label", "C");
        graph.addEdge("AB", "A", "B").addAttribute("ui.label", "AB");
        graph.addEdge("BC", "B", "C").addAttribute("ui.label", "BC");
        graph.addEdge("CA", "C", "A").addAttribute("ui.label", "CA");

        // Création et affichage de la fenêtre
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphStreamFrame(graph, "Titre du Graphe").setVisible(true);
            }
        });
    }
}