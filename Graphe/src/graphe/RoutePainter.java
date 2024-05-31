package graphe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

public class RoutePainter implements Painter<JXMapViewer> {
    private List<GeoPosition> track;
    private int couleur;
    private static List<Color> colorList;
    
    
    
    public RoutePainter(List<GeoPosition> track) {
        this.track = track;
        this.couleur = 0;
        colorList = new ArrayList<>();
        setcolorlist();
        
    }
    
    public RoutePainter(List<GeoPosition> track, int couleur) {
        this.track = track;
        this.couleur = couleur;
        colorList = new ArrayList<>();
        setcolorlist();
        
        
    }
    
    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
        g = (Graphics2D) g.create();
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Rendu des lignes quality ++
        g.setColor(Color.BLUE); 
        
        g.setStroke(new BasicStroke(1));
        
        int lastX = -1;
        int lastY = -1;
        
        for (GeoPosition gp : track) {
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
            if (lastX != -1 && lastY != -1) {
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }
            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }
        g.dispose();
    }
    
    private static Color getRandomColor() {
        // Générer des composants de couleur aléatoires entre 0 et 255
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        // Créer et retourner la couleur avec une opacité de 1.0 (complètement opaque)
        return new Color(red, green, blue);
    }
    
    private static void setcolorlist(){
         for (int i = 0; i < 100; i++) {
            // Générer une couleur aléatoire
            Color randomColor = getRandomColor();
            // Ajouter la couleur à la liste
            colorList.add(randomColor);
        }

        
    }
}
