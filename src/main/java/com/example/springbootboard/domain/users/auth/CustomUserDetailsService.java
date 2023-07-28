package com.example.springbootboard.domain.users.auth;

import com.example.springbootboard.domain.users.User;
import com.example.springbootboard.domain.users.UserRepository;
import com.example.springbootboard.domain.users.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 입력해주세요."));

        session.setAttribute("user", new UserResponseDTO(user));
        return new CustomUserDetails(user);
    }
}
