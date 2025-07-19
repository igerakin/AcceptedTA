package com.example.technicalassessment.interfaces;

import com.example.technicalassessment.entity.MatchOdds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MatchOddsService {

    List<MatchOdds> getAllMatchOdds();

    Optional<MatchOdds> getMatchOddsById(Long id);

    MatchOdds createMatchOdds(MatchOdds matchOdds);

    MatchOdds updateMatchOdds(Long id, MatchOdds matchOddsDetails);

    void deleteMatchOdds(Long id);

    List<MatchOdds> getMatchOddsByMatchId(Long matchId);

    List<MatchOdds> getMatchOddsBySpecifier(String specifier);

    List<MatchOdds> getMatchOddsByMatchIdAndSpecifier(Long matchId, String specifier);

    List<MatchOdds> getMatchOddsByOddRange(BigDecimal minOdd, BigDecimal maxOdd);

    List<MatchOdds> getMatchOddsByMatchIdOrderByOddAsc(Long matchId);

    List<MatchOdds> getMatchOddsByMatchIdOrderByOddDesc(Long matchId);

    List<String> getAllSpecifiers();

}
