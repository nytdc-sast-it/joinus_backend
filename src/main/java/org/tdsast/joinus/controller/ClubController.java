package org.tdsast.joinus.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.dto.ClubDTO;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.response.ClubListResponseData;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.service.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private ClubService clubService;

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
