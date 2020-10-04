package com.task.ninepixels.repository;

import com.task.ninepixels.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<DAOUser, Long> {

    DAOUser findByUsername(String username);

}
