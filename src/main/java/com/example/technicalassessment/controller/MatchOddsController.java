package com.example.technicalassessment.controller;

import com.example.technicalassessment.entity.MatchOdds;
import com.example.technicalassessment.interfaces.MatchOddsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match-odds")
@RequiredArgsConstructor
@Tag(name = "Match Odds Management", description = "APIs for managing match odds")
public class MatchOddsController {

    @Autowired
    private MatchOddsService matchOddsService;

    @GetMapping
    @Operation(summary = "Get all match odds", description = "Retrieve a list of all match odds")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getAllMatchOdds() {
        List<MatchOdds> matchOdds = matchOddsService.getAllMatchOdds();
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match odds by ID", description = "Retrieve specific match odds by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match odds found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchOdds.class))),
            @ApiResponse(responseCode = "404", description = "Match odds not found")
    })
    public ResponseEntity<MatchOdds> getMatchOddsById(
            @Parameter(description = "ID of the match odds to be retrieved")
            @PathVariable Long id) {
        Optional<MatchOdds> matchOdds = matchOddsService.getMatchOddsById(id);
        return matchOdds.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new match odds", description = "Add new match odds to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Match odds created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchOdds.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<MatchOdds> createMatchOdds(
            @Parameter(description = "Match odds to be created")
            @RequestBody MatchOdds matchOdds) {
        try {
            MatchOdds createdMatchOdds = matchOddsService.createMatchOdds(matchOdds);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMatchOdds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update match odds", description = "Update existing match odds by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match odds updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchOdds.class))),
            @ApiResponse(responseCode = "404", description = "Match odds not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<MatchOdds> updateMatchOdds(
            @Parameter(description = "ID of the match odds to be updated")
            @PathVariable Long id,
            @Parameter(description = "Updated match odds data")
            @RequestBody MatchOdds matchOddsDetails) {
        try {
            MatchOdds updatedMatchOdds = matchOddsService.updateMatchOdds(id, matchOddsDetails);
            return ResponseEntity.ok(updatedMatchOdds);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete match odds", description = "Delete match odds by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Match odds deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Match odds not found")
    })
    public ResponseEntity<Void> deleteMatchOdds(
            @Parameter(description = "ID of the match odds to be deleted")
            @PathVariable Long id) {
        try {
            matchOddsService.deleteMatchOdds(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/match/{matchId}")
    @Operation(summary = "Get match odds by match ID", description = "Retrieve all odds for a specific match")
    @ApiResponse(responseCode = "200", description = "Match odds retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getMatchOddsByMatchId(
            @Parameter(description = "Match ID")
            @PathVariable Long matchId) {
        List<MatchOdds> matchOdds = matchOddsService.getMatchOddsByMatchId(matchId);
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/specifier/{specifier}")
    @Operation(summary = "Get match odds by specifier", description = "Retrieve all odds for a specific specifier")
    @ApiResponse(responseCode = "200", description = "Match odds retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getMatchOddsBySpecifier(
            @Parameter(description = "Specifier (e.g., '1', 'X', '2', 'Over 2.5', etc.)")
            @PathVariable String specifier) {
        List<MatchOdds> matchOdds = matchOddsService.getMatchOddsBySpecifier(specifier);
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/match/{matchId}/specifier/{specifier}")
    @Operation(summary = "Get match odds by match ID and specifier", 
               description = "Retrieve odds for a specific match and specifier combination")
    @ApiResponse(responseCode = "200", description = "Match odds retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getMatchOddsByMatchIdAndSpecifier(
            @Parameter(description = "Match ID")
            @PathVariable Long matchId,
            @Parameter(description = "Specifier")
            @PathVariable String specifier) {
        List<MatchOdds> matchOdds = matchOddsService.getMatchOddsByMatchIdAndSpecifier(matchId, specifier);
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/odd-range")
    @Operation(summary = "Get match odds by odd range", 
               description = "Retrieve odds within a specific range")
    @ApiResponse(responseCode = "200", description = "Match odds retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getMatchOddsByOddRange(
            @Parameter(description = "Minimum odd value")
            @RequestParam BigDecimal minOdd,
            @Parameter(description = "Maximum odd value")
            @RequestParam BigDecimal maxOdd) {
        List<MatchOdds> matchOdds = matchOddsService.getMatchOddsByOddRange(minOdd, maxOdd);
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/match/{matchId}/sorted")
    @Operation(summary = "Get match odds sorted by odd value", 
               description = "Retrieve odds for a match sorted by odd value")
    @ApiResponse(responseCode = "200", description = "Match odds retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MatchOdds.class)))
    public ResponseEntity<List<MatchOdds>> getMatchOddsSorted(
            @Parameter(description = "Match ID")
            @PathVariable Long matchId,
            @Parameter(description = "Sort order: 'asc' for ascending, 'desc' for descending")
            @RequestParam(defaultValue = "asc") String order) {
        List<MatchOdds> matchOdds;
        if ("desc".equalsIgnoreCase(order)) {
            matchOdds = matchOddsService.getMatchOddsByMatchIdOrderByOddDesc(matchId);
        } else {
            matchOdds = matchOddsService.getMatchOddsByMatchIdOrderByOddAsc(matchId);
        }
        return ResponseEntity.ok(matchOdds);
    }

    @GetMapping("/specifiers")
    @Operation(summary = "Get all specifiers", description = "Retrieve all distinct specifiers used in match odds")
    @ApiResponse(responseCode = "200", description = "Specifiers retrieved successfully")
    public ResponseEntity<List<String>> getAllSpecifiers() {
        List<String> specifiers = matchOddsService.getAllSpecifiers();
        return ResponseEntity.ok(specifiers);
    }
}