package com.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private int experience, level = 1, untilNextLevel = 300;
    private Date birthday;
    private boolean banned;

    public Player() {
    }

    public Player(String name, String title, Race race, Profession profession) {
    }

    private void calculateExperienceForNextLevel() {
        this.untilNextLevel = 50 * (level + 1) * (level + 2) - experience;
    }

    private void calculateCurrentLevel() {
        this.level = (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
    }
}
