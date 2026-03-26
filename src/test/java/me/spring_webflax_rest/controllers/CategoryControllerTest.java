package me.spring_webflax_rest.controllers;

import me.spring_webflax_rest.domain.Category;
import me.spring_webflax_rest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

class CategoryControllerTest {

WebTestClient webTestClient;
CategoryRepository categoryRepository;
CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void list() {
        Category category1 = new Category();
        category1.setDescription("Cat1");
        category1.setId("1");
        Category category2 = new Category();
        category2.setDescription("Cat2");
        category2.setId("2");



        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(category1,category2));

        webTestClient.get()
                .uri("/api/vi/categories")
               .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        Category category1 = new Category();
        category1.setDescription("Cat1");
        category1.setId("1");

        BDDMockito.given(categoryRepository.findById("anyId"))
                .willReturn(Mono.just(category1));


        webTestClient.get()
                .uri("/api/v1/categories/someid")
                .exchange()
                .expectBody(Category.class);

    }

    @Test
    void createCategory(){
        Category category1 = new Category();
        category1.setDescription("Cat1");
        category1.setId("1");

        Category category2 = new Category();
        Mono<Category> catToSaveMono = Mono.just(category2);

        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(category1));

        webTestClient.post()
                .uri("/api/vi/categories")
                .body(catToSaveMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();


    }

}