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
public class DonneeEroneException extends Exception {

    /**
     * Creates a new instance of <code>DonneeVolException</code> without detail
     * message.
     */
    public DonneeEroneException() {
    }

    /**
     * Constructs an instance of <code>DonneeVolException</code> with the
     * specified detail message.
     *
     * @param file the detail file.
     */
    public DonneeEroneException(File file) {
        super("Le fichier " + file.getName() + " n'a pas de données valable");
    }
}
