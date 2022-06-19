package com.dsm.fox.domain.admin.controller;

import com.dsm.fox.domain.admin.service.AdminDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminDeleteController {
    private final AdminDeleteService deleteService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/post/{id}")
    public void deletePost(@PathVariable int id) {
        deleteService.postDelete(id);
    }

}
