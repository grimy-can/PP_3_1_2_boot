package preproject.boot.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import preproject.boot.model.User;
import preproject.boot.service.UserService;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/users")
    public String getUsers(ModelMap model, @RequestParam(value = "count", required = false ) String number) {
        model.addAttribute("users", service.getUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUser(Model model, @PathVariable("id") int id) {
        Optional<User> optionalUser = service.getUser(id);
        if (!optionalUser.isPresent()) {
            return "error";
        }
        model.addAttribute("user", optionalUser.get());
        return "show";
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "new";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/edit_user")
    public String getEditUserPage(@RequestParam(value = "id") String id, Model model) {

        Optional<User> optionalUser = service.getUser(Integer.valueOf(id));
        if (!optionalUser.isPresent()) {
            return "error";
        }
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("user", optionalUser.get());
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/users";
    }


    @PostMapping("/del_user")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Optional<User> optionalUser = service.getUser(Integer.parseInt(id));
        service.deleteUser(optionalUser.get());
        return "redirect:/users";
    }

}
