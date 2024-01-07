package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId; // PK
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL) // OneToOne (o2o) - 1 do 1 -> @OneToOne
    @JoinColumn(name = "detailId")
    private UserDetails details;
    @OneToMany(cascade = CascadeType.ALL) // OneToMany (o2m) - 1 do wielu -> @OneToMany
    @JoinColumn(name = "userId")
    private List<PhoneNumber> phoneNumber;
    @ManyToMany // ManyToMany (m2m) - wiele do wielu -> @ManyToMany
    @JoinTable(name = "user_address_pivot", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "address_id") })
    private List<Address> address;
}
