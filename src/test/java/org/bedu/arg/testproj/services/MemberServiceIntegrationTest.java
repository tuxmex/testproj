package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.exceptions.MemberNotFoundException;
import org.bedu.arg.testproj.mapper.MemberMapper;
import org.bedu.arg.testproj.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    MemberService memberService;
    MemberRepository memberRepository;
    MemberMapper memberMapper;

    @Autowired
    public MemberServiceIntegrationTest(MemberService memberService, MemberRepository memberRepository,
            MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Test
    void saveAndFindAll() {
        // Arrange
        CreateMemberDTO createMemberDTO = new CreateMemberDTO(/* Datos del miembro a crear */);

        // Act
        MemberDTO savedMember = memberService.save(createMemberDTO);
        MemberDTO foundMember = memberService.findAll().get(0);

        // Assert
        assertEquals(savedMember.getMemberName(), foundMember.getMemberName());
        // Agrega más aserciones según sea necesario
    }

    @Test
    void updateAndFindById() throws MemberNotFoundException {
        // Arrange
        CreateMemberDTO createMemberDTO = new CreateMemberDTO(/* Datos del miembro a crear */);
        MemberDTO savedMember = memberService.save(createMemberDTO);

        UpdateMemberDTO updateMemberDTO = new UpdateMemberDTO(/* Datos actualizados del miembro */);

        // Act
        memberService.update(savedMember.getId(), updateMemberDTO);
        MemberDTO updatedMember = memberService.findAll().get(0);

        // Assert
        assertEquals(updateMemberDTO.getEmail(), updatedMember.getEmail());
        
        // Agrega más aserciones según sea necesario
    }

    @Test
    void updateNonExistingMember() {
        // Act and Assert
        assertThrows(MemberNotFoundException.class, () -> memberService.update(999L, new UpdateMemberDTO()));
    }

    // Puedes agregar más pruebas según sea necesario
}
