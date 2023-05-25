package com.hakunamatata.demo.adapter.driven.persistence.oracle.command;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
public abstract class PersistenceObject<ID extends Serializable> {
    @Id
    protected ID id;

    @Column(name = "create_at")
    protected String createAt;

    @Column(name = "update_at")
    protected String updateAt;
}
