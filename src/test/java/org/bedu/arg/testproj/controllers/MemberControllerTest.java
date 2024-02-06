package org.bedu.arg.testproj.controllers;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.services.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Mock
    private MemberService service;

    @InjectMocks
    private MemberController controller;

    @Test
    void findAll_shouldReturnListOfMembers() {
        // Arrange
        List<MemberDTO> expectedMembers = Collections.singletonList(new MemberDTO(/* Member details */));
        when(service.findAll()).thenReturn(expectedMembers);

        // Act
        List<MemberDTO> actualMembers = controller.findAll();

        // Assert
        assertEquals(expectedMembers, actualMembers);
    }

    @Test
    void save_shouldReturnSavedMember() {
        // Arrange
        CreateMemberDTO createMemberDTO = new CreateMemberDTO(/* Member details for creation */);
        MemberDTO expectedSavedMember = new MemberDTO(/* Saved member details */);
        when(service.save(createMemberDTO)).thenReturn(expectedSavedMember);

        // Act
        MemberDTO actualSavedMember = controller.save(createMemberDTO);

        // Assert
        assertEquals(expectedSavedMember, actualSavedMember);
    }
}

