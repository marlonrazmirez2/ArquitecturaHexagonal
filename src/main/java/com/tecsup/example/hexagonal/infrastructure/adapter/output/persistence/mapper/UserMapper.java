package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper;


import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Convert User domain to UserEntity
     * @param domain
     * @return
     */
    UserEntity toEntity(User domain);

    /**
     * Convert UserEntity to User domain
     * @param entity
     * @return
     */
    User toDomain(UserEntity entity);


    @Mapping(target = "id", ignore = true) // New users don't have ID
    @Mapping(target = "name", source = "name")
    @Mapping(target = "apellidoPaterno", source = "apellidoPaterno")
    @Mapping(target = "apellidoMaterno", source = "apellidoMaterno")
    @Mapping(target = "edad", source = "edad")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "numeroTelefonico", source = "numeroTelefonico")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", ignore = true) // Will be set by service
    @Mapping(target = "enabled", constant = "true") // Default to enabled
    @Mapping(target = "role", ignore = true) // Will be set by service
    User toDomain(UserRequest request);


    UserResponse toResponse(User createUser);

    default String map(com.tecsup.example.hexagonal.domain.model.Role role) {
        return role != null ? role.getName() : null;
    }
}
