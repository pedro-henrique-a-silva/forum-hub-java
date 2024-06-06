package com.forum.hub.service;

import com.forum.hub.entity.User;
import com.forum.hub.repository.UserRepository;
import com.forum.hub.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(
      UserRepository userRepository
  ) {
    this.userRepository = userRepository;
  }

  public User findUserByName(String nameAutor) {

    return userRepository.findByNome(nameAutor)
        .orElseThrow(UserNotFoundException::new);
  }
}
