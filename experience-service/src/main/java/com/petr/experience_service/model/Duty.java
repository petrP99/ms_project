package com.petr.experience_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "duties")
public class Duty {

    @Id
    @Column(name = "experience_id")
    private Long id;

    @Column(name = "duty_name")
    private String dutyName;
}
