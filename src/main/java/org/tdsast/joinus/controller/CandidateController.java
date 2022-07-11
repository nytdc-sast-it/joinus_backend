package org.tdsast.joinus.controller;

import java.util.Objects;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.entity.Candidate;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.model.request.CandidateRequest;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.model.response.ResponseData;
import org.tdsast.joinus.service.CandidateService;
import org.tdsast.joinus.service.ClubService;
import org.tdsast.joinus.service.DepartmentService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CandidateService candidateService;
    private final ClubService clubService;
    private final DepartmentService departmentService;

    public CandidateController(CandidateService candidateService, ClubService clubService,
            DepartmentService departmentService) {
        this.candidateService = candidateService;
        this.clubService = clubService;
        this.departmentService = departmentService;
    }

    private Club clubIdToClub(Long clubId) {
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            throw new IllegalArgumentException("社团不存在");
        }
        return club;
    }

    private Department departmentIdToDepartment(Long clubId, Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null || !Objects.equals(department.getClub().getId(), clubId)) {
            throw new IllegalArgumentException("社团部门不存在");
        }
        return department;
    }

    private Candidate requestToCandidate(CandidateRequest request) {
        Club club = clubIdToClub(request.getClub());
        Department choice1 = departmentIdToDepartment(club.getId(), request.getChoice1());
        Department choice2 = departmentIdToDepartment(club.getId(), request.getChoice2());
        return new Candidate(
                request.getName(),
                request.getStudentId(),
                request.getPhone(),
                request.getQq(),
                request.getCounselor(),
                club,
                choice1,
                choice2,
                request.getReason(),
                null, null, null);
    }

    @PostMapping("/join")
    public Response<ResponseData> join(@RequestBody @Valid CandidateRequest candidateRequest) {
        try {
            candidateService.newCandidate(requestToCandidate(candidateRequest));
        } catch (IllegalArgumentException e) {
            return Response.failure(null, e.getMessage(), 50000);
        } catch (Exception e) {
            logger.error("未知错误", e);
            return Response.failure(null, "未知错误", 50000);
        }
        return Response.success(null);
    }
}
