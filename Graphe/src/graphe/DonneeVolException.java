/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package graphe;

import java.io.File;

/**
 *
 * @author Emric
 */
public class DonneeVolException extends Exception {

    /**
     * Creates a new instance of <code>DonneeVolException</code> without detail
     * message.
     */
    public DonneeVolException() {
    }

    /**
     * Constructs an instance of <code>DonneeVolException</code> with the
     * specified detail message.
     *
     * @param file the detail file.
     */
    public DonneeVolException(File file) {
        super("Le fichier " + file.getName() + " n'a pas de données valable");
    }
}
