package co.edu.uniquindio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger; 

import static org.junit.jupiter.api.Assertions.*;

class VehiculoExistenteTest {

    private static final Logger LOG = Logger.getLogger(VehiculoExistenteTest.class.getName());

    @DisplayName("Consultar vehículo por ID")
    @Test
    void testConsultarVehiculo() {
        Batallon batallon = new Batallon("B01", "Batallon Uno", "Brigada 1",
                "Distrito 1", 1);
        Vehiculo vehiculo = new TransporteTropas("1234", "Twingo",
                2022, 12000, EstadoOperativo.DISPONIBLE, 2);

        batallon.registrarVehiculo(vehiculo);

        assertSame(vehiculo, batallon.buscarVehiculo("1234"));
        LOG.info("Vehículo con ID 1234 encontrado correctamente");

        assertNull(batallon.buscarVehiculo("9999"));
        LOG.warning("No existe ningún vehículo con el ID 9999");
    }
}