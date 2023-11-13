package com.reto.retoInnoqa.Service;


import com.reto.retoInnoqa.Entity.Prices;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    Prices savePrices (Prices prices);

    List<Prices> listPrices ();

    Prices modifyPrices (Long id, Prices prices);

    List<Prices> priceParameters(LocalDateTime date, Long productId, Long brandId);

    boolean deletePrices (Long id);
}
