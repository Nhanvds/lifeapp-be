package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.WeightQueryRequest;
import com.mad.lifeapp.dto.response.DailyWeightStats;
import com.mad.lifeapp.entity.UserHealthProfile;
import com.mad.lifeapp.repository.DailyWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightStatsService {
    @Autowired
    private DailyWeightRepository dailyWeightRepository;

    public List<DailyWeightStats> getWeightStatsByDateRange(WeightQueryRequest request) {
        // Lấy danh sách bản ghi từ repository
        List<UserHealthProfile> profiles = dailyWeightRepository.findByDateRange(
                request.getStartDay(),
                request.getEndDay(),
                request.getMonth(),
                request.getYear()
        );

        // Chuyển đổi sang DTO DailyWeightStats
        return profiles.stream()
                .map(profile -> new DailyWeightStats(
                        profile.getId(),
                        profile.getCreatedAt(),
                        profile.getWeight()
                ))
                .collect(Collectors.toList());
    }
}
