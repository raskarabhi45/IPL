package com.example.demo.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        // here we taking all value from Match Input where that MatchInput taking
        // the values from our CSV usng the spring batch
        Match match = new Match(); // created object of the Match
        // id
        match.setId(Long.parseLong(matchInput.getId())); // converting id to Long
        // city
        match.setCity(matchInput.getCity());
        // date
        match.setDate(LocalDate.parse(matchInput.getDate())); // con date to Local Date
        // player of the match
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        // venue
        match.setVenue(matchInput.getVenue());

        // set team1 and team1 depending on the innings order
        String firstInningTeam, secondInningTeam;
        // if decision is bat then first team is toss winner
        // because toss winner is decided to bat
        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        } else { // other wise second team
            secondInningTeam = matchInput.getToss_winner();
            firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }

        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);

        // Toss winner and remaining field as it is
        match.setTossWinner(matchInput.getToss_winner());

        match.setTossDecision(matchInput.getToss_decision());
        match.setMatchWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        // and finally we return the match instance that we created
        return match;
    }

}
