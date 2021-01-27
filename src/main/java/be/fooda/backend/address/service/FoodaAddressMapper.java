package be.fooda.backend.address.service;


import be.fooda.backend.address.model.entity.FoodaAddress;
import be.fooda.backend.address.model.create.FoodaAddressCreate;
import be.fooda.backend.address.model.read.FoodaAddressExample;
import be.fooda.backend.address.model.update.FoodaAddressUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FoodaAddressMapper {

     FoodaAddress fromCreateToEntity( FoodaAddressCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     FoodaAddress fromUpdateToEntity(FoodaAddressUpdate from, @MappingTarget  FoodaAddress to);



     FoodaAddress fromExampleToEntity( FoodaAddressExample from);

     FoodaAddressCreate fromEntityToCreate( FoodaAddress from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     FoodaAddressUpdate fromEntityToUpdate( FoodaAddress from, @MappingTarget  FoodaAddressUpdate to);


     FoodaAddressExample fromEntityToExample( FoodaAddress from);

    FoodaAddressExample fromEntityToResponse( FoodaAddress from);

}
