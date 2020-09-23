package com.alexsoft.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alexsoft.entyties.categories.shops.BaseShop;

import java.util.List;

public interface BaseShopRepository<T extends BaseShop, id>  extends JpaRepository<BaseShop, Long> {

    BaseShop findByCategoryAndProductId (String category, String productId);

    List<BaseShop> findAllByCategoryAndModelId (String category, Long modelId);

    BaseShop findByCategoryAndSourceAndModelId(String category, String source, Long id);

    List<BaseShop> findAllByCategory(String category, Pageable pageable);
}
