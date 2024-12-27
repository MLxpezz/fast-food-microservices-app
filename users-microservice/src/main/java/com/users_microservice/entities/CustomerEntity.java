package com.users_microservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"phones", "addresses"}, callSuper = false)
@ToString(exclude = {"phones", "addresses"})
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @OneToMany(
            mappedBy = "customer",
            cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private Set<PhoneEntity> phones = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<AddressEntity> addresses =  new ArrayList<>();
}
