package com.tx.travel.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CostCentreResponse {

  private final String id;
  private final String name;
}
