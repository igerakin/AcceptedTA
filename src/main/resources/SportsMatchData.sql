-- Sample data for matches table
INSERT INTO matches (description, match_date, match_time, team_a, team_b, sport) VALUES
('Premier League Match', '2025-01-20', '15:00:00', 'Manchester United', 'Liverpool', 0),
('Champions League Final', '2025-02-15', '20:00:00', 'Real Madrid', 'Barcelona', 0),
('La Liga Classic', '2025-01-25', '18:30:00', 'Atletico Madrid', 'Sevilla', 0),
('NBA Regular Season', '2025-01-22', '21:00:00', 'Los Angeles Lakers', 'Golden State Warriors', 1),
('NBA Conference Final', '2025-02-10', '20:30:00', 'Boston Celtics', 'Miami Heat', 1),
('EuroLeague Quarter Final', '2025-02-05', '19:00:00', 'Real Madrid Basketball', 'Barcelona Basketball', 1),
('Premier League Derby', '2025-01-28', '16:00:00', 'Chelsea', 'Arsenal', 0),
('NBA Playoff Game', '2025-02-20', '22:00:00', 'Denver Nuggets', 'Phoenix Suns', 1);

-- Sample data for match_odds table
-- Match 1: Manchester United vs Liverpool
INSERT INTO match_odds (match_id, specifier, odd) VALUES
(1, '1', 2.50),
(1, 'X', 3.20),
(1, '2', 2.80),

-- Match 2: Real Madrid vs Barcelona
(2, '1', 2.10),
(2, 'X', 3.40),
(2, '2', 3.50),

-- Match 3: Atletico Madrid vs Sevilla
(3, '1', 1.95),
(3, 'X', 3.60),
(3, '2', 4.20),

-- Match 4: Lakers vs Warriors
(4, '1', 1.85),
(4, '2', 1.95),

-- Match 5: Celtics vs Heat
(5, '1', 2.30),
(5, '2', 1.60),

-- Match 6: Real Madrid Basketball vs Barcelona Basketball
(6, '1', 2.05),
(6, '2', 1.75),

-- Match 7: Chelsea vs Arsenal
(7, '1', 2.60),
(7, 'X', 3.10),
(7, '2', 2.70),

-- Match 8: Nuggets vs Suns
(8, '1', 1.70),
(8, '2', 2.15);
