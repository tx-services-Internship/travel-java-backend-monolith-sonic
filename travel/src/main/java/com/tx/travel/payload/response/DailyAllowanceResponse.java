package com.tx.travel.payload.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class DailyAllowanceResponse {

  private final UUID id;
  private final String region;
  private final double amount;

  public DailyAllowanceResponse(UUID id, String region, double amount) {
    this.region = region;
    this.amount = amount;
    this.id = id;
  }

  public DailyAllowanceResponse() {
    this.region = null;
    this.amount = 0;
    this.id = null;
  }

}
