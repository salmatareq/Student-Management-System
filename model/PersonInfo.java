package model;

import Enums.Role;

public abstract class  PersonInfo {

    public String name;
    public int id;
    Role role;

    public PersonInfo(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public abstract int getId();


    public abstract Role getrole();
}
