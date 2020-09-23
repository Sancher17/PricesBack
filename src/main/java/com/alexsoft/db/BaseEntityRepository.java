package com.alexsoft.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexsoft.entyties.categories.BaseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<BaseEntity, Long> {

    @Query("select e from #{#entityName}  as e where e.producer = ?1 ")
    List<T> findAllRows(String producer);

    List<T> findByProducer(String producer);

    List<BaseEntity> findAllByCategory(String category);

    List<BaseEntity> findAllByModelContaining(Pageable firstTen, String model);

    List<BaseEntity> findAllByModelContaining(String model);

    List<BaseEntity> findAllByCategoryAndSource(String category, String source);

    T findByCategoryAndId(String category, Long id);

    T findByCategoryAndModel(String category, String model);

    List<BaseEntity> findAllByCategory(String category, Pageable firstTen);

    List<BaseEntity> findAllByCategoryAndProducer(Pageable firstTen, String category, String producer);

    List<BaseEntity> findAllByCategoryAndProducer(String category, String producer);

    @Override
    Optional<BaseEntity> findById(Long id);
}
