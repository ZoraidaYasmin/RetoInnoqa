package com.reto.retoInnoqa.Controller;

import com.reto.retoInnoqa.Entity.Prices;
import com.reto.retoInnoqa.Service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/savePrice")
    public ResponseEntity<Prices> savePrice (@RequestBody Prices prices){
        return new ResponseEntity<>(priceService.savePrices(prices), HttpStatus.CREATED);
    }

    @GetMapping("listPrice")
    public ResponseEntity<List<Prices>> listPrice (){
        return new ResponseEntity<>(priceService.listPrices(), HttpStatus.OK);
    }

    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<Prices> updatePrice (@PathVariable("id") Long id, @RequestBody  Prices prices){
        return new ResponseEntity<>(priceService.modifyPrices(id, prices), HttpStatus.OK);
    }

    @DeleteMapping("/deletePrices/{id}")
    public ResponseEntity<Boolean> deletePrice (@PathVariable("id") Long id){
        try {
            boolean response = priceService.deletePrices(id);
            HttpStatus status = response ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(status);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @GetMapping("/priceParameters/")
    public ResponseEntity<List<Prices>> priceParameters (@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime date,
                                                         @RequestParam("productId") Long productId,
                                                         @RequestParam("brandId") Long brandId ){

        return new ResponseEntity<>(priceService.priceParameters(date, productId, brandId), HttpStatus.OK);

    }
}
