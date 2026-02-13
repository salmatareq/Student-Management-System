package model;
import Enums.Role;

public class Admin extends PersonInfo {
    String loginPassword;
    static int admincuonter;

    public Admin(String name, String loginPassword) {
        super(++admincuonter, name, Role.ADMIN);
        this.loginPassword = loginPassword;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public boolean login(String password) {
        if (password.equals(loginPassword)) {
            return true;
        } else return false;
    }

    @Override
    public Role getrole() {
        return this.role;
    }

    public void setrole(Role role) {
        this.role = role;
    }


}