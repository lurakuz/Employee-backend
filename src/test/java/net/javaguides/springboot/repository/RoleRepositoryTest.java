package net.javaguides.springboot.repository;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.models.entity.ERole;
import net.javaguides.springboot.models.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void shouldSaveRole() {
        //given
        boolean b = roleRepository.existsById(1L);
        log.info("b = {}", b);
        roleRepository.save(Role.builder().id(5L).name(ERole.ROLE_USER).build());
        int size = roleRepository.findAll().size();
        log.info("size = {}", size);
        //when

        //then
    }
}