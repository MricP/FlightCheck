package graphe;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.graphstream.graph.Graph;
import org.jxmapviewer.painter.CompoundPainter;

public class InterfaceIHMSAE extends JFrame {

    private JXMapViewer mapViewer;
    private Set<Waypoint> waypoints;
    private ListeAeroport listeAeroport;
    private ListeVols listeVolCarte;
    private ListeVols listeVolGraphe;
    private Main main;
    private CompoundPainter<JXMapViewer> compoundPainter;
    private static List<Color> colorList;
    private ArrayList<String> codeaero;
    private ArrayList<GeoPosition> geoCondition;
    private JCheckBox colorationCheckbox;
    private boolean allgood;
    private JTextField heureField;
    private JTextField minuteField;
    private JSpinner kmaxSpinner;
    
    
    public InterfaceIHMSAE() {
        allgood = false;
        main = new Main();
        colorList = new ArrayList<>();
        setTitle("FlightSAE 1.0.0");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        Color bgColor = Color.decode("#283C4F");
        getContentPane().setBackground(bgColor);
        compoundPainter = new CompoundPainter<>();
        codeaero = new ArrayList<>();
        geoCondition = new ArrayList<>();
        
        mapViewer = new JXMapViewer();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        GeoPosition france = new GeoPosition(46.603354, 1.888334);
        mapViewer.setZoom(13);
        mapViewer.setAddressLocation(france);

        MouseInputListener mil = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mil);
        mapViewer.addMouseMotionListener(mil);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(mapViewer, gbc);

