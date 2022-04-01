package co.edu.icesi.ci.thymeval.controller.implementation;

import java.util.Optional;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.controller.interfaces.UserController;
import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UserControllerImpl implements UserController {

	UserServiceImpl userService;

	@Autowired
	public UserControllerImpl(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "users/add-user-1";
	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@PostMapping("/users/add")
	public String saveUserStepOne(@Validated(User.ValidationStepOne.class) @ModelAttribute User user, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				return "users/add-user-1";
			}
			model.addAttribute("user", user);
			model.addAttribute("genders", userService.getGenders());
			model.addAttribute("types", userService.getTypes());
			return "users/add-user-2";
		}
		return "redirect:/users/";
	}
	
	@PostMapping("/users/add1")
	public String saveUserStepTwo(@Validated({User.ValidationStepTwo.class}) @ModelAttribute User user, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				return "users/add-user-2";
			}
			userService.save(user);
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		model.addAttribute("currentPassword", "");
		model.addAttribute("match", true);
		model.addAttribute("blank", false);
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated(User.ValidationEdit.class) @ModelAttribute User user,
			BindingResult bindingResult, @ModelAttribute("currentPassword") String currentPassword, Model model) {
		if (action != null && !action.equals("Cancel")) {
			//log.info("The password is: " + currentPassword);
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				model.addAttribute("currentPassword", "");
				model.addAttribute("match", true);
				model.addAttribute("blank", false);
				return "users/update-user";
			}
			if (user.getPassword().equals("") && currentPassword.equals("")) {
				user.setPassword(userService.findById(user.getId()).get().getPassword());
				userService.save(user);
				return "redirect:/users/";
			}
			if (userService.confirmPassword(user.getId(), currentPassword)) {
				if (user.getPassword() == "") {
					model.addAttribute("user", user);
					model.addAttribute("genders", userService.getGenders());
					model.addAttribute("types", userService.getTypes());
					model.addAttribute("currentPassword", "");
					model.addAttribute("match", true);
					model.addAttribute("blank", true);
					return "users/update-user";
				}
				userService.save(user);
			} else {
				//log.info("Password do not match");
				model.addAttribute("user", user);
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				model.addAttribute("currentPassword", "");
				model.addAttribute("match", false);
				model.addAttribute("blank", false);
				return "users/update-user";
			}
			
			model.addAttribute("users", userService.findAll());
		}
		return "redirect:/users/";
	}
}
