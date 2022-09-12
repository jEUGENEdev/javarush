package com.game.controller;

import com.game.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/players")
@RestController
public class PlayerController {
    @Autowired
    private PlayerModel playerModel;

    @GetMapping("/")
    public String get() {
        return playerModel.get();
    }
}
