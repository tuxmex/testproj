package org.bedu.arg.testproj.mapper;

import org.bedu.arg.testproj.dto.CreateMemberDTO;
import org.bedu.arg.testproj.dto.MemberDTO;
import org.bedu.arg.testproj.dto.UpdateMemberDTO;
import org.bedu.arg.testproj.models.Member;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {

    MemberDTO toDTO(Member model);

    List<MemberDTO> toDTO(List<Member> model);

    @Mapping(target = "id", ignore = true)
    Member toModel(CreateMemberDTO dto);

    // En vez de transformar un UpdateMemberDTO a Member
    // actualiza la referencia de Member con lo que haya en UpdateMemberDTO
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Member member, UpdateMemberDTO data);
}