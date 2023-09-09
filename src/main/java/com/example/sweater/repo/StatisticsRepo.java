package com.example.sweater.repo;

import com.example.sweater.domain.Statistics;
import org.springframework.data.repository.CrudRepository;

public interface StatisticsRepo  extends CrudRepository<Statistics, Long> {
}
