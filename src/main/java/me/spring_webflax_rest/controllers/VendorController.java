package me.spring_webflax_rest.controllers;

import me.spring_webflax_rest.domain.Vendor;
import me.spring_webflax_rest.repositories.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {

    private final VendorRepository vendorRepository;


    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("/api/vi/vendors")
    Flux<Vendor> list(){
        return vendorRepository.findAll();
    }

    @GetMapping("/api/vi/vendors/{id}")
    Mono<Vendor> getVendorById(@PathVariable String id){
        return vendorRepository.findById(id);
    }


}