        JPanel leftControlPanel = new JPanel();
        leftControlPanel.setLayout(new GridBagLayout());
        leftControlPanel.setBackground(bgColor);
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets = new Insets(5, 5, 5, 5);
        lc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton aeroportsButton = new JButton("Aéroports");
        styleButton(aeroportsButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 0;
        leftControlPanel.add(aeroportsButton, lc);
        
        JButton volsButton = new JButton("Vols");
        styleButton(volsButton, bgColor);
        lc.gridx = 0;
        lc.gridy = 1;
        leftControlPanel.add(volsButton, lc);
        
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
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(leftControlPanel, gbc);
        
        JPanel rightControlPanel = new JPanel();
        rightControlPanel.setLayout(new GridBagLayout());
        rightControlPanel.setBackground(bgColor);
        GridBagConstraints rc = new GridBagConstraints();
        rc.insets = new Insets(5, 5, 5, 5);
        rc.fill = GridBagConstraints.HORIZONTAL;
        
        /*
        JCheckBox conflitsCheckbox = new JCheckBox("conflits");
        styleCheckBox(conflitsCheckbox, bgColor);
        rc.gridx = 0;
        rc.gridy = 1;
        rightControlPanel.add(conflitsCheckbox, rc);
        */
        
        JLabel kmaxLabel = new JLabel("K-max : ");
        kmaxLabel.setForeground(Color.WHITE);
        rc.gridx = 0;
        rc.gridy = 2;
        rc.anchor = GridBagConstraints.EAST;
        rightControlPanel.add(kmaxLabel, rc);

        kmaxSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        rc.gridx = 1;
        rc.gridy = 2;
        rightControlPanel.add(kmaxSpinner, rc);
        
        colorationCheckbox = new JCheckBox("coloration");
        styleCheckBox(colorationCheckbox, bgColor);
        rc.gridx = 0;
        rc.gridy = 1;
        rightControlPanel.add(colorationCheckbox, rc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(rightControlPanel, gbc);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(bgColor);
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(5, 5, 5, 5);
        b.fill = GridBagConstraints.HORIZONTAL;
/*
        JButton startButton = new JButton("Start");
        styleButton(startButton, bgColor);
        b.gridx = 1;
        b.gridy = 0;
        bottomPanel.add(startButton, b);
*/
        
        JButton drawLinesButton = new JButton("Draw all Lines");
        styleButton(drawLinesButton, bgColor);
        b.gridx = 1;
        b.gridy = 0;
        bottomPanel.add(drawLinesButton, b);
        
        JButton drawLinesHourButton = new JButton("Draw Lines with hour");
        styleButton(drawLinesHourButton, bgColor);
        b.gridx = 2;
        b.gridy = 0;
        bottomPanel.add(drawLinesHourButton, b);
        
        JButton drawLinescouleurButton = new JButton("Draw Lines with couleur");
        styleButton(drawLinescouleurButton, bgColor);
        b.gridx = 3;
        b.gridy = 0;
        bottomPanel.add(drawLinescouleurButton, b);
        
        JButton showGraphstream = new JButton("show Graphstream");
        styleButton(showGraphstream, bgColor);
        b.gridx = 0;
        b.gridy = 0;
        bottomPanel.add(showGraphstream, b);
        
        
        JLabel algorithmLabel = new JLabel("Algorithme Sélectionné : ");
        algorithmLabel.setForeground(Color.WHITE);
        b.gridx = 0;
        b.gridy = 1;
        b.anchor = GridBagConstraints.EAST;
        bottomPanel.add(algorithmLabel, b);
        
        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{"Welsh et Powell", "Glouton"});
        b.gridx = 1;
        b.gridy = 1;
        bottomPanel.add(algorithmComboBox, b);
      /*  
        JLabel kmaxLabel = new JLabel("K-max : ");
        kmaxLabel.setForeground(Color.WHITE);
        b.gridx = 2;
        b.gridy = 1;
        b.anchor = GridBagConstraints.EAST;
        bottomPanel.add(kmaxLabel, b);
        
        JSpinner kmaxSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 100, 1));
        b.gridx = 3;
        b.gridy = 1;
        bottomPanel.add(kmaxSpinner, b);
        
        */
       
        JButton appliquer  = new JButton("Appliquer");
        b.gridx = 2;
        b.gridy = 1;
        bottomPanel.add(appliquer, b);
                
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(bottomPanel, gbc);
        
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistiques stats = main.calculerStatistiques();
                StatisticsFrame statsFrame = new StatisticsFrame(stats);
                statsFrame.setVisible(true);
            }
        });
        
        showGraphstream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allgood){
                    Graph G2 = main.getGraphStream(listeVolCarte);
                    G2.display();
                }
            }
        });
        
        drawLinescouleurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allgood){
                    //regle a 1
                    openNumberDialog();
                }
            }
        });
        
        drawLinesHourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allgood) {
                    openHourMinuteDialog();
                }
            }
        });
        
 /*     
        
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Start button clicked!");
            }
        });
 
        appliquer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allgood) {
                    if (kmaxCheckbox.isSelected()) {
                        int kmax = (int) kmaxSpinner.getValue();
                        listeVolCarte.setkmax(kmax);
                    }
                    if (algorithmComboBox.getSelectedItem().equals("Glouton")) {
                        listeVolCarte = main.FullGreedyColor(listeVolCarte);
                    } else {
                        listeVolCarte = main.FullWelshPowell(listeVolCarte);
                    }
                } else {
                    System.out.println("please enter your files");
                }
            }
        });
        
        */ 
        
        appliquer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allgood) { // Remplacer true par la condition allgood
                    int kmax = (int) kmaxSpinner.getValue();
                    listeVolCarte.setkmax(kmax);

                    if (algorithmComboBox.getSelectedItem().equals("Glouton")) {
                        listeVolCarte = main.FullGreedyColor(listeVolCarte);
                    } else {
                        listeVolCarte = main.FullWelshPowell(listeVolCarte);
                    }
                } else {
                    System.out.println("please enter your files");
                }
            }
        });
        
        
        drawLinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorationCheckbox.isSelected()) {
                    drawLinesAllVolsWithColoration();
                } else {
                    drawLinesAllVolsInBlue();
                }
            }
        });
        
        aeroportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });
        
        volsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooserVols();
            }
        });

        colorationCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorationCheckbox.isSelected()) {
                    drawLinesAllVolsWithColoration();
                } else {
                    drawLinesAllVolsInBlue();
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, Color.decode("#007BFF"));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(exitButton, gbc);

        waypoints = new HashSet<>();
        
        
        /*
        // Initialisation avec des fichiers par défaut
        String FilePath = "C:/Users/Emric/OneDrive/Bureau/S2/SaeFinal/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        File file = new File(FilePath);
        
        main.setAeroportlist(file);
        listeAeroport = main.getlisteaero();
        addAirportMarkers();
        
        FilePath = "C:/Users/Emric/OneDrive/Bureau/S2/SaeFinal/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv";
        file = new File(FilePath);
        
        main.setvolaeroports(file);
        listeVolCarte = main.getlisteVols();
        
        //permet de mettre des couleurs dans notre liste de couleurs
        setcolorlist();
        
        //crée le grpahe avec l'ajout des arretes
        listeVolCarte = main.creationgraphe(listeVolCarte);
        
        //colorie le graphe
        //listeVol  = main.FullGreedyColor(listeVolCarte);
        listeVolCarte.MAXWelshPowell();
        mapViewer.setOverlayPainter(compoundPainter);
        
        
        //changer le kmax
        listeVolCarte.setkmax(listeVolCarte.getkmax());
        
        //enlever/ mettre le kmax
        listeVolCarte.sethavekmax(true);
        
        //l'autre qui comporte le graphe qui ne se modelise pas avec la carte, uniquement avec Graphstream
        FilePath = "C:/Users/Emric/OneDrive/Bureau/S2/SaeFinal/sae_mathieu_petit_pirrera/DataTest/graph-test1.txt";
        //C:/Users/Emric/OneDrive/Bureau/S2/SaeFinal/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv
        file = new File(FilePath);
        listeVolGraphe = main.CreateGraphText(file);
        //listeVolGraphe = main.FullGreedyColor(listeVolGraphe);
        //listeVolGraphe.setcouleurdefault();
            
        //listeVolGraphe = main.FullWelshPowell(listeVolGraphe);
        listeVolGraphe = main.FullWelshPowell(listeVolGraphe);
        https://forge.univ-lyon1.fr/p2306053/sae_mathieu_petit_pirrera.git
        //les Graph provenant de Graphstream des 2 graphes
        
        Graph G1 = main.getGraphStream(listeVolCarte);
        Graph G2 = main.getGraphStream(listeVolGraphe);
        
        System.out.println("nb conflitts : "+listeVolGraphe.getnbconflit());
        System.out.println(listeVolGraphe.goodcoloration());
        G1.display();
        G2.display();
        */
        //pour tester les graphetest
        /*
        String FilePath = "C:/Users/Robi6/OneDrive/Bureau/DataTest/graph-test2.txt";
        //C:/Users/Emric/OneDrive/Bureau/S2/SaeFinal/sae_mathieu_petit_pirrera/DataTest/vol-test8.csv
        File file = new File(FilePath);
        listeVolGraphe = main.CreateGraphText(file);
        //listeVolGraphe = main.FullGreedyColor(listeVolGraphe);
        //listeVolGraphe.setcouleurdefault();
            
        //listeVolGraphe = main.FullWelshPowell(listeVolGraphe);
        listeVolGraphe = main.FullWelshPowell(listeVolGraphe);

        Graph G2 = main.getGraphStream(listeVolGraphe);
        G2.display();
        */
 
    }
    
    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //loadAeroportFile(selectedFile);
            main.setAeroportlist(selectedFile);
            listeAeroport = main.getlisteaero();
            addAirportMarkers();
            
        }
    }
    
    private void openFileChooserVols() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            main.setvolaeroports(selectedFile);
            listeVolCarte = main.getlisteVols();
            listeVolCarte = main.creationgraphe(listeVolCarte);
            listeVolCarte = main.FullGreedyColor(listeVolCarte);
            setcolorlist();
            allgood = true;
            
        }
    }
    
    private void addAirportMarkers() {
        if (listeAeroport == null || listeAeroport.taillelisteaero() == 0) {
            System.out.println("Aucun aéroport à afficher.");
            return;
        }
        
        waypoints.clear();
        codeaero.clear();
        geoCondition.clear();

        for (int i = 0; i < listeAeroport.taillelisteaero(); i++) {
            Aeroport aeroport = listeAeroport.getaeroport(i);
            GeoPosition position = new GeoPosition(aeroport.getlongitude(), aeroport.getlatitude());
            waypoints.add(new DefaultWaypoint(position));
            codeaero.add(aeroport.getcode());
            geoCondition.add(position);
        }
        
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        compoundPainter.addPainter(waypointPainter);
        mapViewer.setOverlayPainter(compoundPainter);
        mapViewer.repaint();
        System.out.println("Les aéroports sont maintenant affichés");
    }
    
