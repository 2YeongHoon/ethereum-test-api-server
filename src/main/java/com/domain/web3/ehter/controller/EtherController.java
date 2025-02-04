package com.domain.web3.ehter.controller;

import com.domain.web3.ehter.service.EtherService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EtherController {

    private final EtherService etherService;

    @GetMapping
    public ResponseEntity<Void> getLatestBlockNumber() throws IOException {
        etherService.getLatestBlockNumber();
        return ResponseEntity.ok().build();
    }


}
