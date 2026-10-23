package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BatallonTest {
    private static final Logger LOG = Logger.getLogger(BatallonTest.class.getName());
    private Batallon batallon;
    private TransporteTropas tropas;

    @BeforeEach
    void Preparar() {
        LOG.warning("Ejecución del BeforeEach");
        batallon = new Batallon("2", "Batallon Ingeneria", "03", "Filandia", 3);
        tropas = new TransporteTropas("03", "Campero", 2005, 1000, EstadoOperativo.DISPONIBLE, 8);
        batallon.registrarVehiculo(tropas);
    }

    private void crearMision(int numero, EstadoMision estadoMision) {
        Mision mision = new Mision("J" + numero, LocalDate.of(2016, 1, 12).plusDays(numero), "Filandia");
        mision.agregarPersonal("Juan Pacheco");
        assertTrue(batallon.registrarMision(mision));
        assertTrue(batallon.asignarVehiculoMision(mision.getId(), tropas.getId()));
        if (estadoMision == EstadoMision.EN_CURSO || estadoMision == EstadoMision.COMPLETADA) {
            assertTrue(batallon.iniciarMision(mision.getId()));
        }
        if (estadoMision == EstadoMision.COMPLETADA) {
            assertTrue(batallon.completarMision(mision.getId()));
        }
    }


    //Registrar mision con personal y vehiculo
    @DisplayName("Prueba 7: Registrar mision con personal y vehiculo")
    @Test
    void testRegistrarMision() {
        Mision mision = new Mision("06", LocalDate.of(2006, 07, 01), "Filandia");
        mision.agregarPersonal("Juan Pacheco");

        assertTrue(batallon.registrarMision(mision));
        assertTrue(batallon.asignarVehiculoMision("06", tropas.getId()));
        assertEquals(mision, batallon.buscarMision("06"));
        assertEquals("Filandia", mision.getUbicacion());
        assertEquals("Juan Pacheco", mision.getPersonalAsignado().get(0));
        assertEquals(1, mision.getPersonalAsignado().size());
        assertEquals(tropas, mision.getListaDetallesMision().get(0).getVehiculo());
    }

    //contar solo las mision completadas
    @DisplayName("Prueba 8: contar misiones completadas")
    @Test
    void testContarMisiones() {
        for (int i = 1; i <= 3; i++) {
            crearMision(i, EstadoMision.COMPLETADA);
        }
        for (int i = 4; i <= 5; i++) {
            crearMision(i, EstadoMision.PLANIFICADA);
        }
        crearMision(6, EstadoMision.EN_CURSO);
        assertEquals(3, tropas.contarMisionesCompletadas());
    }

    @DisplayName("Prueba 9: Vehiculo con exactamente 50 misiones")
    @Test
    void testVehiculo50Misiones() {
        for (int i = 1; i <= 50; i++) {
            crearMision(i, EstadoMision.COMPLETADA);
        }

    }
}