private void drawLinesAllVolsInBlue() {
    if (waypoints.isEmpty()) {
        System.out.println("Aucun waypoint disponible pour dessiner des lignes.");
        return;
    }

    compoundPainter = new CompoundPainter<>(); // Crée une nouvelle instance de CompoundPainter

    for (int i = 0; i < listeVolCarte.taille(); i++) {
        List<GeoPosition> positions = new ArrayList<>();
        Vol vol = listeVolCarte.getVolindice(i);
        String codedepart = vol.getcodedepart();
        String codearrivee = vol.getcodearrive();
        GeoPosition positionDepart = null;
        GeoPosition positionArrivee = null;

        for (int y = 0; y < codeaero.size(); y++) {
            if (codeaero.get(y).equals(codedepart)) {
                positionDepart = geoCondition.get(y);
            } else if (codeaero.get(y).equals(codearrivee)) {
                positionArrivee = geoCondition.get(y);
            }
        }

        if (positionDepart != null && positionArrivee != null) {
            positions.add(positionDepart);
            positions.add(positionArrivee);

            RoutePainter routePainter = new RoutePainter(positions, Color.BLUE);
            compoundPainter.addPainter(routePainter);
        } else {
            System.out.println("Erreur : Impossible de trouver les positions pour le vol " + vol.toString());
        }
    }

    WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    waypointPainter.setWaypoints(waypoints);
    compoundPainter.addPainter(waypointPainter);

    mapViewer.setOverlayPainter(compoundPainter);
    mapViewer.repaint();
    System.out.println("Les lignes entre les waypoints ont été dessinées en bleu");
}

