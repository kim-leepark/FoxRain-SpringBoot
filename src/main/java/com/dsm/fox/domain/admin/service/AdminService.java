package com.dsm.fox.domain.admin.service;

import com.dsm.fox.domain.admin.Admin;
import com.dsm.fox.domain.admin.AdminRepository;
import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import com.dsm.fox.domain.admin.rqrs.AdminCreateRq;
import com.dsm.fox.domain.admin.rqrs.AdminLoginRq;
import com.dsm.fox.domain.phrase.report.PhraseReportRepository;
import com.dsm.fox.global.exception.exceptions.AlreadyNameException;
import com.dsm.fox.global.exception.exceptions.PasswordNoMatchedException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import com.dsm.fox.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PhraseReportRepository reportRepository;

    public void create(AdminCreateRq rq) {
        if (adminRepository.existsByName(rq.getName())) {
            throw new AlreadyNameException();
        }
        adminRepository.save(
                Admin.builder()
                        .password(rq.getPassword())
                        .name(rq.getName()).build()
        );
    }

    public AccessTokenRs login(AdminLoginRq rq) {
        Admin admin = adminRepository.findByName(rq.getName()).orElseThrow(UserNotFoundException::new);
        if (!admin.getPassword().equals(rq.getPassword())) {
            throw new PasswordNoMatchedException();
        }
        return AccessTokenRs.builder()
                .accessToken(jwtUtil.createAdminToken(admin.getId(), admin.getName())).build();
    }

}

