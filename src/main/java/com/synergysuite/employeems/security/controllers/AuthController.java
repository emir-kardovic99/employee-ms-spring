package com.synergysuite.employeems.security.controllers;

import com.synergysuite.employeems.security.dto.LoginDTO;
import com.synergysuite.employeems.security.jwt.JwtTokenProvider;
import com.synergysuite.employeems.security.jwt.dto.JwtTokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("api/authenticate")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
            );
            Authentication auth = authenticationManager.authenticate(authentication); // Does a lot background work

            JwtTokenDTO token = jwtTokenProvider.generateToken(auth, loginDTO.isRememberMe());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
