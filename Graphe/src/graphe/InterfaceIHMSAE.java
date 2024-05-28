/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe InterfaceIHMSAE représente l'interface utilisateur principale pour l'application FlightSAE.
 * Elle inclut une carte interactive, des panneaux de contrôle et divers composants d'interface.
 */
public class InterfaceIHMSAE extends JFrame {

    /**
     * Visionneuse de carte pour afficher et interagir avec la carte.
     */
    private JXMapViewer mapViewer;

    /**
     * Ensemble de waypoints à afficher sur la carte.
     */
    private Set<Waypoint> waypoints;

    /**
     * Constructeur par défaut de la classe InterfaceIHMSAE.
     * Initialise les composants de l'interface utilisateur et configure la fenêtre.
     */
    public InterfaceIHMSAE() {
        setTitle("FlightSAE 1.0.0");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ajout d'une couleur background 
        Color bgColor = Color.decode("#283C4F");
        getContentPane().setBackground(bgColor);

        // Initialisation de la visionneuse de carte
        mapViewer = new JXMapViewer();
        TileFactoryInfo info = new OSMTileFactoryInfo() {
            @Override
            public int getMinimumZoomLevel() {
                return 5; // Zoom minimum
            }

            @Override
            public int getMaximumZoomLevel() {
                return 19; // Zoom maximum
            }
        };
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
        
        GeoPosition france = new GeoPosition(46.603354, 1.888334);
        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(france);

        // Configuration des interactions de la souris
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        // Carte au centre de la frame
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(mapViewer, gbc);

        // Panneau de contrôle à gauche
        JPanel leftControlPanel = new JPanel();
        leftControlPanel.setLayout(new GridBagLayout());
        leftControlPanel.setBackground(bgColor);
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets = new Insets(5, 5, 5, 5);
        lc.fill = GridBagConstraints.HORIZONTAL;

        JButton volsButton = new JButton("Vols");
        styleButton(volsButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 0;
        leftControlPanel.add(volsButton, lc);

        JButton aeroportsButton = new JButton("Aéroports");
        styleButton(aeroportsButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 1;
        leftControlPanel.add(aeroportsButton, lc);

        JButton graphesButton = new JButton("Graphes");
        styleButton(graphesButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 2;
        leftControlPanel.add(graphesButton, lc);

        JButton statsButton = new JButton("Statistiques");
        styleButton(statsButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 3;
        leftControlPanel.add(statsButton, lc);

        // Ajout du panneau de contrôle gauche à la frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(leftControlPanel, gbc);

        // Panneau de contrôle à droite
        JPanel rightControlPanel = new JPanel();
        rightControlPanel.setLayout(new GridBagLayout());
        rightControlPanel.setBackground(bgColor);
        GridBagConstraints rc = new GridBagConstraints();
        rc.insets = new Insets(5, 5, 5, 5);
        rc.fill = GridBagConstraints.HORIZONTAL;

        JCheckBox kmaxCheckbox = new JCheckBox("kmax");
        styleCheckBox(kmaxCheckbox, bgColor);
        rc.gridx = 0;
        rc.gridy = 0;
        rightControlPanel.add(kmaxCheckbox, rc);

        JCheckBox conflitsCheckbox = new JCheckBox("conflits");
        styleCheckBox(conflitsCheckbox, bgColor);
        rc.gridx = 0;
        rc.gridy = 1;
        rightControlPanel.add(conflitsCheckbox, rc);

        JCheckBox colorationCheckbox = new JCheckBox("coloration");
        styleCheckBox(colorationCheckbox, bgColor);
        rc.gridx = 0;
        rc.gridy = 2;
        rightControlPanel.add(colorationCheckbox, rc);

        // Ajout du panneau de contrôle droit à la frame
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(rightControlPanel, gbc);

        // Création du panneau inférieur
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(bgColor);
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(5, 5, 5, 5);
        b.fill = GridBagConstraints.HORIZONTAL;

        JButton startButton = new JButton("Start");
        styleButton(startButton, bgColor);
        b.gridx = 1;
        b.gridy = 0;
        bottomPanel.add(startButton, b);

        JLabel algorithmLabel = new JLabel("Algorithme Sélectionné : ");
        algorithmLabel.setForeground(Color.WHITE);
        b.gridx = 0;
        b.gridy = 1;
        b.anchor = GridBagConstraints.EAST;
        bottomPanel.add(algorithmLabel, b);

        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{"Dijkstra", "A*"});
        b.gridx = 1;
        b.gridy = 1;
        bottomPanel.add(algorithmComboBox, b);

        JLabel kmaxLabel = new JLabel("K-max : ");
        kmaxLabel.setForeground(Color.WHITE);
        b.gridx = 2;
        b.gridy = 1;
        b.anchor = GridBagConstraints.EAST;
        bottomPanel.add(kmaxLabel, b);

        JSpinner kmaxSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        b.gridx = 3;
        b.gridy = 1;
        bottomPanel.add(kmaxSpinner, b);

        // Ajout du panneau inférieur à la frame
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(bottomPanel, gbc);

        // Ajout des écouteurs d'action pour les boutons
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticsFrame statsFrame = new StatisticsFrame();
                statsFrame.setVisible(true);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement start button functionality here
                JOptionPane.showMessageDialog(null, "Start button clicked!");
            }
        });

        aeroportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAirportMarkers();
            }
        });

        // Ajout du bouton quitter "Exit"
        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, Color.decode("#007BFF"));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(exitButton, gbc);

        waypoints = new HashSet<>();
    }

    /**
     * Ajoute des marqueurs d'aéroport à la carte.
     */
    private void addAirportMarkers() {
        double[][] coordinates = {
            {49.012779, 2.55}, // CDG
            {48.723333, 2.379444}, // Orly
            {43.637222, 1.367778}, // Toulouse
            {45.727222, 4.944722} // Lyon
        };

        for (double[] coord : coordinates) {
            GeoPosition position = new GeoPosition(coord[0], coord[1]);
            waypoints.add(new DefaultWaypoint(position));
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);
    }

    /**
     * Applique un style spécifique à un bouton.
     *
     * @param button Le bouton à styliser.
     * @param bgColor La couleur de fond du bouton.
     */
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * Applique un style spécifique à une case à cocher.
     *
     * @param checkBox La case à cocher à styliser.
     * @param bgColor La couleur de fond de la case à cocher.
     */
    private void styleCheckBox(JCheckBox checkBox, Color bgColor) {
        checkBox.setBackground(bgColor);
        checkBox.setForeground(Color.WHITE);
        checkBox.setOpaque(true);
    }

    /**
     * Méthode principale pour exécuter l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceIHMSAE().setVisible(true);
            }
        });
    }
}