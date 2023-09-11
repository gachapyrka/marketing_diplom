package com.example.sweater.service;

import com.example.sweater.repo.StatisticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    @Autowired
    private StatisticsRepo statisticsRepo;
}
