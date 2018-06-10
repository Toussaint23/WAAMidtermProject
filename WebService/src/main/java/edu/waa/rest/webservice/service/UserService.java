package edu.waa.rest.webservice.service;


import edu.waa.rest.webservice.domain.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void setUser(User user, String role, String mail);
    public void saveUser(User user);
}
