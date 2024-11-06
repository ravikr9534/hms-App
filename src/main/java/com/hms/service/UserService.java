package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;
   private LoginDto loginDto;
   private JWTService jwtService;
    // Constructor injection for both the repository and the mapper
    public UserService(AppUserRepository appUserRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.loginDto=loginDto;
    }

    // Map DTO to Entity
    private AppUser mapToEntity(AppUserDto userDto) {
        return modelMapper.map(userDto, AppUser.class);
    }

    // Map Entity to DTO
    private AppUserDto mapToDto(AppUser savedUser) {
        return modelMapper.map(savedUser, AppUserDto.class);
    }

    // Get user by username or email, and map it to AppUserDto using the mapper
    public AppUserDto getUser(AppUserDto userDto) {
        // Map DTO to Entity
        AppUser appUser = mapToEntity(userDto);

        // Check if username is already present
        Optional<AppUser> opUser = appUserRepository.findByUsername(appUser.getUsername());
        if (opUser.isPresent()) {
            throw new IllegalArgumentException("Username already present");
        }

        // Check if email is already present
        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUser.getEmail());
        if (opEmail.isPresent()) {
            throw new IllegalArgumentException("Email already present");
        }

        // Encrypt the password
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(5));

        // Set the encrypted password into the AppUser entity
        appUser.setPassword(encryptedPassword);
        appUser.setRole("ROLE_USER");

        // Save the user entity and return the DTO
        AppUser savedUser = appUserRepository.save(appUser);
        return mapToDto(savedUser);
    }
    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(dto.getUsername());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
            if (BCrypt.checkpw(dto.getPassword(), appUser.getPassword())) {
                String token = jwtService.generateToken(appUser.getUsername());

                return token;
            } else {
                return null;
            }
          //  return null;
        }
        return null;
    }
    public AppUserDto getProperty(AppUserDto userDto) {
        // Map DTO to Entity
        AppUser appUser = mapToEntity(userDto);

        // Check if username is already present
        Optional<AppUser> opUser = appUserRepository.findByUsername(appUser.getUsername());
        if (opUser.isPresent()) {
            throw new IllegalArgumentException("Username already present");
        }

        // Check if email is already present
        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUser.getEmail());
        if (opEmail.isPresent()) {
            throw new IllegalArgumentException("Email already present");
        }

        // Encrypt the password
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(5));

        // Set the encrypted password into the AppUser entity
        appUser.setPassword(encryptedPassword);
        appUser.setRole("ROLE_OWNER");

        // Save the user entity and return the DTO
        AppUser savedUser = appUserRepository.save(appUser);
        return mapToDto(savedUser);
    }
}