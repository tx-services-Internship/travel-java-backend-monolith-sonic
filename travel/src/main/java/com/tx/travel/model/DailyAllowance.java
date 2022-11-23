package com.tx.travel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(
        name = "daily_allowance",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "region")
        })
public class DailyAllowance {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter
        private UUID id;

        @NotBlank
        @Size(max = 100)
        @Setter
        @Getter
        private String region;

        @NotBlank
        @Setter
        @Getter
        private double amount;

        public DailyAllowance() {}

        public DailyAllowance(String region, double amount) {
            this.region = region;
            this.amount = amount;
        }

        public DailyAllowance(UUID id, String region, double amount) {
                this.region = region;
                this.amount = amount;
                this.id = id;
        }

}
