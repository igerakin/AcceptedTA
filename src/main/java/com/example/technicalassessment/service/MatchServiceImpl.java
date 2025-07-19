package com.example.technicalassessment.service;

import com.example.technicalassessment.entity.Match;
import com.example.technicalassessment.enums.Sport;
import com.example.technicalassessment.interfaces.MatchService;
import com.example.technicalassessment.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    
    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
    
    @Override
    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }
    
    @Override
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }
    
    @Override
    public Match updateMatch(Long id, Match matchDetails) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        
        match.setDescription(matchDetails.getDescription());
        match.setMatchDate(matchDetails.getMatchDate());
        match.setMatchTime(matchDetails.getMatchTime());
        match.setTeamA(matchDetails.getTeamA());
        match.setTeamB(matchDetails.getTeamB());
        match.setSport(matchDetails.getSport());
        
        return matchRepository.save(match);
    }
    
    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }
    
    @Override
    public List<Match> getMatchesBySport(Sport sport) {
        return matchRepository.findBySport(sport);
    }
    
    @Override
    public List<Match> getMatchesByDate(LocalDate date) {
        return matchRepository.findByMatchDate(date);
    }
    
    @Override
    public List<Match> getMatchesByTeam(String team) {
        return matchRepository.findByTeam(team);
    }
    
    @Override
    public List<Match> getMatchesByDateRange(LocalDate startDate, LocalDate endDate) {
        return matchRepository.findByDateRange(startDate, endDate);
    }
    
    @Override
    public List<Match> searchMatchesByTeamName(String teamName) {
        return matchRepository.findByTeamAContainingIgnoreCaseOrTeamBContainingIgnoreCase(teamName, teamName);
    }
}