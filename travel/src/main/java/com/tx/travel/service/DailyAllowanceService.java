package com.tx.travel.service;

import com.tx.travel.mapper.DailyAllowanceMapper;
import com.tx.travel.model.DailyAllowance;
import com.tx.travel.model.User;
import com.tx.travel.payload.response.DailyAllowanceResponse;
import com.tx.travel.repository.DailyAllowanceRepository;
import com.tx.travel.repository.RoleRepository;
import com.tx.travel.repository.UserRepository;
import com.tx.travel.service.exception.DailyAllowanceNotFoundException;
import com.tx.travel.service.exception.RegionAlreadyExistsException;
import com.tx.travel.service.exception.UsernameAlreadyExistsException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class DailyAllowanceService {

    private final DailyAllowanceMapper dailyAllowanceMapper;
    private final DailyAllowanceRepository dailyAllowanceRepository;
    private final List<DailyAllowance> dailyAllowances = new CopyOnWriteArrayList<>();

    public static final String ERROR_REGION_NOT_FOUND = "Error: Region is not found.";

    public DailyAllowanceService(final DailyAllowanceRepository dailyAllowanceRepository, final DailyAllowanceMapper dailyAllowanceMapper) {
        this.dailyAllowanceMapper = dailyAllowanceMapper;
        this.dailyAllowanceRepository = dailyAllowanceRepository;
    }

    public List<DailyAllowanceResponse> getAllDailyAllowances() {
        List<DailyAllowanceResponse> dailyAllowanceResponses = new CopyOnWriteArrayList<>();

        for (DailyAllowance s : dailyAllowanceRepository.findAll()) {
            dailyAllowanceResponses.add(dailyAllowanceMapper.mapDailyAllowanceToDailyAllowanceResponse(s));
        }

        return dailyAllowanceResponses;
    }

    public Long findByRegion(@NotNull final String region)
            throws RegionAlreadyExistsException {
        final Optional<DailyAllowance> dailyAllowanceByRegion = dailyAllowanceRepository.findByRegion(region);
        if (dailyAllowanceByRegion.isPresent()) {
            throw new RegionAlreadyExistsException(region);
        }
        return dailyAllowanceByRegion.
    }

    public DailyAllowance addDailyAllowance(final DailyAllowance dailyAllowance) {
        return dailyAllowanceRepository.save(dailyAllowance);
    }

    public DailyAllowanceResponse findById(Long id) {

        Optional<DailyAllowance> dailyAllowance = dailyAllowanceRepository.findById(id);

        if(dailyAllowance.isPresent()){
            return dailyAllowanceMapper.mapDailyAllowanceToDailyAllowanceResponse(dailyAllowance.get());
        }else {
            throw new DailyAllowanceNotFoundException(id);
        }
    }

    public DailyAllowanceResponse updateDailyAllowance() {
    }

    public void deleteDailyAllowance(Long id) {
    }
}
