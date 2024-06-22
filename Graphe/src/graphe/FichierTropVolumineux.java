/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package graphe;

/**
 *
 * @author Emric
 */
public class FichierTropVolumineux extends Exception{

    /**
     * Creates a new instance of <code>FichierTropVolumineux</code> without
     * detail message.
     */
    public FichierTropVolumineux() {
        super("Le fichier est trop volumineux");
    }

}
