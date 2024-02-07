package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.exceptions.MemberNotFoundException;
import org.bedu.arg.testproj.mapper.MemberMapper;
import org.bedu.arg.testproj.models.Member;
import org.bedu.arg.testproj.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberServiceIntegrationTest {
    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("Service should return members from repository")
    void findAllTest() {
        List<Member> data = new LinkedList<>();

        Member member = new Member();

        member.setId(6548L);
        member.setMemberName("Laura Moctezuma");
        member.setEmail("lmoctezuma@gmail.com");

        data.add(member);

        when(memberRepository.findAll()).thenReturn(data);

        List<MemberDTO> result = memberService.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(member.getId(), result.get(0).getId());
        assertEquals(member.getMemberName(), result.get(0).getMemberName());
        assertEquals(member.getEmail(), result.get(0).getEmail());
  }

  @Test
  @DisplayName("Service should save a movie in repository")
  void saveTest() {
    CreateMemberDTO createMemberDTO = new CreateMemberDTO();
    createMemberDTO.setMemberName("Juan");
    createMemberDTO.setEmail("crackiman@gmail.com");
    
    Member model = new Member();
    model.setId(8794L);
    model.setMemberName(createMemberDTO.getMemberName());
    model.setEmail(createMemberDTO.getEmail());
    
    when(memberRepository.save(any(Member.class))).thenReturn(model);

    MemberDTO result = memberService.save(createMemberDTO);

    assertNotNull(result);
    assertEquals(model.getId(), result.getId());
    assertEquals(model.getMemberName(), result.getMemberName());
    assertEquals(model.getEmail(), result.getEmail());
  }


    @Test
    void saveAndFindAll() {
        // Arrange
        CreateMemberDTO createMemberDTO = new CreateMemberDTO();
        createMemberDTO.setMemberName("Juan");
        createMemberDTO.setEmail("crackiman@gmail.com");
        
        Member savedMember = new Member();
        savedMember.setId(3325L);
        savedMember.setMemberName(createMemberDTO.getMemberName());
        savedMember.setEmail(createMemberDTO.getEmail());
        List<Member> data = new LinkedList<>();
    
        // Act
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember); // Simulamos la llamada al repositorio
        MemberDTO result = memberService.save(createMemberDTO);

        data.add(memberMapper.toModel(createMemberDTO));
        when(memberRepository.findAll()).thenReturn(data);
        MemberDTO foundMember = memberService.findAll().get(0);

        // Assert
        assertEquals(result.getMemberName(), foundMember.getMemberName());
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
