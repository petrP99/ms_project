package com.petr.experience_service.model;

import com.petr.experience_service.util.YearMonthDateAttributeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth periodFrom;

    @Convert(converter = YearMonthDateAttributeConverter.class)
    @Column(name = "period_to")
    private YearMonth periodTo;

    @Column(name = "present_time")
    private Boolean presentTime;

    @Column(name = "industry_id")
    private Long industryId;
    private String position;
    private String achievements;
    private String link;
}
