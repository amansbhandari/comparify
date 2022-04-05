package ca.dal.comparify.user.model.iam.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserAuthorizationRoleEnumTest {
    @Test
    void testValueOf() {
        assertEquals("USER", UserAuthorizationRoleEnum.valueOf("USER").getValue());
    }
}

