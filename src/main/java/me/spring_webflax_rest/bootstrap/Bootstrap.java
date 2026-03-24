package me.spring_webflax_rest.bootstrap;

import me.spring_webflax_rest.domain.Category;
import me.spring_webflax_rest.domain.Vendor;
import me.spring_webflax_rest.repositories.CategoryRepository;
import me.spring_webflax_rest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.deleteAll().block();
        vendorRepository.deleteAll().block();
        //пришлось очистить репозиторий, иначе при каждом запуске данные записываются по новой
        loadCategories();

        loadVendors();

    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Joe");
        vendor1.setLastName("Buck");
        vendorRepository.save(vendor1).block();

        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Micheal");
        vendor2.setLastName("Weston");
        vendorRepository.save(vendor2).block();

        Vendor vendor3 = new Vendor();
        vendor3.setFirstName("Jessie");
        vendor3.setLastName("Waters");
        vendorRepository.save(vendor3).block();

        Vendor vendor4 = new Vendor();
        vendor4.setFirstName("Mira");
        vendor4.setLastName("Sorrengeil");
        vendorRepository.save(vendor4).block();

        Vendor vendor5 = new Vendor();
        vendor5.setFirstName("Drake");
        vendor5.setLastName("Cordella");
        vendorRepository.save(vendor5).block();

        System.out.println("Vendors loaded " + vendorRepository.count().block());
    }

    private void loadCategories() {
        Category nuts = new Category();
        nuts.setDescription("Nuts");
        categoryRepository.save(nuts).block();

        Category breads = new Category();
        breads.setDescription("Breads");
        categoryRepository.save(breads).block();

        Category meats = new Category();
        meats.setDescription("Meats");
        categoryRepository.save(meats).block();

        Category fruits = new Category();
        fruits.setDescription("Fruits");
        categoryRepository.save(fruits).block();

        Category eggs = new Category();
        eggs.setDescription("Eggs");
        categoryRepository.save(eggs).block();

        System.out.println("Categories loaded " + categoryRepository.count().block());
    }



}
