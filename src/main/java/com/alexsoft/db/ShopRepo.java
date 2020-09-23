package com.alexsoft.db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexsoft.entyties.categories.shops.Shop;

public interface ShopRepo extends JpaRepository<Shop, Long> {


}
