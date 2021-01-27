package be.fooda.backend.address.dao;


import be.fooda.backend.address.model.entity.FoodaAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodaAddressRepository extends JpaRepository<FoodaAddress, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM FoodaAddress a WHERE a.id = :id AND a.isActive = :isActive")
    boolean existByIsActive(@Param("id") Long id,
                            @Param("isActive") boolean isActive);

    @Query("SELECT c FROM FoodaAddress c WHERE c.user.externalUserId = :externalUserId ")
    List<FoodaAddress> findByExternalUserId(long externalUserId);


    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM FoodaAddress a " +
            "WHERE a.coordinates.latitude = :latitude AND a.coordinates.longitude = :longitude")
    boolean existByUniqueFields(@Param("latitude") Double latitude,
                                @Param("longitude") Double longitude);
}
