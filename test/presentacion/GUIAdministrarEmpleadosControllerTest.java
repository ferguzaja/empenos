/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ferguzaja
 */
public class GUIAdministrarEmpleadosControllerTest {
    
    public GUIAdministrarEmpleadosControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class GUIAdministrarEmpleadosController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        GUIAdministrarEmpleadosController instance = new GUIAdministrarEmpleadosController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llenaTabla method, of class GUIAdministrarEmpleadosController.
     */
    @Test
    public void testLlenaTabla() {
        System.out.println("llenaTabla");
        GUIAdministrarEmpleadosController instance = new GUIAdministrarEmpleadosController();
        instance.llenaTabla();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
