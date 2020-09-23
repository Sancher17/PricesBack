package com.alexsoft.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoft.db.BrandRepo;
import com.alexsoft.entyties.Brand;
import com.alexsoft.services.AbstrWebClient;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController()
@RequestMapping("/db/brand")
public class ControllerBrand extends AbstrWebClient {

    @Autowired
    private BrandRepo brandRepo;

    @GetMapping("/getBrands")
    public List<Brand> getBrands() throws IOException {
        log.info("called getBrands");
        List<Brand> all = brandRepo.findAll();
        log.info("Data: {}", all);
        return all;
    }
}
