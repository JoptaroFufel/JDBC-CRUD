package com.example.hstutor.Controller;

import com.example.hstutor.Model.*;
import com.example.hstutor.Repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shipments")
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;
    private final ContractRepository contractRepository;
    private final ShipperRepository shipperRepository;
    private final VehicleRepository vehicleRepository;
    private final SVSRepository svsRepository;
    private final PlaceRepository placeRepository;

    public ShipmentController(ShipmentRepository shipmentRepository,
                              ContractRepository contractRepository,
                              ShipperRepository shipperRepository,
                              VehicleRepository vehicleRepository,
                              PlaceRepository placeRepository,
                              SVSRepository svsRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.contractRepository = contractRepository;
        this.shipperRepository = shipperRepository;
        this.vehicleRepository = vehicleRepository;
        this.placeRepository = placeRepository;
        this.svsRepository = svsRepository;
    }

    @GetMapping("list/shipments/all")
    public Iterable<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @GetMapping("list/places/all")
    public Iterable<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @GetMapping("list/shipment/{id}")
    public Shipment getShipmentById(@PathVariable Integer id) {
        return shipmentRepository.findShipmentById(id);
    }

    @GetMapping("list/shipment/contract={id}")
    public List<Shipment> getShipmentByContractId(@PathVariable Integer id) {
        return shipmentRepository.findAllByContract_Id(id);
    }

    @PostMapping("add/shipment")
    public ResponseEntity<?> addShipment(@RequestBody Shipment shipment)  {
        Contract contract = contractRepository.findById(shipment.getContract().getId()).orElseThrow();
        Place departure = placeRepository.findById(shipment.getDeparture().getId()).orElseThrow();
        Place destination = placeRepository.findById(shipment.getDestination().getId()).orElseThrow();
        shipment.setContract(contract);
        shipment.setDeparture(departure);
        shipment.setDestination(destination);
        shipmentRepository.save(shipment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add/place")
    public ResponseEntity<?> addPlace(@RequestBody Place place) {
        if (placeRepository.existsByAddress(place.getAddress())) {
            return new ResponseEntity<>("Already exists", HttpStatus.CONFLICT);
        }
        placeRepository.save(place);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list/svs/all")
    public Iterable<SVS> getAllSVS() {
        return svsRepository.findAll();
    }

    @GetMapping("list/svs/shipment={id}")
    public List<SVS> getSVSByShipmentId(@PathVariable Integer id) {
        return svsRepository.findAllByPk_Shipment_Id(id);
    }

    @PostMapping("add/svs")
    public ResponseEntity<?> addSVS(@RequestBody SVSPK svspk) {
        if (svsRepository.existsByPk(svspk)) {
            return new ResponseEntity<>("Already exists", HttpStatus.CONFLICT);
        }
        Vehicle vehicle = vehicleRepository.findById(svspk.getVehicle().getId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(svspk.getShipment().getId()).orElseThrow();
        Shipper shipper = shipperRepository.findById(svspk.getShipper().getId()).orElseThrow();
        svspk.setShipment(shipment);
        svspk.setShipper(shipper);
        svspk.setVehicle(vehicle);
        SVS svs = new SVS();
        svs.setPk(svspk);
        svsRepository.save(svs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("list/vehicles/all")
    public Iterable<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
