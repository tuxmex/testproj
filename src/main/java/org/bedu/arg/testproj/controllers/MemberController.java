package org.bedu.arg.testproj.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.PatchMemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.exceptions.MemberNotFoundException;
import org.bedu.arg.testproj.models.Member;
import org.bedu.arg.testproj.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Miembros de Proyecto", description = "CRUD de miembros del proyecto")
@RestController
@RequestMapping("member")
public class MemberController {

    private final MemberService service;
 
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

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

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @Valid @RequestBody UpdateMemberDTO data) throws MemberNotFoundException {
        service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Member> patchProject(
            @PathVariable long memberId,
            @RequestBody PatchMemberDTO patchDTO) throws MemberNotFoundException {
        Member updatedMember = service.patchMember(memberId, patchDTO);
        return ResponseEntity.ok(updatedMember);
    }
}