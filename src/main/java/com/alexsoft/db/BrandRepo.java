package com.alexsoft.db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexsoft.entyties.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {


}
