package com.security.auth_2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "username_update_time_stamp")
    private LocalDateTime usernameUpdateTimeStamp;

    @PreUpdate
    public void updateTimeStamp(){
        if(username != null){
            usernameUpdateTimeStamp = LocalDateTime.now();
        }else{
            usernameUpdateTimeStamp = null;
        }
    }

}
