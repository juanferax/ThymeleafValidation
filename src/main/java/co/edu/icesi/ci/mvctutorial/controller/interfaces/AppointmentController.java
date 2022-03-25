package co.edu.icesi.ci.mvctutorial.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.Appointment;

public interface AppointmentController {

	public String addApp(Model model);

	public String deleteApp(@PathVariable("id") long id);

	public String indexApp(Model model);

	public String saveApp(@RequestParam(value = "action", required = true) String action, Appointment app, Model model);

	public String showUpdateApp(@PathVariable("id") long id, Model model);

	public String updateApp(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			Appointment app);
}
