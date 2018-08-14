package com.authorization.service.server.web.controller;

import com.authorization.service.server.model.User;
import com.authorization.service.server.web.request.*;
import com.authorization.service.server.web.service.AuthorizationBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DataRestController {

    private final AuthorizationBusinessService authorizationBusinessService;

    @Autowired
    public DataRestController(AuthorizationBusinessService authorizationBusinessService) {
        this.authorizationBusinessService = authorizationBusinessService;
    }

    @PreAuthorize("authentication.name == #username || hasRole('ROLE_ADMIN')")
    @GetMapping("/one")
    public ResponseEntity<User> findOne(@RequestParam(value = "username") String username) {
        return authorizationBusinessService.findOne(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return authorizationBusinessService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<User> post(@RequestBody UserCreateRequest request) {
        return authorizationBusinessService.post(request);
    }

    @PreAuthorize("authentication.name == #request.username || hasRole('ROLE_ADMIN')")
    @PutMapping("/update-password")
    public ResponseEntity<User> updatePassword(@RequestBody UserUpdatePasswordRequest request) {
        return authorizationBusinessService.updatePassword(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/add-role")
    public ResponseEntity<User> addRole(@RequestBody UserAddRoleRequest request) {
        return authorizationBusinessService.addRole(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/remove-role")
    public ResponseEntity<User> removeRole(@RequestBody UserRemoveRoleRequest request) {
        return authorizationBusinessService.removeRole(request);
    }

    @PreAuthorize("authentication.name == #request.username || hasRole('ROLE_ADMIN')")
    @DeleteMapping("/remove")
    public ResponseEntity<User> remove(@RequestBody UserRemoveRequest request) {
        return authorizationBusinessService.remove(request);
    }
}
