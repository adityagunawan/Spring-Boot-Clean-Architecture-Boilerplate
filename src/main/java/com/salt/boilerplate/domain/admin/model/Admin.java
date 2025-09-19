package com.salt.boilerplate.domain.admin.model;

import com.salt.boilerplate.domain.user.model.User;

public class Admin extends User {
    public Admin(String username, String password, String name, String email) {
      super(username, password, name, email);
      this.role = "ROLE_ADMIN";
    }
}
