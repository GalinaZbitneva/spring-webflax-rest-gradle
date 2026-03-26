package me.spring_webflax_rest.controllers;

import me.spring_webflax_rest.domain.Category;
import me.spring_webflax_rest.domain.Vendor;
import me.spring_webflax_rest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @BeforeEach
    void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void list() {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId("1");
        Vendor vendor2 = new Vendor();
        vendor2.setId("2");

        //when
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(vendor1,vendor2));

        //then
        webTestClient.get()
                .uri("/api/vi/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);


    }

    @Test
    void getVendorById() {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId("1");

        //when
        BDDMockito.given(vendorRepository.findById("someId"))
                .willReturn(Mono.just(vendor1));
        //then
        webTestClient.get()
                .uri("/api/vi/vendors/anyId")
                .exchange()
                .expectBodyList(Vendor.class);



    }
}