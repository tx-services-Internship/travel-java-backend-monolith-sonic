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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
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

    public DailyAllowanceResponse findByRegion(final String region) {

        Optional<DailyAllowance> dailyAllowance = dailyAllowanceRepository.findByRegion(region);

        if(dailyAllowance.isPresent()){
            return dailyAllowanceMapper.mapDailyAllowanceToDailyAllowanceResponse(dailyAllowance.get());
        }else {
            throw new DailyAllowanceNotFoundException(region);
        }
    }

    public void addDailyAllowance(final DailyAllowanceResponse dailyAllowanceResponse) throws RegionAlreadyExistsException{

        DailyAllowance da = dailyAllowanceMapper.mapDailyAllowanceResponseToDailyAllowance(dailyAllowanceResponse);
        dailyAllowanceRepository.save(da);
    }

    public DailyAllowanceResponse findById(Long id) {

        Optional<DailyAllowance> dailyAllowance = dailyAllowanceRepository.findById(id);

        if(dailyAllowance.isPresent()){
            return dailyAllowanceMapper.mapDailyAllowanceToDailyAllowanceResponse(dailyAllowance.get());
        }else {
            throw new DailyAllowanceNotFoundException(id);
        }
    }

    public void deleteDailyAllowance(final Long id) throws DailyAllowanceNotFoundException {

        try{
           dailyAllowanceRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new DailyAllowanceNotFoundException(id);
        }
    }

    public DailyAllowanceResponse updateDailyAllowance(final DailyAllowanceResponse newDailyAllowanceInfo, final Long id) throws RegionAlreadyExistsException {
        DailyAllowance da = dailyAllowanceMapper.mapDailyAllowanceResponseToDailyAllowance(newDailyAllowanceInfo);

        dailyAllowanceRepository.save(da);

        return dailyAllowanceMapper.mapDailyAllowanceToDailyAllowanceResponse(da);
    }
}
