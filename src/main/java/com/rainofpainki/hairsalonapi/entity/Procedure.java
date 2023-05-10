package com.rainofpainki.hairsalonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROCEDURE")
public class Procedure {
    @Id
    @GeneratedValue(generator = "procedure_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "procedure_seq", allocationSize = 1, sequenceName = "PROCEDURE_SEQ")
    private Long procedureId;
    private String procedureName;
    private Integer procedurePrice;
    private Integer procedureHours;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;
}
