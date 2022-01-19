package net.javaguides.springboot.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ERole {
    ROLE_USER(0),
    ROLE_MANAGER(1),
    ROLE_ADMIN(2);

    private int value;
}
