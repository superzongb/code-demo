package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_trips")
public class TripPo extends PersistenceObject<Long> {
    @Column(name = "flight_no")
    private String flightNo;

    @Column(name = "date")
    private String date;

    @Column(name = "passenger_id")
    private String passengerId;

    @Column(name = "gender")
    private int gender;

    @Column(name = "name")
    private String name;
}
