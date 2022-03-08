package com.company;

public class Ability {

    Integer Dmg = null;
    Boolean Stun = null;
    Boolean Dodge = null;
    Integer Cd = null;

    // Setters
    public void setDmg(Integer dmg) {
        Dmg = dmg;
    }
    public void setStun(Boolean stun) {
        Stun = stun;
    }
    public void setDodge(Boolean dodge) {
        Dodge = dodge;
    }
    public void setCd(Integer cd) {
        Cd = cd;
    }


    // Getters
    public Integer getDmg() {
        return Dmg;
    }
    public Boolean getStun() {
        return Stun;
    }
    public Boolean getDodge() {
        return Dodge;
    }
    public Integer getCd() {
        return Cd;
    }

    @Override
    public String toString() {
        return  " Dmg:" + Dmg +
                " Stun:" + Stun +
                " Dodge:" + Dodge +
                " Cd:" + Cd;
    }
}
