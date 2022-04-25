package com.dsm.fox.domain.admin.controller;

import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import com.dsm.fox.domain.admin.rqrs.AdminCreateRq;
import com.dsm.fox.domain.admin.rqrs.AdminLoginRq;
import com.dsm.fox.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminAuthController {
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
}
