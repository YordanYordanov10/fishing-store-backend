package com.fishingstore.Web;

import com.fishingstore.User.Model.User;
import com.fishingstore.User.Service.UserService;
import com.fishingstore.Web.Dto.UserResponse;
import com.fishingstore.Web.Mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("me");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponseList = DtoMapper.toUserResponseList(users);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {

        User user = userService.getUserById(id);
        UserResponse userResponse = DtoMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
