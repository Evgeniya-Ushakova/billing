package com.evg.billing.service.user;

import com.evg.billing.dto.user.UserResponse;
import com.evg.billing.dto.user.UserUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", path = "/user")
public interface UserClient {

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable Long userId, @RequestHeader("x-auth-token") String token);

}
