package org.tdsast.joinus.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.config.security.JWTUtils;
import org.tdsast.joinus.model.dto.CandidateDTO;
import org.tdsast.joinus.model.entity.Candidate;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.model.request.CandidateRequest;
import org.tdsast.joinus.model.response.CandidateListResponseData;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.model.response.ResponseData;
import org.tdsast.joinus.service.CandidateService;
import org.tdsast.joinus.service.ClubService;
import org.tdsast.joinus.service.DepartmentService;
import org.tdsast.joinus.service.UserService;
import org.tdsast.joinus.utils.ExcelHelper;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CandidateService candidateService;
    private final ClubService clubService;
    private final DepartmentService departmentService;
    private final UserService userService;

    @Inject
    public CandidateController(CandidateService candidateService, ClubService clubService,
                               DepartmentService departmentService, UserService userService) {
        this.candidateService = candidateService;
        this.clubService = clubService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    private Club clubIdToClub(Long clubId) {
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            throw new IllegalArgumentException("社团不存在");
        }
        return club;
    }

    private Department departmentIdToDepartment(Long clubId, Long departmentId) {
        Club club = clubService.getClubById(clubId);
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null || !club.getDepartments().contains(department)) {
            throw new IllegalArgumentException("社团部门不存在");
        }
        return department;
    }

    private Candidate requestToCandidate(CandidateRequest request) {
        Club club = clubIdToClub(request.getClub());
        Department choice1 = departmentIdToDepartment(club.getId(), request.getChoice1());
        Department choice2 = departmentIdToDepartment(club.getId(), request.getChoice2());
        return new Candidate(request.getName(), request.getStudentId(), request.getPhone(),
                request.getQq(), request.getMajor(), request.getCounselor(), club, choice1, choice2,
                request.getReason(), null, null, null);
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

    private CandidateDTO candidateToCandidateDTO(Candidate candidate) {
        return new CandidateDTO(candidate.getId(), candidate.getName(), candidate.getStudentId(),
                candidate.getPhone(), candidate.getQq(), candidate.getMajor(),
                candidate.getCounselor(), candidate.getClub().getName(),
                candidate.getChoice1().getName(), candidate.getChoice2().getName(),
                candidate.getReason(), candidate.getCreatedAt());
    }

    @GetMapping("/list")
    public Response<CandidateListResponseData> list(@RequestParam int current,
                                                    @RequestParam int pageSize, @RequestParam(required = false) String name,
                                                    @RequestParam(name = "club", required = false) Long clubId,
                                                    @RequestParam(name = "department", required = false) Long departmentId) {
        String currentUsername = JWTUtils.getUsernameFromToken((String) SecurityUtils.getSubject().getPrincipal());
        User user = userService.getUserByUsername(currentUsername);
        if (Boolean.FALSE.equals(user.getIsAdmin()) && user.getClub() != null) {
            clubId = user.getClub().getId();
        }
        Club club = null;
        if (clubId != null) {
            club = clubIdToClub(clubId);
        }
        if (Boolean.FALSE.equals(user.getIsAdmin()) && user.getDepartment() != null) {
            departmentId = user.getDepartment().getId();
        }
        Department department = null;
        if (departmentId != null) {
            department = departmentIdToDepartment(clubId, departmentId);
        }
        List<CandidateDTO> list = candidateService.getCandidates(current, pageSize, name, club, department)
            .stream().map(this::candidateToCandidateDTO).collect(Collectors.toList());
        long total = candidateService.getCandidatesCount(name, club, department);
        return Response.success(new CandidateListResponseData(list, total));
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> export(@RequestParam(required = false) String name,
                                           @RequestParam(name = "club", required = false) Long clubId,
                                           @RequestParam(name = "department", required = false) Long departmentId) {
        String currentUsername = JWTUtils.getUsernameFromToken((String) SecurityUtils.getSubject().getPrincipal());
        User user = userService.getUserByUsername(currentUsername);
        if (Boolean.FALSE.equals(user.getIsAdmin()) && user.getClub() != null) {
            clubId = user.getClub().getId();
        }
        Club club = null;
        if (clubId != null) {
            club = clubIdToClub(clubId);
        }
        if (Boolean.FALSE.equals(user.getIsAdmin()) && user.getDepartment() != null) {
            departmentId = user.getDepartment().getId();
        }
        Department department = null;
        if (departmentId != null) {
            department = departmentIdToDepartment(clubId, departmentId);
        }
        InputStreamResource file = new InputStreamResource(candidateService.export(name, club, department));
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + Instant.now() + ".xlsx")
            .contentType(MediaType.parseMediaType(ExcelHelper.TYPE)).body(file);
    }
}
