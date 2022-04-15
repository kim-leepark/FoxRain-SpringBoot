package com.dsm.fox.domain.admin.service;

import com.dsm.fox.domain.admin.Admin;
import com.dsm.fox.domain.admin.AdminRepository;
import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import com.dsm.fox.domain.admin.rqrs.AdminCreateRq;
import com.dsm.fox.domain.admin.rqrs.AdminLoginRq;
import com.dsm.fox.domain.admin.rqrs.PhraseReportRs;
import com.dsm.fox.domain.phrase.report.PhraseReportRepository;
import com.dsm.fox.domain.user.UserRs;
import com.dsm.fox.global.exception.exceptions.AlreadyNameException;
import com.dsm.fox.global.exception.exceptions.PasswordNoMatchedException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import com.dsm.fox.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Admin admin = adminRepository.findById(rq.getId()).orElseThrow(UserNotFoundException::new);
        if (!admin.getPassword().equals(rq.getPassword())) {
            throw new PasswordNoMatchedException();
        }
        return AccessTokenRs.builder()
                .accessToken(jwtUtil.createAdminToken(admin.getName())).build();
    }

    public List<PhraseReportRs> getPhraseList(Pageable pageable) {
        List<PhraseReportRs> phrases = reportRepository.findAll(pageable)
                .stream().map(phrase ->
                    PhraseReportRs.builder()
                            .id(phrase.getId())
                            .content(phrase.getContent())
                            .user(UserRs.builder()
                                    .id(phrase.getUser().getId())
                                    .name(phrase.getUser().getName())
                                    .build()).build()).collect(Collectors.toList());
        return phrases;
    }
}

