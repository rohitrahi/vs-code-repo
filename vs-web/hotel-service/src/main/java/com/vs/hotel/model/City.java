package com.vs.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @Column(name = "CITY_ID")
    Integer cityId;
    @Column(name = "CITY_NAME")
    String cityName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID")
    Country country;
}