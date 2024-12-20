package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.com.parking.models.dto.AccessControlDTO;
import py.com.parking.models.entities.AccessControl;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.entities.ParkingOccupancy;
import py.com.parking.models.entities.Users;
import py.com.parking.models.entities.Vehicles;
import py.com.parking.models.mappers.AccessControlMapper;
import py.com.parking.models.repository.AccessControlRepository;
import py.com.parking.models.repository.ParkingOccupancyRepository;
import py.com.parking.models.repository.UserRepository;
import py.com.parking.models.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class AccessControlService {

    @Inject
    ParkingAreaService parkingAreaService;

    @Inject
    AccessControlRepository accessControlRepository;

    @Inject
    VehicleRepository vehicleRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    ParkingOccupancyRepository parkingOccupancyRepository;

    @Transactional
    public AccessControlDTO registerEntry(final Integer parkingAreaId, final String deviceType, final String deviceIdentityValue) {
        final Vehicles vehicle = getVehicleIdentity(deviceType, deviceIdentityValue);
        final Optional<ParkingArea> parkingArea = parkingAreaService.getParkingAreaById(parkingAreaId);
        if(parkingArea.isEmpty()) {
            throw new IllegalArgumentException("Parking Area " + parkingAreaId + " is invalid.");
        } else if (parkingArea.get().getTotalSpaces().equals(parkingArea.get().getOccupiedSpaces())) {
            throw new IllegalArgumentException("Parking Area " + parkingAreaId + " does not contain available free spaces.");
        }
        parkingAreaService.updateParkingAreaOccupancy(parkingArea.get().getId(), 1);
        final LocalDateTime currentTime = LocalDateTime.now();

        ParkingOccupancy parkingOccupancy = new ParkingOccupancy();
        parkingOccupancy.setVehicle(vehicle);
        parkingOccupancy.setParkingArea(parkingArea.get());
        parkingOccupancy.setCreatedAt(currentTime);
        parkingOccupancy.setEntryTime(currentTime);
        parkingOccupancy.setIsOccupied(true);
        parkingOccupancy.setCreatedAt(currentTime);
        parkingOccupancy.setUpdatedAt(currentTime);
        parkingOccupancyRepository.save(parkingOccupancy);

        AccessControl accessControl = new AccessControl();
        accessControl.setVehicle(vehicle);
        accessControl.setEntryTime(currentTime);
        accessControl.setCreatedAt(currentTime);
        accessControl.setUpdatedAt(currentTime);
        accessControl.setIsAuthorized(true);
        return AccessControlMapper.INSTANCE.toDTO(accessControlRepository.save(accessControl));
    }

    @Transactional
    public AccessControlDTO registerExit(final Integer parkingAreaId, final String deviceType, final String deviceIdentityValue) {
        final Vehicles vehicle = getVehicleIdentity(deviceType, deviceIdentityValue);
        final Optional<AccessControl> accessControlEntity = accessControlRepository.findVehicleAndExitTime(vehicle.getId());
        if (accessControlEntity.isEmpty()) {
            throw new IllegalArgumentException("No active parking area found for vehicle with identity " + deviceIdentityValue);
        }

        final Optional<ParkingArea> parkingArea = parkingAreaService.getParkingAreaById(parkingAreaId);
        if(parkingArea.isEmpty()) {
            throw new IllegalArgumentException("Parking Area " + parkingAreaId + " is invalid.");
        }
        final LocalDateTime currentTime = LocalDateTime.now();

        parkingAreaService.updateParkingAreaOccupancy(parkingArea.get().getId(), -1);

        ParkingOccupancy parkingOccupancy = parkingOccupancyRepository.findByVehicleIdAndParkingAreaIdAndExitTimeIsNull(vehicle.getId(), parkingArea.get().getId());
        parkingOccupancy.setExitTime(currentTime);
        parkingOccupancy.setIsOccupied(false);
        parkingOccupancyRepository.save(parkingOccupancy);

        AccessControl accessControl = accessControlEntity.get();
        accessControl.setExitTime(currentTime);
        return AccessControlMapper.INSTANCE.toDTO(accessControlRepository.save(accessControl));
    }

    public long getCurrentOccupancy() {
        return accessControlRepository.findAll().stream()
                .filter(accessControl -> accessControl.getExitTime() == null)
                .count();
    }

    private Vehicles getVehicleIdentity(final String deviceType, final String deviceIdentityValue) {
        Optional<Vehicles> vehicle;
        switch(deviceType) {
            case "license_plate_reader":
                vehicle = vehicleRepository.findVehiclesByLicensePlate(deviceIdentityValue);
                if (vehicle.isEmpty()) {
                    throw new IllegalArgumentException("Vehicle with license plate " + deviceIdentityValue + " is not registered.");
                }
                break;
            case "id_card_reader":
                try {
                    final Integer userId = Integer.valueOf(deviceIdentityValue);
                    Optional<Users> user = userRepository.findById(userId);
                    if (user.isEmpty()) {
                        throw new IllegalArgumentException("User with identity " + deviceIdentityValue + " is not registered.");
                    }
                    vehicle = vehicleRepository.findUsersById(user.get().getId());
                    if (vehicle.isEmpty()) {
                        throw new IllegalArgumentException("User Vehicle identification " + deviceIdentityValue + " is not registered.");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("User identity " + deviceIdentityValue + " is invalid.");
                }
                break;
            default:
                throw new IllegalArgumentException("Device Type is a invalid registered.");
        }
        return vehicle.get();
    }
}
