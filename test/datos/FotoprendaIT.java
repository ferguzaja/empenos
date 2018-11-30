/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import logica.FotoPrenda;
import logica.Prenda;
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
public class FotoprendaIT {
    
    public FotoprendaIT() {
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
     * Test of getIdfotoPrenda method, of class Fotoprenda.
     */
    @Test
    public void testGetIdfotoPrenda() {
        System.out.println("getIdfotoPrenda");
        Fotoprenda instance = new Fotoprenda();
        Integer expResult = null;
        Integer result = instance.getIdfotoPrenda();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdfotoPrenda method, of class Fotoprenda.
     */
    @Test
    public void testSetIdfotoPrenda() {
        System.out.println("setIdfotoPrenda");
        Integer idfotoPrenda = null;
        Fotoprenda instance = new Fotoprenda();
        instance.setIdfotoPrenda(idfotoPrenda);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaHora method, of class Fotoprenda.
     */
    @Test
    public void testGetFechaHora() {
        System.out.println("getFechaHora");
        Fotoprenda instance = new Fotoprenda();
        Date expResult = null;
        Date result = instance.getFechaHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaHora method, of class Fotoprenda.
     */
    @Test
    public void testSetFechaHora() {
        System.out.println("setFechaHora");
        Date fechaHora = null;
        Fotoprenda instance = new Fotoprenda();
        instance.setFechaHora(fechaHora);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrendaIdprenda method, of class Fotoprenda.
     */
    @Test
    public void testGetPrendaIdprenda() {
        System.out.println("getPrendaIdprenda");
        Fotoprenda instance = new Fotoprenda();
        Prenda expResult = null;
        Prenda result = instance.getPrendaIdprenda();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrendaIdprenda method, of class Fotoprenda.
     */
    @Test
    public void testSetPrendaIdprenda() {
        System.out.println("setPrendaIdprenda");
        Prenda prendaIdprenda = null;
        Fotoprenda instance = new Fotoprenda();
        instance.setPrendaIdprenda(prendaIdprenda);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Fotoprenda.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Fotoprenda instance = new Fotoprenda();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Fotoprenda.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Fotoprenda instance = new Fotoprenda();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Fotoprenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Fotoprenda instance = new Fotoprenda();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscaFotos method, of class Fotoprenda.
     */
    @Test
    public void testBuscaFotos() {
        System.out.println("buscaFotos");
        int prenda = 0;
        Fotoprenda instance = new Fotoprenda();
        List<Fotoprenda> expResult = null;
        List<Fotoprenda> result = instance.buscaFotos(prenda);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFoto method, of class Fotoprenda.
     */
    @Test
    public void testGetFoto() {
        System.out.println("getFoto");
        Fotoprenda instance = new Fotoprenda();
        byte[] expResult = null;
        byte[] result = instance.getFoto();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFoto method, of class Fotoprenda.
     */
    @Test
    public void testSetFoto() {
        System.out.println("setFoto");
        byte[] foto = null;
        Fotoprenda instance = new Fotoprenda();
        instance.setFoto(foto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deLogicaADatos method, of class Fotoprenda.
     */
    @Test
    public void testDeLogicaADatos() {
        System.out.println("deLogicaADatos");
        List<FotoPrenda> listaFotos = null;
        List<Fotoprenda> expResult = null;
        List<Fotoprenda> result = Fotoprenda.deLogicaADatos(listaFotos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarFotosPrendas method, of class Fotoprenda.
     */
    @Test
    public void testGuardarFotosPrendas() {
        System.out.println("guardarFotosPrendas");
        List<List<FotoPrenda>> listaFotos = null;
        Fotoprenda.guardarFotosPrendas(listaFotos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of devuelveFotos method, of class Fotoprenda.
     */
    @Test
    public void testDevuelveFotos() {
        System.out.println("devuelveFotos");
        int idPrenda = 0;
        boolean nuevo = false;
        List<FotoPrenda> expResult = null;
        List<FotoPrenda> result = Fotoprenda.devuelveFotos(idPrenda, nuevo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of datosALogica method, of class Fotoprenda.
     */
    @Test
    public void testDatosALogica() {
        System.out.println("datosALogica");
        Fotoprenda foto = null;
        boolean nuevo = false;
        FotoPrenda expResult = null;
        FotoPrenda result = Fotoprenda.datosALogica(foto, nuevo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of devuelveImagen method, of class Fotoprenda.
     */
    @Test
    public void testDevuelveImagen() {
        System.out.println("devuelveImagen");
        byte[] bytes = null;
        Image expResult = null;
        Image result = Fotoprenda.devuelveImagen(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of devuelveArregloFotos method, of class Fotoprenda.
     */
    @Test
    public void testDevuelveArregloFotos() {
        System.out.println("devuelveArregloFotos");
        List<Prenda> listaPrendas = null;
        boolean nuevo = false;
        List<List<FotoPrenda>> expResult = null;
        List<List<FotoPrenda>> result = Fotoprenda.devuelveArregloFotos(listaPrendas, nuevo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
