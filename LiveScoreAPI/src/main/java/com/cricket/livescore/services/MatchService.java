package com.cricket.livescore.services;

import com.cricket.livescore.dto.CricketInfoDto;
import com.cricket.livescore.model.CricketInfo;
import com.cricket.livescore.repo.MatchRepo;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Service
public class MatchService {
    private MatchRepo matchRepo;


    public MatchService(MatchRepo matchRepo){
        this.matchRepo=matchRepo;
    }

public List<CricketInfo> getLiveMatches(){
        List<CricketInfo> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                CricketInfo match1 = new CricketInfo();
                match1.setTeamHeading(teamsHeading);
                match1.setMatchNumberVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlTeam(bowlTeam);
                match1.setBowlTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);



                matches.add(match1);

//                update the match in database


                updateMatch(match1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<List<String>> getCWC2023PointTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
//                    System.out.println(points);
                    pointTable.add(points);
                }


            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }

    public List<List<String>> getWPL2024PointTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = "https://www.cricbuzz.com/cricket-series/7518/womens-premier-league-2024/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
//                    System.out.println(points);
                    pointTable.add(points);
                }


            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }

    private void updateMatch(CricketInfo match1) {
        CricketInfo match = matchRepo.findByTeamHeading(match1.getTeamHeading());
        if (match == null) {
            matchRepo.save(match1);
        } else {
            matchRepo.save(match);
        }

    }
    public List<CricketInfo> getAllMatches(){
        return  matchRepo.findAll();
    }

    public CricketInfo getMatcheDetails(String matchHeading){

        return matchRepo.findByTeamHeading(matchHeading);
    }


}
