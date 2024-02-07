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

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired(required = false)
    private MemberMapper mapper;

    public List<MemberDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public MemberDTO save(CreateMemberDTO data) {
        Member entity = repository.save(mapper.toModel(data));
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