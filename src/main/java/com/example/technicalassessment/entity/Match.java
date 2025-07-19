package com.example.technicalassessment.entity;

import com.example.technicalassessment.enums.Sport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String description;
    
    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;
    
    @Column(name = "match_time", nullable = false)
    private LocalTime matchTime;
    
    @Column(name = "team_a", nullable = false)
    private String teamA;
    
    @Column(name = "team_b", nullable = false)
    private String teamB;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Sport sport;
}