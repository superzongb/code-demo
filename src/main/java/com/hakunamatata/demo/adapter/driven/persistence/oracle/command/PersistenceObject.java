package com.hakunamatata.demo.adapter.driven.persistence.oracle.command;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
public abstract class PersistenceObject<ID extends Serializable>{
    @Id
    protected ID id;

    @Column(name = "create_at")
    protected String createAt;

    @Column(name = "update_at")
    protected String updateAt;
}
