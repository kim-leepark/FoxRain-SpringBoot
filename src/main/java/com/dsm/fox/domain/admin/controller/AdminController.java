package com.dsm.fox.domain.admin.controller;

import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import com.dsm.fox.domain.admin.rqrs.AdminCreateRq;
import com.dsm.fox.domain.admin.rqrs.AdminLoginRq;
import com.dsm.fox.domain.admin.rqrs.PhraseReportRs;
import com.dsm.fox.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createAdmin(@RequestBody AdminCreateRq rq) {
        adminService.create(rq);
    }

    @PutMapping
    public AccessTokenRs login(@RequestBody AdminLoginRq rq) {
        return adminService.login(rq);
    }

    @GetMapping("/phrase/reports")
    public List<PhraseReportRs> getPhrases(final Pageable pageable) {
        return adminService.getPhraseList(pageable);
    }

}
