package com.hms.controller;

import com.hms.payload.AppUserDto;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    private AppUserDto userDto;



    public UserController(UserService userService,AppUserDto userDto) {
        this.userService = userService;
        this.userDto = userDto;
    }
    @PostMapping("/signup")
  public ResponseEntity<?>createUser(@RequestBody AppUserDto user){

       AppUserDto Dto= userService.getUser(user);
       return new ResponseEntity<>(Dto, HttpStatus.CREATED);


  }
  @PostMapping("/login")
    public ResponseEntity<?>login(
        @RequestBody LoginDto dto
  ){
      String token= userService.verifyLogin(dto);
       if(token!=null){
           TokenDto tokenDto=new TokenDto();
           tokenDto.setToken(token);
           tokenDto.setType("JWT");
           return new ResponseEntity<>(tokenDto,HttpStatus.OK);
       }else{
           return new ResponseEntity<>("user/ password wrong",HttpStatus.FORBIDDEN);
  }
}
    @PostMapping("/signup-property-owner")
    public ResponseEntity<?>createPropertyOwner(@RequestBody AppUserDto user){

        AppUserDto Dto= userService.getProperty(user);
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);


    }
}




