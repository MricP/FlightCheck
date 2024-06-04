/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import graphe.Statistiques;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emric
 */
public class StatitiquesTest {
    Statistiques stat;
    public StatitiquesTest() {
    }

    @Before
    public void setUp() {
        stat = new Statistiques(2.0,2,5,4,2);
    }
    
    @Test
    public void getDegreMoyenTest() {
        assertTrue(stat.getDegreMoyen() == 2.0);
    }

    @Test
    public void getNbComposantesTest() {
        assertTrue(stat.getNbComposantes() == 2);
    }

    @Test
    public void getNbNoeudsTest() {
        assertEquals(5,stat.getNbNoeuds(),1);
    }

    @Test
    public void getNbAretesTest() {
        assertEquals(4,stat.getNbAretes(),1);
    }

    @Test
    public void getDiametreTest() {
        assertTrue(stat.getDiametre() == 2.0);
    }
}
