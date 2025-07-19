package com.example.technicalassessment.repository;

import com.example.technicalassessment.entity.Match;
import com.example.technicalassessment.enums.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findBySport(Sport sport);
    
    List<Match> findByMatchDate(LocalDate matchDate);
    
    @Query("SELECT m FROM Match m WHERE m.teamA = :team OR m.teamB = :team")
    List<Match> findByTeam(@Param("team") String team);
    
    @Query("SELECT m FROM Match m WHERE m.matchDate BETWEEN :startDate AND :endDate")
    List<Match> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    List<Match> findByTeamAContainingIgnoreCaseOrTeamBContainingIgnoreCase(String teamA, String teamB);
}