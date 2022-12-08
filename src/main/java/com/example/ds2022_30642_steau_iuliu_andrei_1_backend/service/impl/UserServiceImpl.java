package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.impl;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder.DeviceBuilder;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.builder.UserBuilder;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.dto.*;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.Device;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.entity.User;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.exception.ResourceNotFoundException;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.DeviceRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.repository.UserRepository;
import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.service.sketch.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public ClientDTO insert(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return UserBuilder.toUserDTO(user);
    }

    @Override
    public ClientDTO delete(ClientDTO clientDTO) {
        Optional<User> user = userRepository.findUserByEmail(clientDTO.getEmail());
        if(!user.isPresent()){
            throw  new ResourceNotFoundException("Invalid email-user "+ clientDTO.getEmail());
        }
        userRepository.delete(user.get());
        return clientDTO;
    }

    @Override
    public UpdateUserDTO update(UpdateUserDTO updateUserDTO) {
        Optional<User> user = userRepository.findUserByEmail(updateUserDTO.getOldEmail());
        if(!user.isPresent()){
            throw new ResourceNotFoundException("This user doesnt exist "+updateUserDTO.getOldEmail());
        }
        user.get().setRole(updateUserDTO.getRole());
        user.get().setFullName(updateUserDTO.getFullName());
        user.get().setEmail(updateUserDTO.getNewEmail());
        userRepository.save(user.get());
        return updateUserDTO;
    }

    @Override
    public ChangePassDTO changePassword(ChangePassDTO user) {
        Optional<User> searchedUser = userRepository.findUserByEmail(user.getEmail());
        if(!searchedUser.isPresent()){
            throw new ResourceNotFoundException("This user doesnt exist "+user.getEmail());
        }
        if(new BCryptPasswordEncoder().matches(user.getOldPassword(),searchedUser.get().getPassword())){
            throw new ResourceNotFoundException("Invalid password");
        }

        if(user.getNewPassword().equals(user.getConfirmNewPassword())){
            throw new ResourceNotFoundException("Incorrect password");
        }

        searchedUser.get().setPassword(new BCryptPasswordEncoder().encode(user.getNewPassword()));
        userRepository.save(searchedUser.get());
        return user;
    }

    @Override
    public List<ClientDTO> findAll() {
        return UserBuilder.toUserDTOList(userRepository.findAll());
    }


    @Override
    @Transactional
    public ClientDTO logIn(AuthDTO authDTO) {
        Optional<User> user = userRepository.findUserByEmail(authDTO.getEmail());
        if(!user.isPresent()){
             throw new ResourceNotFoundException("Invalid email "+ authDTO.getEmail());
        }
        else{
            if(!(new BCryptPasswordEncoder().matches(authDTO.getPassword(), user.get().getPassword()))){
                throw new ResourceNotFoundException("Invalid email password");
            }
        }
        //authDTO.setToken(jwtTokenUtil.generateToken(authDTO));

        return UserBuilder.toUserDTO(user.get());
    }



    @Override
    public Device associateDevice(String email, DeviceDTO device) {
        Optional<User> user= userRepository.findUserByEmail(email);
        if(!user.isPresent()) {
            throw new ResourceNotFoundException("Invalid email " + email);
        }
        if(device.getDeviceId() == 0){
            throw new ResourceNotFoundException("Invalid device ");
        }
        Optional<Device> searchedDevice = deviceRepository.findDeviceByDeviceId(device.getDeviceId());
        if(!searchedDevice.isPresent()){
            throw new ResourceNotFoundException("Invalid device ");
        }
        user.get().getDevices().add(searchedDevice.get());
        userRepository.save(user.get());
        return searchedDevice.get();
    }




    @Override
    public List<DeviceDTO> getAssociatedDevices(String email) {
        Optional<User> user= userRepository.findUserByEmail(email);
        if(!user.isPresent()) {
            throw new ResourceNotFoundException("Invalid email "+email);
        }
        return DeviceBuilder.toDeviceDTOList(user.get().getDevices());
    }


}
