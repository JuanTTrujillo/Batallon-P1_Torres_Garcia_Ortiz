package co.edu.uniquindio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoExistenteTest {

    @DisplayName("Consultar vehículo por ID")
    @Test
    void testConsultarVehiculo() {
        Batallon batallon = new Batallon("B01", "Batallon Uno", "Brigada 1",
                "Distrito 1", 1);
        Vehiculo vehiculo = new TransporteTropas("1234", "Twingo",
                2022, 12000, EstadoOperativo.DISPONIBLE, 2);

        batallon.registrarVehiculo(vehiculo);

        assertSame(vehiculo, batallon.buscarVehiculo("1234"));
        assertNull(batallon.buscarVehiculo("9999"));
    }
}