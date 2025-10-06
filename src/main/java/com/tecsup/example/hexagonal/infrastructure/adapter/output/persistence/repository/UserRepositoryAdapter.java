package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    private final UserMapper userMapper;

    @Override
    public User save(User user) {

        // Domain to Entity
        UserEntity userEntity = this.userMapper.toEntity(user);

        // Save entity
        UserEntity entityCreated = this.jpaRepository.save(userEntity);

        log.info("User created: {}", entityCreated);

        // Entity to Domain
        return this.userMapper.toDomain(entityCreated);
    }

    @Override
    public Optional<User> findById(Long id) {

        return this.jpaRepository.findById(id).map(this.userMapper::toDomain);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jpaRepository.findByEmail(email).map(this.userMapper::toDomain);
    }

    @Override
    public List<User> findByApellidoPaterno(String apellidoPaterno) {
        return this.jpaRepository.findByApellidoPaterno(apellidoPaterno)
                .stream()
                .map(this.userMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<User> findByDni(String dni) {
        return this.jpaRepository.findByDni(dni)
                .stream()
                .map(this.userMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<User> findByEdadLessThan(Integer edad) {
        return this.jpaRepository.findByEdadLessThan(edad)
                .stream()
                .map(this.userMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
}
