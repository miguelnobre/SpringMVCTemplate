package com.mvc.template.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    private static Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/{path}")
    @ApiOperation(value = "Say Hello", notes = "Say a beautiful Hello Message")
    public ResponseEntity sayHello(@PathVariable String path, @RequestParam String name) {
        log.info("[sayHello()] Request Params: Path: {} | Name: {}", path, name);
        return ResponseEntity.ok("Hello " + name + " from /" + path);
    }
}
