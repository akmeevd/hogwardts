package ru.hogwardts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Profile("default")
public class InfoController {

    @Value("${server.port}")
    int port;

    @GetMapping("/getPort")
    public int getPort() {
        return port;
    }
}
