package pl.Dominik.controllers;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.Dominik.dao.UserDao;
import pl.Dominik.entity.User;
import pl.Dominik.app.UserEntityDetails;
import pl.Dominik.repo.UserRepo;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepo repo;
    private final UserDao userDao;


    public UserController(UserRepo repo, UserDao userDao) {
        this.repo = repo;
        this.userDao = userDao;
    }

//logowanie Spring security...

    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        UserEntityDetails details = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = details.getUsername();
        model.addAttribute("username", userName);
        return "userPanel";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }


    @PostMapping("/save")
    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        try {
            userDao.add(user);
        } catch (Exception e) {
            return "addUser";
        }
        return "redirect:/user/homePage";
    }
    @GetMapping("/edit")
    public String editUser(Model model){
        User user = new User();
        UserEntityDetails details = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = details.getUsername();
        user.setId(repo.findByUsernameIgnoreCase(userName).get().getId());
        user.setActive(repo.findByUsernameIgnoreCase(userName).get().isActive());
        user.setLastName(repo.findByUsernameIgnoreCase(userName).get().getLastName());
        user.setName(repo.findByUsernameIgnoreCase(userName).get().getName());
        user.setPassword(repo.findByUsernameIgnoreCase(userName).get().getPassword());
        user.setUsername(repo.findByUsernameIgnoreCase(userName).get().getUsername());
        user.setRoles(repo.findByUsernameIgnoreCase(userName).get().getRoles());
        model.addAttribute("user", user);
        return "userEdit";
    }
    @PostMapping("/editUser")
    public String saveEditUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repo.save(user);
        return "redirect:/user/userPanel";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model){
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "adminPanel";
    }


    @RequestMapping("/userPanel")
    public String userPanel() {
        return "userPanel";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }



}
