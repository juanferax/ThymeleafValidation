package co.edu.icesi.ci.mvctutorial.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.User;

public interface UserController {

	public String addUser(Model model, @ModelAttribute("user") User user);

	public String deleteUser(@PathVariable("id") long id, Model model);

	public String indexUser(Model model);

	public String saveUser(User user, @RequestParam(value = "action", required = true) String action);

	public String showUpdateForm(@PathVariable("id") long id, Model model);

	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, User user, Model model);
}
