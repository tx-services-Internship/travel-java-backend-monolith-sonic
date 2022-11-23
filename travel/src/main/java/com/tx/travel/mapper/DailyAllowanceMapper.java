package com.tx.travel.mapper;

import com.tx.travel.model.DailyAllowance;
import com.tx.travel.payload.response.DailyAllowanceResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class DailyAllowanceMapper {
    public DailyAllowance mapDailyAllowanceResponseToDailyAllowance(DailyAllowanceResponse dailyAllowanceResponse){

        return new DailyAllowance().builder()
                .id(dailyAllowanceResponse.getId())
                .amount(dailyAllowanceResponse.getAmount())
                .region(dailyAllowanceResponse.getRegion())
                .build();
    }



    public DailyAllowanceResponse mapDailyAllowanceToDailyAllowanceResponse(DailyAllowance dailyAllowance){

        return new DailyAllowanceResponse().builder()
                .id(dailyAllowance.getId())
                .amount(dailyAllowance.getAmount())
                .region(dailyAllowance.getRegion())
                .build();
    }

    public List<DailyAllowanceResponse> mapStudentListToStudentResponseList(List<DailyAllowance> dailyAllowances){

        List<DailyAllowanceResponse> dailyAllowanceResponses = new CopyOnWriteArrayList<>();

        for (DailyAllowance da : dailyAllowances) {
            dailyAllowanceResponses.add(mapDailyAllowanceToDailyAllowanceResponse(da));
        }

        return dailyAllowanceResponses;
    }
}
