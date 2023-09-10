package com.example.sweater.repo;

import com.example.sweater.domain.Statistics;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StatisticsRepo  extends CrudRepository<Statistics, Long> {
    List<Statistics> findByDate(Date date);
}
