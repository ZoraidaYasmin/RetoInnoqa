package com.reto.retoInnoqa.Util;

import com.reto.retoInnoqa.Entity.Prices;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Validation {

    public static void validationNull(Prices prices){
        if (prices.getBrandId() ==null || prices.getProductId() == null ||
                prices.getPriceList() ==null || prices.getStartDate() == null ||
                prices.getEndDate() == null || prices.getPriority() == null ||
                prices.getPrice() == null || prices.getCurr() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required fields.");
        }
    }
}
