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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jxmapviewer.painter.CompoundPainter;

public class InterfaceIHMSAE extends JFrame {

    private JXMapViewer mapViewer;
    private Set<Waypoint> waypoints;
    private ListeAeroport listeAeroport;
    private ListeVols listeVol;
    private Main main;
    private CompoundPainter<JXMapViewer> compoundPainter ;
    
    private ArrayList<String> codeaero;
    private ArrayList<GeoPosition> geoCondition;
    public InterfaceIHMSAE() {
        main = new Main();
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
        TileFactoryInfo info = new OSMTileFactoryInfo() {
            @Override
            public int getMinimumZoomLevel() {
                return 5;
            }

            @Override
            public int getMaximumZoomLevel() {
                return 19;
            }
        };
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        GeoPosition france = new GeoPosition(46.603354, 1.888334);
        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(france);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
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

        JButton startButton = new JButton("Start");
        styleButton(startButton, bgColor);
        b.gridx = 1;
        b.gridy = 0;
        bottomPanel.add(startButton, b);

        JButton drawLinesButton = new JButton("Draw Lines");
        styleButton(drawLinesButton, bgColor);
        b.gridx = 2;
        b.gridy = 0;
        bottomPanel.add(drawLinesButton, b);

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
                StatisticsFrame statsFrame = new StatisticsFrame();
                statsFrame.setVisible(true);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Start button clicked!");
            }
        });
            
        drawLinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLinesBetweenWaypoints();
            }
        });
        
        aeroportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAirportMarkers();
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

        String filePath = "C:/Users/Aspect-PC/Desktop/SAE-IMH/sae_mathieu_petit_pirrera/DataTest/aeroports.txt";
        listeAeroport = new ListeAeroport();

        main.setAeroportlist();
        listeAeroport = main.getlisteaero();
        addAirportMarkers();
        
        main.setvolaeroports();
        listeVol = main.getlisteVols();
        listeVol = main.creationgraphe(listeVol);
        
        mapViewer.setOverlayPainter(compoundPainter);
        
    }
    
    private void addAirportMarkers() {
        if (listeAeroport == null || listeAeroport.taillelisteaero() == 0) {
            System.out.println("Aucun aéroport à afficher.");
            return;
        }

        waypoints.clear();

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
    
    private void drawLinesBetweenWaypoints() {
        if (waypoints.isEmpty()) {
            System.out.println("Aucun waypoint disponible pour dessiner des lignes.");
            return;
        }
        
        for (int i=0; i <  listeVol.taille();i++ ){
            

            List<GeoPosition> positions = new ArrayList<>();
            Vol vol= listeVol.getVolindice(i);
            String codedepart = vol.getcodedepart();
            String codearrivée = vol.getcodearrive();
            for (int y=0; y < codeaero.size();y++){
                if (codeaero.get(y).equals(codedepart)){
                    positions.add(geoCondition.get(y));
                }else if(codeaero.get(y).equals(codearrivée)){
                    positions.add(geoCondition.get(y));
                }
            }
            
            RoutePainter routePainter = new RoutePainter(positions);
            compoundPainter.addPainter(routePainter);
            
        }
        
        
        mapViewer.setOverlayPainter(compoundPainter);
        mapViewer.repaint();
        System.out.println("Les lignes entre les waypoints ont été dessinées");
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
    
}
