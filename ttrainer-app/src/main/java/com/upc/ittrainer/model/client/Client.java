package com.upc.ittrainer.model.client;

import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.progression.Progression;
import com.upc.ittrainer.model.schedule.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "client")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Client implements ModelEntity<Integer>, Comparable<Client> {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private Integer phone;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column
    private Integer height;

    @Column(name = "init_date")
    @Temporal(TemporalType.DATE)
    private Date initDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "calendar_notes")
    private String calendarNotes;
    @Column(name = "nutritional_notes")
    private String nutritionalNotes;
    @Column(name = "training_notes")
    private String trainingNotes;

    @ManyToOne
    @JoinColumn(name = "app_user_name")
    @ToString.Exclude
    private AppUser appUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "client")
    private Set<Progression> progressions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "client")
    private Set<Event> events;

    @Override
    public int compareTo(Client o) {
        return this.name.compareTo(o.name);
    }

    @Transient
    @Override
    public Integer getID() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Progression> getSortedProgressions() {
        return progressions.stream().sorted().collect(Collectors.toList());
    }
}
