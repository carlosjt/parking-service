package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.com.parking.models.dto.AccessControlDTO;
import py.com.parking.models.entities.AccessControl;
import py.com.parking.models.entities.Vehicles;
import py.com.parking.models.mappers.AccessControlMapper;
import py.com.parking.models.repository.AccessControlRepository;
import py.com.parking.models.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class AccessControlService {

    @Inject
    AccessControlRepository accessControlRepository;

    @Inject
    VehicleRepository vehicleRepository;

    /**
     * Registra la entrada de un vehículo al estacionamiento.
     *
     * @param deviceType Medio de identificacion del usuario.
     * @param licensePlate Matrícula del vehículo.
     * @return Registro de acceso creado.
     */
    @Transactional
    public AccessControlDTO registerEntry(final String deviceType, final String licensePlate) {
        switch(deviceType) {
            case "license_plate_reader":
                Optional<Vehicles> vehicle = vehicleRepository.findVehiclesByLicensePlate(licensePlate);
                if (vehicle.isEmpty()) {
                    throw new IllegalArgumentException("Vehicle with license plate " + licensePlate + " is not registered.");
                }
                break;
            case "id_card_reader":
                break;
            default:
                throw new IllegalArgumentException("Vehicle device is a invalid registered.");
        }


        AccessControl accessControl = new AccessControl();
        accessControl.setVehicle(vehicle.get());
        accessControl.setEntryTime(LocalDateTime.now());
        accessControl.setIsAuthorized(true);

        return AccessControlMapper.INSTANCE.toDTO(accessControlRepository.save(accessControl));
    }

    /**
     * Registra la salida de un vehículo del estacionamiento.
     *
     * @param licensePlate Matrícula del vehículo.
     * @return Registro de acceso actualizado.
     */
    @Transactional
    public AccessControl registerExit(final String deviceType, final String licensePlate) {
        // Busca el registro de entrada más reciente
        AccessControl accessControl = accessControlRepository.find("vehicle.licensePlate = ?1 AND exitTime IS NULL", licensePlate)
                .firstResult();

        if (accessControl == null) {
            throw new IllegalArgumentException("No active entry found for vehicle with license plate " + licensePlate);
        }

        // Registra la salida
        accessControl.setExitTime(LocalDateTime.now());
        return accessControl;
    }

    /**
     * Obtiene la ocupación actual del estacionamiento.
     *
     * @return Número de vehículos actualmente dentro del estacionamiento.
     */
    public long getCurrentOccupancy() {
        return accessControlRepository.find("exitTime IS NULL").count();
    }
}
