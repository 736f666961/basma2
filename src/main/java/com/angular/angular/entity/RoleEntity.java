package com.angular.angular.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "RoleEntity")
@Table(name = "roles")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -5299077814427394790L;

    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @Column(columnDefinition = "CHAR(16)")
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    @Column(nullable = false)
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}