private void drawLinesAllVolsWithColoration() {
    if (waypoints.isEmpty()) {
        System.out.println("Aucun waypoint disponible pour dessiner des lignes.");
        return;
    }
    
    compoundPainter = new CompoundPainter<>();
    
    for (int i = 0; i < listeVolCarte.taille(); i++) {
        List<GeoPosition> positions = new ArrayList<>();
        Vol vol = listeVolCarte.getVolindice(i);
        String codedepart = vol.getcodedepart();
        String codearrivee = vol.getcodearrive();
        GeoPosition positionDepart = null;
        GeoPosition positionArrivee = null;

        for (int y = 0; y < codeaero.size(); y++) {
            if (codeaero.get(y).equals(codedepart)) {
                positionDepart = geoCondition.get(y);
            } else if (codeaero.get(y).equals(codearrivee)) {
                positionArrivee = geoCondition.get(y);
            }
        }
        
        if (positionDepart != null && positionArrivee != null) {
            positions.add(positionDepart);
            positions.add(positionArrivee);

            RoutePainter routePainter = new RoutePainter(positions, colorList.get(vol.getcouleur() + 1));
            compoundPainter.addPainter(routePainter);
        } else {
            System.out.println("Erreur : Impossible de trouver les positions pour le vol " + vol.toString());
        }
    }
    
    WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    waypointPainter.setWaypoints(waypoints);
    compoundPainter.addPainter(waypointPainter);
    
    mapViewer.setOverlayPainter(compoundPainter);
    mapViewer.repaint();
    System.out.println("Les lignes entre les waypoints ont été dessinées avec coloration");
}

