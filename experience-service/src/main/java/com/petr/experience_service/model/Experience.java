package com.petr.experience_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    @Column(name = "period_from")
    private YearMonth periodFrom;

    @Column(name = "period_to")
    private YearMonth periodTo;

    @Column(name = "present_time")
    private Boolean presentTime;

    private String company;
    private String position;
    private String achievements;
    private String link;

}
