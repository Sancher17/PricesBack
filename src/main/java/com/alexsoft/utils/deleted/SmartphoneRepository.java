package com.alexsoft.utils.deleted;//package com.prices.db;
//
//import com.prices.entyties.categories.Smartphone;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
//
//    Smartphone getByModel(String model);
//
//    @Query("select s from Smartphone s where s.model like %?1%")
//    List<Smartphone> findAllByModel(String model);
//}
