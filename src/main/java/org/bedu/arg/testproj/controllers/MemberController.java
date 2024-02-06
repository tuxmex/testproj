package org.bedu.arg.testproj.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Miembros de Proyecto", description = "CRUD de miembros del proyecto")
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO save(@Valid @RequestBody CreateMemberDTO data) {
        return service.save(data);
    }
}