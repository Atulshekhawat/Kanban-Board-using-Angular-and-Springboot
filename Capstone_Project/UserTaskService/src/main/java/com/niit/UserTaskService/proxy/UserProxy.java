package com.niit.UserTaskService.proxy;

import com.niit.UserTaskService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserAuth-service",url="localhost:8089")
public interface UserProxy {
    @PostMapping("api/v2/save")
    public ResponseEntity<?> saveUser(@RequestBody User user);

}
