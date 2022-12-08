package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="`User`")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name ="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(name="email",unique = true)
    private String email;
    private String password;
    private String fullName;
    private Role role;

    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.PERSIST,orphanRemoval = true)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private List<Device> devices;


    public User(String email, String password,String fullName , Role role){
        this.email = email;
        this.password= password;
        this.fullName = fullName;
        this.role = role;
    }

}
