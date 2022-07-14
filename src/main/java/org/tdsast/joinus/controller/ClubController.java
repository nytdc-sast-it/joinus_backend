package org.tdsast.joinus.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.dto.ClubDTO;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.response.ClubListResponseData;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.service.ClubService;

import javax.inject.Inject;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @Inject
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    private ClubDTO clubToClubDTO(Club club) {
        return new ClubDTO(club.getId(), club.getName());
    }

    @GetMapping("/list")
    public Response<ClubListResponseData> list() {
        List<ClubDTO> list = clubService.getClubs().stream().map(this::clubToClubDTO)
            .collect(Collectors.toList());
        return Response.success(new ClubListResponseData(list));
    }
}
