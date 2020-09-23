package com.alexsoft.utils.deleted;//package com.prices.db;
//
//import com.prices.entyties.categories.BaseEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import com.prices.entyties.categories.Notebook;
//
//import java.util.List;
//
//public interface NotebookRepository<T extends BaseEntity> extends BaseEntityRepository<Notebook, Long> {
////public interface NotebookRepository extends JpaRepository<Notebook, Long> {
//
////    @Modifying
////    @Query("update Notebook n set n.price = :price where n.model = :model")
////    void updatePrice(@Param("price")Double price, @Param("model") String model);
//
//    Notebook getByModel(String model);
//
////    @Query("select n from Notebook n where n.model like %?1%")
//    List<Notebook> findAllByModel(String model);
//
//    List<Notebook> findAllByIsFillDetailDataIsFalseAndSourceContaining(String source);
//
//
//}
