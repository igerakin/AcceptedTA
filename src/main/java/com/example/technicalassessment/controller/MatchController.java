package com.example.technicalassessment.controller;

import com.example.technicalassessment.entity.Match;
import com.example.technicalassessment.enums.Sport;
import com.example.technicalassessment.interfaces.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
@Tag(name = "Match Management", description = "APIs for managing sports matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    @Operation(summary = "Get all matches", description = "Retrieve a list of all matches")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match by ID", description = "Retrieve a specific match by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "404", description = "Match not found")
    })
    public ResponseEntity<Match> getMatchById(
            @Parameter(description = "ID of the match to be retrieved")
            @PathVariable Long id) {
        Optional<Match> match = matchService.getMatchById(id);
        return match.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new match", description = "Add a new match to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Match created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Match> createMatch(
            @Parameter(description = "Match to be created")
            @RequestBody Match match) {
        try {
            Match createdMatch = matchService.createMatch(match);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a match", description = "Update an existing match by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "404", description = "Match not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Match> updateMatch(
            @Parameter(description = "ID of the match to be updated")
            @PathVariable Long id,
            @Parameter(description = "Updated match data")
            @RequestBody Match matchDetails) {
        try {
            Match updatedMatch = matchService.updateMatch(id, matchDetails);
            return ResponseEntity.ok(updatedMatch);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a match", description = "Delete a match by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Match deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Match not found")
    })
    public ResponseEntity<Void> deleteMatch(
            @Parameter(description = "ID of the match to be deleted")
            @PathVariable Long id) {
        try {
            matchService.deleteMatch(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sport/{sport}")
    @Operation(summary = "Get matches by sport", description = "Retrieve matches filtered by sport type")
    @ApiResponse(responseCode = "200", description = "Matches retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> getMatchesBySport(
            @Parameter(description = "Sport type (FOOTBALL or BASKETBALL)")
            @PathVariable Sport sport) {
        List<Match> matches = matchService.getMatchesBySport(sport);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/date/{date}")
    @Operation(summary = "Get matches by date", description = "Retrieve matches for a specific date")
    @ApiResponse(responseCode = "200", description = "Matches retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> getMatchesByDate(
            @Parameter(description = "Match date (yyyy-MM-dd format)")
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Match> matches = matchService.getMatchesByDate(date);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/team/{team}")
    @Operation(summary = "Get matches by team", description = "Retrieve matches where the specified team is playing")
    @ApiResponse(responseCode = "200", description = "Matches retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> getMatchesByTeam(
            @Parameter(description = "Team name")
            @PathVariable String team) {
        List<Match> matches = matchService.getMatchesByTeam(team);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get matches by date range", description = "Retrieve matches within a specific date range")
    @ApiResponse(responseCode = "200", description = "Matches retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> getMatchesByDateRange(
            @Parameter(description = "Start date (yyyy-MM-dd format)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date (yyyy-MM-dd format)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Match> matches = matchService.getMatchesByDateRange(startDate, endDate);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/search")
    @Operation(summary = "Search matches by team name", description = "Find matches by team name (case-insensitive)")
    @ApiResponse(responseCode = "200", description = "Search completed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Match.class)))
    public ResponseEntity<List<Match>> searchMatchesByTeamName(
            @Parameter(description = "Team name to search for")
            @RequestParam String teamName) {
        List<Match> matches = matchService.searchMatchesByTeamName(teamName);
        return ResponseEntity.ok(matches);
    }
}