package com.rajtomar.sapient.microservice.controller;

import com.rajtomar.sapient.microservice.exception.NoDataFoundException;
import com.rajtomar.sapient.microservice.response.Standing;
import com.rajtomar.sapient.microservice.service.CountryService;
import com.rajtomar.sapient.microservice.service.LeagueService;
import com.rajtomar.sapient.microservice.service.StandingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.rajtomar.sapient.microservice.Constants.SCORE;
import static com.rajtomar.sapient.microservice.Constants.TEAM;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(TEAM)
public class TeamController {

    final CountryService countryService;
    final LeagueService leagueService;
    final StandingService standingService;

    @RequestMapping(SCORE)
    public Standing getTeamStanding(@RequestParam String countryName,
                                    @RequestParam String leagueName,
                                    @RequestParam String teamName) {
        String countryId = null;
        try {
            countryId = countryService.getCountryId(countryName);
            String leagueId = leagueService.getLeagueId(countryId, leagueName);
            Standing standing = standingService.getStanding(leagueId, teamName);
            standing.setCountryId(countryId);
            return standing;
        } catch (Exception e) {
            throw new NoDataFoundException("There is no data found with the given input.");
        }
    }

}
