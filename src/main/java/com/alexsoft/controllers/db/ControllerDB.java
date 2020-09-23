package com.alexsoft.controllers.db;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoft.controllers.dto.BaseEntityDto;
import com.alexsoft.db.BaseEntityRepository;
import com.alexsoft.db.BaseShopRepository;
import com.alexsoft.db.ShopRepo;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.entyties.categories.shops.Shop;
import com.alexsoft.enums.Shops;
import com.alexsoft.services.AbstrWebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController()
@RequestMapping("/db")
public class ControllerDB extends AbstrWebClient {

    @Autowired
    private BaseEntityRepository baseEntityRepository;
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private BaseShopRepository baseShopRepository;

    @GetMapping("/devorceModel")
    public Integer devorec(@RequestParam String category) {
        log.info("called devorceModel");
        int count = 0;
        List<BaseEntity> byCategory = baseEntityRepository.findAllByCategory(category);
        for (BaseEntity item : byCategory) {
            String modelName = item.getModel();
            String[] split = modelName.split(" ");

            try {
                String shortModel3 = split[1] + " " + split[2];
                item.setModelShort3(shortModel3);
                baseEntityRepository.save(item);
            } catch (Exception e) {
                System.out.println("Can't save item id: " + item.getId());
                String shortModel2 = split[1];
                item.setModelShort2(shortModel2);
                baseEntityRepository.save(item);
            }

        }
        return byCategory.size();
    }

    @GetMapping("/getPhotos")
    public Integer getPhotos(@RequestParam String category) throws IOException {
        //        String url = "https://market.yandex.by/catalog--noutbuki/54544/list?text=acer%20aspire%205&local-offers-first=0"
        log.info("called getPhotos");
        List<BaseEntity> byCategory = baseEntityRepository.findAllByCategory(category);
        for (BaseEntity item : byCategory) {
            String producer = item.getProducer();
            String model3 = item.getModelShort3();

            String model = model3 != null ? model3 : item.getModelShort2();
            String fullName = producer + model;
            String url = "https://market.yandex.by/catalog--noutbuki/54544/list?local-offers-first=0&text=" + fullName;
            String content = getContent(url);
            Document document = Jsoup.parse(content);
//            document.getEle

        }

        return byCategory.size();
    }

    @GetMapping("/setShopToTable")
    public Integer setShopToTable() {
        log.info("called setShopToTable");
        int count = 0;
        List<Shop> shops = new ArrayList<>();
        for (Shops shop : Shops.values()) {
            long id = shop.getId();
            String name = shop.name();
            Shop shopEntity = new Shop();
            shopEntity.setId(id);
            shopEntity.setName(name);
            shops.add(shopEntity);
            count++;
        }
        shopRepo.saveAll(shops);
        return count;
    }


//    @GetMapping("/getById")
//    public BaseShop getById(@RequestParam Long id) {
//        log.info("getById");
//        BaseShop one = (BaseShop) baseShopRepository.getOne(id);
//        return one;
//    }

    @Setter
    @Getter
    private class NotebookShopDto {

        String source;

        NotebookShopDto getDto(BaseShop shop) {
            NotebookShopDto dto = new NotebookShopDto();
            dto.setSource(shop.getSource());
            return dto;
        }
    }

    @GetMapping("/getByCategory")
    public List<BaseEntityDto> getByCategory(@RequestParam String category, @RequestParam int page,
                                             @RequestParam int size) {
        log.info("getByCategory: {}", category);
        Pageable pageable = PageRequest.of(page, size);

//        Pageable sortedByPriceDescNameAsc =
//                PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

        List<BaseEntity> entities = baseEntityRepository.findAllByCategory(category, pageable);

        for (BaseEntity entity : entities) {
            List<BaseShop> list = entity.getShops();
            list.sort(Comparator.comparing(BaseShop::getPrice));
        }

        BaseEntityDto baseEntityDto = new BaseEntityDto();
        List<BaseEntityDto> baseEntityDtos = baseEntityDto.getBaseEntityDto(entities);
        return baseEntityDtos;
    }



    @GetMapping("/getShopsByCategory")
    public List<BaseShop> getShopsByCategory(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        log.info("getByCategory: {}", category);
        Pageable pageable = PageRequest.of(page, size);
        List<BaseShop> entities = baseShopRepository.findAllByCategory(category, pageable);
        return entities;
    }


    @GetMapping("/getByCategoryAndProducer")
    public List<BaseEntity> getByCategoryAndProducer(@RequestParam String category, @RequestParam String producer,
                                                     @RequestParam int page, @RequestParam int size) {
        log.info("getByCategoryAndProducer: {} - {}", category, producer);
        Pageable pageable = PageRequest.of(page, size);
        List<BaseEntity> entities = baseEntityRepository.findAllByCategoryAndProducer(pageable, category, producer);
        return entities;
    }

    @GetMapping("/getById")
    public BaseEntity getById(@RequestParam Long id) {
        log.info("getById");
        Optional byId = baseEntityRepository.findById(id);
        return (BaseEntity) byId.get();
    }

    @GetMapping("/searchByModel")
    public List<BaseEntity> searchByModel(@RequestParam String model, @RequestParam int page, @RequestParam int size) {
        log.info("searchByModel");
        Pageable pageable = PageRequest.of(page, size);
        List<BaseEntity> entities = baseEntityRepository.findAllByModelContaining(pageable, model);
        return entities;
    }
}
