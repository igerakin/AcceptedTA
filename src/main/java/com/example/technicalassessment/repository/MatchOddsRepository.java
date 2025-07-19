package com.example.technicalassessment.repository;

import com.example.technicalassessment.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
    
    List<MatchOdds> findByMatchId(Long matchId);
    
    List<MatchOdds> findBySpecifier(String specifier);
    
    List<MatchOdds> findByMatchIdAndSpecifier(Long matchId, String specifier);
    
    @Query("SELECT mo FROM MatchOdds mo WHERE mo.odd >= :minOdd AND mo.odd <= :maxOdd")
    List<MatchOdds> findByOddRange(@Param("minOdd") BigDecimal minOdd, @Param("maxOdd") BigDecimal maxOdd);
    
    @Query("SELECT mo FROM MatchOdds mo WHERE mo.matchId = :matchId ORDER BY mo.odd ASC")
    List<MatchOdds> findByMatchIdOrderByOddAsc(@Param("matchId") Long matchId);
    
    @Query("SELECT mo FROM MatchOdds mo WHERE mo.matchId = :matchId ORDER BY mo.odd DESC")
    List<MatchOdds> findByMatchIdOrderByOddDesc(@Param("matchId") Long matchId);
    
    @Query("SELECT DISTINCT mo.specifier FROM MatchOdds mo")
    List<String> findAllDistinctSpecifiers();
}