package com.reto.retoInnoqa.Repository;

import com.reto.retoInnoqa.Entity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Prices, Long> {



    List<Prices> findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductId(
            Long brandId, LocalDateTime dateTime, LocalDateTime date, Long productId);
}
