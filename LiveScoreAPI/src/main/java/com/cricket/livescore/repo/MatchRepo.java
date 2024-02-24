package com.cricket.livescore.repo;

import com.cricket.livescore.constant.MatchStatus;
import com.cricket.livescore.model.CricketInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepo extends JpaRepository<CricketInfo,Long> {
    //List<CricketInfo> findByMatchStatus(MatchStatus matchStatus);

    CricketInfo findByTeamHeading(String teamHeading);
}
