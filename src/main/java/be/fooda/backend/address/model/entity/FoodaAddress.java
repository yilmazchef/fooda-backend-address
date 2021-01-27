package be.fooda.backend.address.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Indexed
public class FoodaAddress {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String door;

    @Field
    private String house;

    @Field
    @NotNull
    private String street;

    private Boolean isActive = Boolean.TRUE;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:SS")
    @CreationTimestamp
    private LocalDateTime registryTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:SS")
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Field
    private String title;

    private Boolean isCurrent;

    @Embedded
    private FoodaAddressCoordinates coordinates;

    @EqualsAndHashCode.Include
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    private FoodaAddressUser user;

    public void setUser(FoodaAddressUser user) {
        user.setAddress(this);
        this.user = user;
    }

    @Field
    private String municipality;

    @Field
    private String postcode;

    @Field
    private String city;

    @Field
    private String region;

    @Field
    private String country;

    @Field
    private String countryCode;
    
}