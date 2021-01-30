package be.fooda.backend.address.service;


import be.fooda.backend.address.model.entity.FoodaAddress;
import be.fooda.backend.address.model.create.FoodaAddressCreate;
import be.fooda.backend.address.model.read.FoodaAddressExample;
import be.fooda.backend.address.model.update.FoodaAddressUpdate;
import org.mapstruct.*;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = ALWAYS,
        collectionMappingStrategy = ADDER_PREFERRED
)
public interface FoodaAddressMapper {

    FoodaAddress fromCreateToEntity(FoodaAddressCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaAddress fromUpdateToEntity(FoodaAddressUpdate from, @MappingTarget FoodaAddress to);

    FoodaAddress fromExampleToEntity(FoodaAddressExample from);

    FoodaAddressCreate fromEntityToCreate(FoodaAddress from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FoodaAddressUpdate fromEntityToUpdate(FoodaAddress from, @MappingTarget FoodaAddressUpdate to);

    FoodaAddressExample fromEntityToExample(FoodaAddress from);

    FoodaAddressExample fromEntityToResponse(FoodaAddress from);

}
