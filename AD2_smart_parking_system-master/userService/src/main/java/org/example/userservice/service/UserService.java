package org.example.userservice.service;



import org.example.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    public boolean addUser(UserDTO userDTO);
    public boolean deleteUser(int u_id);
//    public List<UserDTO> updateUsers(int u_id, UserDTO userDTO);
    public boolean updateUsers(UserDTO userDTO);
    public List<UserDTO> getAllUsers();

    //jwt
    boolean saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    UserDTO getUserById(int u_id);
}
