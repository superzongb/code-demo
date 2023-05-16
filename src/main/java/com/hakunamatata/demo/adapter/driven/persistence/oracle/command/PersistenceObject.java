package com.hakunamatata.demo.adapter.driven.persistence.oracle.command;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class PersistenceObject<ID extends Serializable>{
    @Id
    protected ID id;

    @Column(name = "create_at")
    protected Instant createAt;

    @Column(name = "update_at")
    protected Instant updateAt;
}
