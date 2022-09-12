package com.game.entity;

import com.game.exception.ValidationException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
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
    private int experience, level, untilNextLevel;
    private Date birthday;
    private boolean banned;

    public Player() throws ValidationException {
        birthday = new Date(System.currentTimeMillis());
        calculateCurrentLevel();
        calculateExperienceForNextLevel();
        validateData();
    }

    public Player(String name, String title, Race race, Profession profession) throws ValidationException {
        this();
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
    }

    public Player(String name, String title, Race race, Profession profession, boolean banned) throws ValidationException {
        this(name, title, race, profession);
        this.banned = banned;
    }

    private void calculateExperienceForNextLevel() {
        this.untilNextLevel = 50 * (level + 1) * (level + 2) - experience;
    }

    private void calculateCurrentLevel() {
        int lastLevel = this.level;
        this.level = (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
        if (lastLevel != level) calculateExperienceForNextLevel();
    }

    private void validateData() throws ValidationException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthday);
        if (this.name.length() > 12 || this.title.length() > 30 || this.id < 0 || this.experience > 10_000_000 ||
                !(calendar.get(Calendar.YEAR) >= 2000 && calendar.get(Calendar.YEAR) <= 3000)) {
            throw new ValidationException("Incorrect value");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(int untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
