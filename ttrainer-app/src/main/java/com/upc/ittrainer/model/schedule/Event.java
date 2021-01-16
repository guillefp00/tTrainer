package com.upc.ittrainer.model.schedule;


import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.model.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event implements ModelEntity<Integer> {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime init;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;

    @Override
    public Integer getID() {
        return id;
    }

    public Integer getId() {
        return id;
    }
}
