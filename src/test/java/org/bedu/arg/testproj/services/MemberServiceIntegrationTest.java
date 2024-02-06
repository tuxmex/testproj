package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.exceptions.MemberNotFoundException;
import org.bedu.arg.testproj.mapper.MemberMapper;
import org.bedu.arg.testproj.models.Member;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @MockBean
    MemberRepository memberRepository;
    @Autowired
    MemberMapper memberMapper;

    @Test
    void saveAndFindAll() {
        // Arrange
        CreateMemberDTO createMemberDTO = new CreateMemberDTO();
        createMemberDTO.setMemberName("Juan");
        createMemberDTO.setEmail("crackiman@gmail.com");
        
        Member model = new Member();
        model.setId(8794L);
        model.setMemberName(createMemberDTO.getMemberName());
        model.setEmail(createMemberDTO.getEmail());
        List<Member> data = new LinkedList<>();
        data.add(model);
        
        // Act
        MemberDTO savedMember = memberService.save(createMemberDTO);
        
        when(memberRepository.save(any(Member.class))).thenReturn(model);
        MemberDTO foundMember = memberService.findAll().get(0);

        // Assert
        assertEquals(savedMember.getMemberName(), foundMember.getMemberName());
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

}
