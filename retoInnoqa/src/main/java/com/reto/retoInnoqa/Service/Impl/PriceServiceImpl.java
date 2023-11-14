package com.reto.retoInnoqa.Service.Impl;

import com.reto.retoInnoqa.Entity.Prices;
import com.reto.retoInnoqa.Exception.PriceListException;
import com.reto.retoInnoqa.Exception.PriceNotFoundException;
import com.reto.retoInnoqa.Exception.PriceSaveExcepcion;
import com.reto.retoInnoqa.Repository.PriceRepository;
import com.reto.retoInnoqa.Service.PriceService;
import com.reto.retoInnoqa.Util.Validation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PriceRepository priceRepository;

    @Override
    public Prices savePrices(Prices prices){
        Validation.validationNull(prices);
        try {
            return priceRepository.save(prices);
        }catch (DataAccessException e){
            logger.error("Error connection H2" + e.getMessage());
            throw new PriceSaveExcepcion("Error saving price to the database");
        }
    }

    @Override
    public List<Prices> listPrices() {
        List<Prices> prices = priceRepository.findAll();
        if (prices.isEmpty()) {
            throw new PriceListException("Prices not found");
        }
        return prices;
    }

    @Override
    public Prices modifyPrices(Long id, Prices prices) {
        return priceRepository.findById(id).map(existingPrice -> {
            existingPrice.setBrandId(prices.getBrandId());
            existingPrice.setStartDate(prices.getStartDate());
            existingPrice.setEndDate(prices.getEndDate());
            existingPrice.setPriceList(prices.getPriceList());
            existingPrice.setProductId(prices.getProductId());
            existingPrice.setPriority(prices.getPriority());
            existingPrice.setPrice(prices.getPrice());
            existingPrice.setCurr(prices.getCurr());
            return priceRepository.save(existingPrice);
        }).orElseThrow(()-> new PriceNotFoundException("Price not found with id: " + id));
    }

    @Override
    public List<Prices> priceParameters(LocalDateTime date, Long productId, Long brandId) {
        List<Prices> prices = priceRepository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductId(brandId, date, date, productId);

        if(prices.isEmpty()){
            throw new PriceNotFoundException( "No prices found for the provided parameters");
        }
        Prices selectedPrice = prices.stream()
                .max(Comparator.comparingInt(Prices::getPriority))
                .orElseThrow(() -> new IllegalStateException("No price with max priority found"));

        return Collections.singletonList(selectedPrice);

    }

    @Override
    public void deletePrices(Long id) {
        if (!priceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found");
        }
        priceRepository.deleteById(id);
    }

}
