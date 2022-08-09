package com.sastit.joinus.controller;

import com.sastit.joinus.model.dto.ClubDTO;
import com.sastit.joinus.model.dto.DepartmentDTO;
import com.sastit.joinus.model.entity.Club;
import com.sastit.joinus.model.entity.Department;
import com.sastit.joinus.model.request.NewClubRequest;
import com.sastit.joinus.model.response.ClubListResponseData;
import com.sastit.joinus.model.response.ClubResponseData;
import com.sastit.joinus.model.response.Response;
import com.sastit.joinus.service.ClubService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @Inject
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    private DepartmentDTO departmentToDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName());
    }

    private ClubDTO clubToClubDTO(Club club) {
        return new ClubDTO(club.getId(), club.getName(),
            club.getDepartments().stream().map(this::departmentToDepartmentDTO).collect(Collectors.toList()));
    }

    @GetMapping("/list")
    public Response<ClubListResponseData> list() {
        List<ClubDTO> list = clubService.getClubs().stream().map(this::clubToClubDTO)
            .collect(Collectors.toList());
        return Response.success(new ClubListResponseData(list));
    }

    @PostMapping("/new")
    public Response<ClubResponseData> newClub(@RequestBody @Valid NewClubRequest request) {
        Club club = clubService.newClub(request.getName());
        return Response.success(new ClubResponseData(clubToClubDTO(club)));
    }

    @DeleteMapping("/{clubId}")
    public Response<ClubResponseData> removeClub(@PathVariable Long clubId) {
        clubService.removeClub(clubId);
        return Response.success(new ClubResponseData(null));
    }
}
