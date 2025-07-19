package com.example.technicalassessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "match_odds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchOdds {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "match_id", nullable = false)
    private Long matchId;
    
    @Column(nullable = false)
    private String specifier;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal odd;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    private Match match;
}