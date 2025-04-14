package com.mickaelsouzadev.volcanoesfinder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mickaelsouzadev.volcanoesfinder.model.Volcano;
import com.mickaelsouzadev.volcanoesfinder.service.VolcanoService;

@RestController
@RequestMapping("/api/volcanoes")
public class VolcanoController {
    private final VolcanoService service;

    public VolcanoController(VolcanoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Volcano> listVolcanoes() {
        return service.getFormattedVolcanoes();
    }
}
