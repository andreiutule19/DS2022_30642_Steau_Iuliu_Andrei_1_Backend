package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.ClientDTO;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserBuilder {

    public static ClientDTO toUserDTO(User user) {
        return new ClientDTO(user.getEmail(), user.getFullName(), user.getRole(),user.getDevices());
    }

    public static List<ClientDTO> toUserDTOList(List<User> users) {
        return users.stream().map(UserBuilder::toUserDTO).collect(Collectors.toList());
    }

}
