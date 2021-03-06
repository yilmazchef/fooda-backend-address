package be.fooda.backend.address.view;

import be.fooda.backend.address.client.FoodaUserClient;
import be.fooda.backend.address.dao.FoodaAddressIndexRepository;
import be.fooda.backend.address.dao.FoodaAddressRepository;
import be.fooda.backend.address.model.create.FoodaAddressCreate;
import be.fooda.backend.address.model.entity.FoodaAddress;
import be.fooda.backend.address.model.http.FoodaAddressHttpFailureMessages;
import be.fooda.backend.address.model.http.FoodaAddressHttpSuccessMessages;
import be.fooda.backend.address.service.FoodaAddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodaAddressController {

    private final FoodaUserClient userClient;
    private final FoodaAddressMapper addressMapper;
    private final FoodaAddressRepository addressRepository;
    private final FoodaAddressIndexRepository indexRepository;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid FoodaAddressCreate addressCreate) {

        if (addressRepository.existByUniqueFields(addressCreate.getCoordinates().getLatitude(), addressCreate.getCoordinates().getLongitude())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaAddressHttpFailureMessages.ADDRESS_ALREADY_EXISTS);
        }

        if (!userClient.exists(addressCreate.getUser().getExternalUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaAddressHttpFailureMessages.USER_DOES_NOT_EXIST);
        }

        final FoodaAddress addressBeingCreated = addressMapper.fromCreateToEntity(addressCreate);
        addressRepository.save(addressBeingCreated);

        // ADDRESS CREATED ..
        return ResponseEntity.status(HttpStatus.CREATED).body(FoodaAddressHttpSuccessMessages.ADDRESS_ADDED);

    }

    @GetMapping("all")
    public ResponseEntity getAll() {
        final List<FoodaAddress> addresses = addressRepository.findAll();
        return !addresses.isEmpty()
                ? ResponseEntity.status(HttpStatus.FOUND).body(addresses)
                : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<FoodaAddress> foundAddress = addressRepository.findById(id);
        return foundAddress.isPresent()
                ? ResponseEntity.status(HttpStatus.FOUND).body(foundAddress)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("ext/user/{id}")
    public ResponseEntity getByExtUserId(@PathVariable Long externalUserId) {
        List<FoodaAddress> foundContact = addressRepository.findByExternalUserId(externalUserId);
        return foundContact.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaAddressHttpFailureMessages.ADDRESS_DOES_NOT_EXIST)
                : ResponseEntity.status(HttpStatus.FOUND).body(foundContact);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delById(@PathVariable Long id) {
        Optional<FoodaAddress> foundAddress = addressRepository.findById(id);
        if (!foundAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FoodaAddressHttpFailureMessages.ADDRESS_DOES_NOT_EXIST);

        FoodaAddress addressBeingDeleted = foundAddress.get();
        addressBeingDeleted.setIsActive(Boolean.FALSE);
        addressRepository.save(addressBeingDeleted);

        return addressRepository.existByIsActive(id, false)
                ? ResponseEntity.status(HttpStatus.FOUND).body(FoodaAddressHttpSuccessMessages.ADDRESS_DELETED)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(FoodaAddressHttpFailureMessages.ADDRESS_COULD_NOT_BE_DELETED);
    }


}