private void openNumberDialog() {
    int kmax = listeVolCarte.maxcouleur();
    JTextField numberField = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Entrer un nombre entre 1 et " + kmax + ":"));
    myPanel.add(numberField);

    int result = JOptionPane.showConfirmDialog(null, myPanel, 
             "Veuillez entrer un nombre", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        try {
            int number = Integer.parseInt(numberField.getText());
            if (number >= 1 && number <= kmax) {
                drawLinesColorVolsWithColoration(number);
            } else {
                JOptionPane.showMessageDialog(null, "Le nombre doit être entre 1 et " + kmax, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void openHourMinuteDialog() {
    JPanel myPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel heureLabel = new JLabel("Heure : ");
    gbc.gridx = 0;
    gbc.gridy = 0;
    myPanel.add(heureLabel, gbc);

    JTextField heureField = new JTextField(5);
    gbc.gridx = 1;
    gbc.gridy = 0;
    myPanel.add(heureField, gbc);

    JLabel minuteLabel = new JLabel("Minute : ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    myPanel.add(minuteLabel, gbc);

    JTextField minuteField = new JTextField(5);
    gbc.gridx = 1;
    gbc.gridy = 1;
    myPanel.add(minuteField, gbc);

    int result = JOptionPane.showConfirmDialog(null, myPanel, 
             "Veuillez entrer l'heure et les minutes", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        try {
            int heure = Integer.parseInt(heureField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            drawLinesHourVolsWithColoration(heure, minute);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides pour l'heure et les minutes.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}


private void drawLinesColorVolsWithColoration(int couleur) {
    if (waypoints.isEmpty()) {
        System.out.println("Aucun waypoint disponible pour dessiner des lignes.");
        return;
    }
    
    compoundPainter = new CompoundPainter<>();
    
    for (int i = 0; i < listeVolCarte.taille(); i++) {
        List<GeoPosition> positions = new ArrayList<>();
        Vol vol = listeVolCarte.getVolindice(i);
        if(vol.getcouleur() == couleur){
            String codedepart = vol.getcodedepart();
            String codearrivee = vol.getcodearrive();
            GeoPosition positionDepart = null;
            GeoPosition positionArrivee = null;

            for (int y = 0; y < codeaero.size(); y++) {
                if (codeaero.get(y).equals(codedepart)) {
                    positionDepart = geoCondition.get(y);
                } else if (codeaero.get(y).equals(codearrivee)) {
                    positionArrivee = geoCondition.get(y);
                }
            }

            if (positionDepart != null && positionArrivee != null) {
                positions.add(positionDepart);
                positions.add(positionArrivee);

                RoutePainter routePainter = new RoutePainter(positions, colorList.get(vol.getcouleur() + 1));
                compoundPainter.addPainter(routePainter);
            } else {
                System.out.println("Erreur : Impossible de trouver les positions pour le vol " + vol.toString());
            }
        }
    }
    
    WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    waypointPainter.setWaypoints(waypoints);
    compoundPainter.addPainter(waypointPainter);
    
    mapViewer.setOverlayPainter(compoundPainter);
    mapViewer.repaint();
    System.out.println("Les lignes entre les waypoints ont été dessinées avec coloration");
}

private void drawLinesHourVolsWithColoration(int heure, int minute) {
    if (waypoints.isEmpty()) {
        System.out.println("Aucun waypoint disponible pour dessiner des lignes.");
        return;
    }
    
    compoundPainter = new CompoundPainter<>();
    int minutes = heure * 60 + minute;
    for (int i = 0; i < listeVolCarte.taille(); i++) {
        List<GeoPosition> positions = new ArrayList<>();
        Vol vol = listeVolCarte.getVolindice(i);
        if (vol.getminutesdepart() <= minutes && vol.getminutes_arrive() >= minutes){
            String codedepart = vol.getcodedepart();
            String codearrivee = vol.getcodearrive();
            GeoPosition positionDepart = null;
            GeoPosition positionArrivee = null;
            
            for (int y = 0; y < codeaero.size(); y++) {
                if (codeaero.get(y).equals(codedepart)) {
                    positionDepart = geoCondition.get(y);
                } else if (codeaero.get(y).equals(codearrivee)) {
                    positionArrivee = geoCondition.get(y);
                }
            }

            if (positionDepart != null && positionArrivee != null) {
                positions.add(positionDepart);
                positions.add(positionArrivee);

                RoutePainter routePainter = new RoutePainter(positions, colorList.get(vol.getcouleur() + 1));
                compoundPainter.addPainter(routePainter);
            } else {
                System.out.println("Erreur : Impossible de trouver les positions pour le vol " + vol.toString());
            }
        }
    }
    
    WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    waypointPainter.setWaypoints(waypoints);
    compoundPainter.addPainter(waypointPainter);
    
    mapViewer.setOverlayPainter(compoundPainter);
    mapViewer.repaint();
    System.out.println("Les lignes entre les waypoints ont été dessinées avec coloration");
}
    
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    
    private void styleCheckBox(JCheckBox checkBox, Color bgColor) {
        checkBox.setBackground(bgColor);
        checkBox.setForeground(Color.WHITE);
        checkBox.setOpaque(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceIHMSAE().setVisible(true);
            }
        });
    }
    
    private static Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return new Color(red, green, blue);
    }
    
    private static void setcolorlist(){
        for (int i = 0; i < 100; i++) {
            Color randomColor = getRandomColor();
            colorList.add(randomColor);
        }
    }
}