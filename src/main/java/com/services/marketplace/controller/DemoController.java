package com.services.marketplace.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "/test")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String welcome() {
        return "Welcome from secure endpoint";
    }
}
