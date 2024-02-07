package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.exceptions.MemberNotFoundException;
import org.bedu.arg.testproj.mapper.MemberMapper;
import org.bedu.arg.testproj.models.Member;
import org.bedu.arg.testproj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;
    private final MemberMapper mapper;

    @Autowired
    public MemberService(MemberRepository repository, MemberMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public List<MemberDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Transactional
    public MemberDTO save(CreateMemberDTO data) {
        System.out.println("To Model: "+mapper.toModel(data));
        Member entity = repository.save(mapper.toModel(data));
        System.out.println("Guardada: "+ entity);
        return mapper.toDTO(entity);
    }

    public void update(long memberId, UpdateMemberDTO data) throws MemberNotFoundException {
        Optional<Member> result = repository.findById(memberId);

        if (result.isEmpty()) {
            throw new MemberNotFoundException(memberId);
        }

        Member member = result.get();

        // Aplicar los cambios al artista
        mapper.update(member, data);

        repository.save(member);
    }
}