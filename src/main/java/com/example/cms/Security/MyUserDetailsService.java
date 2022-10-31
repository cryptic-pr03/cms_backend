package com.example.cms.Security;

import com.example.cms.Constants;
import com.example.cms.Models.Staff;
import com.example.cms.Models.User;
import com.example.cms.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    Constants cnts;
    UserDAO userRepo;
    StaffDAO staffRepo;
    TypeUserDAO typeUserRepo;

    public MyUserDetailsService(UserDAO userRepo, StaffDAO staffRepo, TypeUserDAO typeUserRepo) {
        this.userRepo = userRepo;
        this.staffRepo = staffRepo;
        this.typeUserRepo = typeUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {

        try {
            User user = userRepo.getUserByEmailId(userEmailId);

            if (user != null) {
                List<Integer> roleValues = typeUserRepo.getRolesByUserId(user.getUserID());

                ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (int role : roleValues) {
                    authorities.add(new SimpleGrantedAuthority(cnts.typeUserCode.get(role)));
                }

                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
            }

        } catch (CustomException e) {
            e.printStackTrace();
        }

        try {
            Staff user = staffRepo.getStaffByEmailId(userEmailId);

            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
            }

        } catch (CustomException e) {
            e.printStackTrace();
        }

        throw new UsernameNotFoundException("User with Email Id : " + userEmailId + " not found");
    }
}
