package com.finflow.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.finflow.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq_generator"
    )
    @SequenceGenerator(
            name = "user_seq_generator",
            sequenceName = "USER_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable= false,length = 50)
    private String firstName;
    @Column(nullable= false,length = 50)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private String password;

}
