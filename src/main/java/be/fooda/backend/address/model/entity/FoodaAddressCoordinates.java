package be.fooda.backend.address.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FoodaAddressCoordinates {

    @Basic
    @Latitude
    private Double latitude;

    @Basic
    @Longitude
    private Double longitude;


}