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
@Table(name = "STYLIST")
public class Stylist {
    @Id
    @GeneratedValue(generator = "stylist_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "stylist_seq", allocationSize = 1, sequenceName = "STYLIST_SEQ")
    private Long stylistId;
    private String stylistName;
    private String stylistMessage;
    private String stylistThumbUrl;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;
}
