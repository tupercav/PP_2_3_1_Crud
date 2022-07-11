package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("userList", userDao.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}/edit")
    public String editUser (Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDao.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser (@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDao.createUser(user);
        return "redirect:/users";
    }

}
