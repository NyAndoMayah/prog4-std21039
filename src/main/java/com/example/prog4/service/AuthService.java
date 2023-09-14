package com.example.prog4.service;

import com.example.prog4.model.Employee;
import com.example.prog4.model.Session;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.SessionRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthService {

    private EmployeeRepository usersRepository;
    private SessionRepository sessionRepository;

    public boolean login(String username, String password) {
        Employee employee = usersRepository.findByUsernameAndPassword(username, password);
        return employee != null && employee.getPassword().equals(password);
    }
    public String createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        Session session = sessionRepository.save(Session.builder()
                        .id(sessionId)
                        .username(username)
                        .build());
        return session.getId();
    }
    public boolean isLogged(HttpSession session){
        String sessionId = (String) session.getAttribute("sessionId");
        Session sessions = sessionRepository.findById(sessionId).orElse(null);
        return sessions != null;
    }
    public void invalidateSession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
