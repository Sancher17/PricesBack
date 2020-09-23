package com.alexsoft.db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexsoft.entyties.PriceHistory;

public interface PriceHistoryRepo extends JpaRepository<PriceHistory, Long> {

}
