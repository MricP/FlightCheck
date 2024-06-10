/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Robi6
 */
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * La classe ButtonWaypoint représente un bouton personnalisé avec une icône spécifique
 * et quelques propriétés de style modifiées.
 * <p>
 * Cette classe hérite de {@link JButton} et configure les propriétés suivantes :
 * - Remplissage de la zone de contenu désactivé
 * - Icône personnalisée
 * - Curseur en forme de main
 * - Taille fixe de 24x24 pixels
 * - Bordure non peinte
 * </p>
 * 
 * Exemple d'utilisation :
 * <pre>
 *     ButtonWaypoint bouton = new ButtonWaypoint();
 *     panneau.add(bouton);
 * </pre>
 * 
 * @see JButton
 */
public class ButtonWaypoint extends JButton {

    /**
     * Constructeur par défaut de la classe ButtonWaypoint.
     * <p>
     * Ce constructeur initialise le bouton avec les propriétés suivantes :
     * - Zone de contenu non remplie
     * - Icône définie à partir du chemin de ressource "/graphe/aero.png"
     * - Curseur de type main
     * - Taille de 24x24 pixels
     * - Bordure désactivée
     * </p>
     */
    public ButtonWaypoint() {
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/graphe/aero.png")));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24, 24));
        setBorderPainted(false);
    }
}