package com.example.technicalassessment.interfaces;

import com.example.technicalassessment.entity.Match;
import com.example.technicalassessment.enums.Sport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchService {

    List<Match> getAllMatches();
    Optional<Match> getMatchById(Long id);
    Match createMatch(Match match);
    Match updateMatch(Long id, Match matchDetails);
    void deleteMatch(Long id);
    List<Match> getMatchesBySport(Sport sport);
    List<Match> getMatchesByDate(LocalDate date);
    List<Match> getMatchesByTeam(String team);
    List<Match> getMatchesByDateRange(LocalDate startDate, LocalDate endDate);
    List<Match> searchMatchesByTeamName(String teamName);

}
