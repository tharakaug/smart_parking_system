package org.example.userservice.repo;

import org.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
//    boolean existsByEmail(String email);
    @Query("SELECT u.u_id FROM User u")
    List<Integer> findAllIds();

    @Query("SELECT u.u_id FROM User u WHERE u.role IN ('Teacher', 'Admin')")
    List<Integer> findAllIdsByRole();

    @Query("SELECT MAX(u.u_id) FROM User u")
    Integer findMaxId();


    //    jwt
    User findByEmail(String userName);

    boolean existsByEmail(String userName);

    int deleteByEmail(String userName);

}
