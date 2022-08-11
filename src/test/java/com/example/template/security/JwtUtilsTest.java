package com.example.template.security;

import com.example.template.security.util.JwtUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JwtUtilsTest {

    @Test
    public void testCreateJwt() {
        List<String> roles = new ArrayList<String>(){{
            add("normal");
        }};
        Assertions.assertNotNull(JwtUtils.createJwt("uncle-lv", "uncle-lv", roles));
    }

    @Test
    public void testGetClaims() {
        List<String> roles = new ArrayList<String>(){{
            add("normal");
        }};
        String jwt = JwtUtils.createJwt("uncle-lv", "uncle-lv", roles);
        Assertions.assertEquals("uncle-lv", JwtUtils.getId(jwt));
    }
}
