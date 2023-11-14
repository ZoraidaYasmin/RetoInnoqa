package com.reto.retoInnoqa.Controller;

import com.reto.retoInnoqa.Entity.Prices;
import com.reto.retoInnoqa.Exception.PriceListException;
import com.reto.retoInnoqa.Exception.PriceNotFoundException;
import com.reto.retoInnoqa.Service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.reto.retoInnoqa.Exception.PriceSaveExcepcion;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/savePrice")
    public ResponseEntity<Prices> savePrice (@RequestBody Prices prices){
        try {
            Prices savedPrice = priceService.savePrices(prices);
            return new ResponseEntity<>(savedPrice, HttpStatus.CREATED);
        } catch (PriceSaveExcepcion e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("listPrice")
    public ResponseEntity<List<Prices>> listPrice (){
        try {
            List<Prices> prices = priceService.listPrices();
            return new ResponseEntity<>(prices, HttpStatus.OK);
        } catch (PriceListException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<Prices> updatePrice (@PathVariable("id") Long id, @RequestBody  Prices prices){
        try {
            Prices updatedPrice = priceService.modifyPrices(id, prices);
            return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
        } catch (PriceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePrices/{id}")
    public ResponseEntity<String> deletePrice (@PathVariable("id") Long id){
        try {
            priceService.deletePrices(id);
            return new ResponseEntity<>("Price deleted successfully", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @GetMapping("/priceParameters/")
    public ResponseEntity<List<Prices>> priceParameters (@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime date,
                                                         @RequestParam("productId") Long productId,
                                                         @RequestParam("brandId") Long brandId ){

        try {
            List<Prices> result = priceService.priceParameters(date, productId, brandId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (PriceNotFoundException e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
