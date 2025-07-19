package com.example.technicalassessment.service;

import com.example.technicalassessment.entity.MatchOdds;
import com.example.technicalassessment.interfaces.MatchOddsService;
import com.example.technicalassessment.repository.MatchOddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MatchOddsServiceImpl implements MatchOddsService {

    @Autowired
    private MatchOddsRepository matchOddsRepository;

    @Override
    public List<MatchOdds> getAllMatchOdds() {
        return matchOddsRepository.findAll();
    }
    @Override
    public Optional<MatchOdds> getMatchOddsById(Long id) {
        return matchOddsRepository.findById(id);
    }
    @Override
    public MatchOdds createMatchOdds(MatchOdds matchOdds) {
        return matchOddsRepository.save(matchOdds);
    }
    @Override
    public MatchOdds updateMatchOdds(Long id, MatchOdds matchOddsDetails) {
        MatchOdds matchOdds = matchOddsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MatchOdds not found with id: " + id));

        matchOdds.setMatchId(matchOddsDetails.getMatchId());
        matchOdds.setSpecifier(matchOddsDetails.getSpecifier());
        matchOdds.setOdd(matchOddsDetails.getOdd());

        return matchOddsRepository.save(matchOdds);
    }
    @Override
    public void deleteMatchOdds(Long id) {
        if (!matchOddsRepository.existsById(id)) {
            throw new RuntimeException("MatchOdds not found with id: " + id);
        }
        matchOddsRepository.deleteById(id);
    }
    @Override
    public List<MatchOdds> getMatchOddsByMatchId(Long matchId) {
        return matchOddsRepository.findByMatchId(matchId);
    }
    @Override
    public List<MatchOdds> getMatchOddsBySpecifier(String specifier) {
        return matchOddsRepository.findBySpecifier(specifier);
    }
    @Override
    public List<MatchOdds> getMatchOddsByMatchIdAndSpecifier(Long matchId, String specifier) {
        return matchOddsRepository.findByMatchIdAndSpecifier(matchId, specifier);
    }
    @Override
    public List<MatchOdds> getMatchOddsByOddRange(BigDecimal minOdd, BigDecimal maxOdd) {
        return matchOddsRepository.findByOddRange(minOdd, maxOdd);
    }
    @Override
    public List<MatchOdds> getMatchOddsByMatchIdOrderByOddAsc(Long matchId) {
        return matchOddsRepository.findByMatchIdOrderByOddAsc(matchId);
    }
    @Override
    public List<MatchOdds> getMatchOddsByMatchIdOrderByOddDesc(Long matchId) {
        return matchOddsRepository.findByMatchIdOrderByOddDesc(matchId);
    }
    @Override
    public List<String> getAllSpecifiers() {
        return matchOddsRepository.findAllDistinctSpecifiers();
    }

}
