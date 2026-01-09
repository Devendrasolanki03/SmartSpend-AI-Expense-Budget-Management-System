package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

@Service
@Transactional
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    // ✅ CONSTRUCTOR INJECTION (FIX)
    public AuthService(UserRepository repo,
                       PasswordEncoder encoder,
                       JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void register(RegisterRequestDTO dto) {

        String email = dto.getEmail().toLowerCase().trim();

        if (repo.existsByEmail(email)) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName().trim());
        user.setEmail(email);
        user.setPassword(encoder.encode(dto.getPassword()));

        // ✅ MAP LOCATION FIELDS
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setCountry(dto.getCountry());

     // 🔒 DEFAULT ROLE — BACKEND DECIDES
        user.setRole(Role.USER);



        repo.save(user);
    }

    // ================= LOGIN =================
    public AuthResponseDTO login(LoginRequestDTO dto) {

        User user = repo.findByEmail(dto.getEmail().toLowerCase().trim())
                .orElseThrow(() -> new InvalidRequestException("Invalid credentials"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidRequestException("Invalid credentials");
        }

        String token = jwt.generateToken(user.getEmail(), user.getRole());

        return new AuthResponseDTO(token, "Login successful");
    }
}
