package com.elastech.LadyTech.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elastech.LadyTech.models.Administrator;
import com.elastech.LadyTech.models.Called;
import com.elastech.LadyTech.models.Technical;
import com.elastech.LadyTech.models.User;
import com.elastech.LadyTech.repositories.AdministratorRepository;
import com.elastech.LadyTech.repositories.CalledRepository;
import com.elastech.LadyTech.repositories.TechnicalRepository;
import com.elastech.LadyTech.repositories.UserRepository;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CalledRepository calledRepository;

	@GetMapping("/create-technician")
	public String createTechnician(Model model) {
		model.addAttribute("technical", new Technical());
		return "administrador-cadastro-tecnico";
	}

	@PostMapping("/create-technician")
	public String createTechnician(@ModelAttribute("technical") Technical technical, Model model) {
		Long idAdministrator = technical.getAdministrator().getIdAdministrator();

		Optional<Administrator> admin = administratorRepository.findById(idAdministrator);

		if (admin.isEmpty()) {
			model.addAttribute("error", "Administrador não encontrado.");

			return "redirect:/administrator/consult-users";

		}

		Administrator administrator = admin.get();
		technical.setAdministrator(administrator);

		if (technicalRepository.existsByUserName(technical.getUserName())) {
			model.addAttribute("error", "Já existe um técnico cadastrado com esse usuário.");

			return "redirect:/administrator/consult-users";

		} else {
			technical.setAdministratorName(administrator.getName());
			technical.setActive(true);
			technicalRepository.save(technical);
		}

		model.addAttribute("technical", new Technical());
		model.addAttribute("success", "Técnico cadastrado com sucesso!");

		return "redirect:/administrator/consult-users";
	}

	@GetMapping("/create-user")
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		return "administrador-cadastro-usuario";
	}

	@PostMapping("/create-user")
	public String createUser(@ModelAttribute("user") User user, Model model) {

		Long idAdministrator = user.getAdministrator().getIdAdministrator();

		Optional<Administrator> admin = administratorRepository.findById(idAdministrator);

		if (admin.isEmpty()) {
			model.addAttribute("error", "Administrador não encontrado.");
			return "error-page";
		}

		Administrator administrator = admin.get();
		user.setAdministrator(administrator);

		if (userRepository.existsByUserName(user.getUserName())) {
			model.addAttribute("error", "Já existe um cliente cadastrado com esse usuário.");
			return "error-page";
		} else {

			user.setAdministratorName(administrator.getName());
			user.setActive(true);
			userRepository.save(user);
		}

		model.addAttribute("success", "Cliente cadastrado com sucesso!");
		return "redirect:/administrator/consult-users";
	}

	@GetMapping("/consult-users")
	public String consultUsers(Model model) {
		List<User> users = userRepository.findAll();
		List<Technical> technicians = technicalRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("technicians", technicians);

		return "administrador-usuarios";
	}

	@GetMapping("/update-user/{idUser}")
	public String updateUser(Model model) {
		List<User> users = userRepository.findAll();
		List<Technical> technicians = technicalRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("technicians", technicians);

		return "administrador-usuarios";
	}

	@PutMapping("/update-user/{idUser}")
	public String updateUser(@PathVariable Long idUser, @ModelAttribute User user, Model model) {
		Optional<User> searchUser = userRepository.findById(idUser);

		if (searchUser.isEmpty()) {
			return "redirect:/administrator/consult-users";
		} else {

			User updateUser = searchUser.get();
			updateUser.setName(user.getName());
			updateUser.setPhone(user.getPhone());
			updateUser.setUserName(user.getUserName());
			updateUser.setEmail(user.getEmail());

			updateUser.setAdministrator(updateUser.getAdministrator());
			updateUser.setAdministratorName(updateUser.getAdministratorName());
			updateUser.setRegisterDate(updateUser.getRegisterDate());

			userRepository.save(updateUser);

			return "redirect:/administrator/consult-users";
		}
	}

	@GetMapping("/update-technician/{idTechnical}")
	public String updateTechnicalId(Model model) {
		List<User> users = userRepository.findAll();
		List<Technical> technicians = technicalRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("technicians", technicians);

		return "administrador-usuarios";
	}

	@PutMapping("/update-technician/{idTechnical}")
	public String updateTechnicalId(@PathVariable Long idTechnical, @ModelAttribute Technical technical, Model model) {

		Optional<Technical> searchTechnical = technicalRepository.findById(idTechnical);

		if (searchTechnical.isEmpty()) {
			return "redirect:/administrator/consult-users";

		} else {

			Technical updateTechnical = searchTechnical.get();
			updateTechnical.setName(technical.getName());
			updateTechnical.setPhone(technical.getPhone());
			updateTechnical.setUserName(technical.getUserName());
			updateTechnical.setEmail(technical.getEmail());

			updateTechnical.setAdministrator(updateTechnical.getAdministrator());
			updateTechnical.setAdministratorName(updateTechnical.getAdministratorName());

			technicalRepository.save(updateTechnical);

		}

		return "redirect:/administrator/consult-users";
	}

	@PostMapping("/update-status-technical")
	@ResponseBody
	public void alterarStatus(@RequestParam Long idTechnical) {
		Technical technical = technicalRepository.findById(idTechnical).orElse(null);

		if (technical != null) {
			technical.setActive(!technical.isActive());
			technicalRepository.save(technical);
		}
	}

	@PostMapping("/update-status-user")
	@ResponseBody
	public void updateStatusUser(@RequestParam Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);

		if (user != null) {
			user.setActive(!user.isActive());
			userRepository.save(user);
		}
	}

	@GetMapping("/graphic-administrator")
	public String graphicAdministrator(Model model) {
		List<Called> allCalled = calledRepository.findAll();

		int totalCalled = allCalled.size();
		List<String> technicianNames = new ArrayList<>();
		List<Integer> countCalled = new ArrayList<>();

		for (Called called : allCalled) {
			String technicianName = called.getTechnicalName();

			if (technicianName == null || technicianName.isEmpty()) {
				technicianName = "Aguardando Técnico";
			}

			int index = technicianNames.indexOf(technicianName);

			if (index == -1) {
				technicianNames.add(technicianName);
				countCalled.add(1);
			} else {
				countCalled.set(index, countCalled.get(index) + 1);
			}
		}

		List<Double> percentageCalledPerTechnician = new ArrayList<>();
		if (totalCalled != 0) {
			for (int i = 0; i < countCalled.size(); i++) {
				double percentage = (double) countCalled.get(i) / totalCalled * 100;
				percentageCalledPerTechnician.add(percentage);
			}
		}

		int countCalledHighPriority = 0;
		int countCalledMediumPriority = 0;
		int countCalledLowPriority = 0;

		for (Called called : allCalled) {
			String priority = called.getPriority();

			if (priority != null) {
				if (priority.equalsIgnoreCase("alto")) {
					countCalledHighPriority++;
				} else if (priority.equalsIgnoreCase("media")) {
					countCalledMediumPriority++;
				} else if (priority.equalsIgnoreCase("baixo")) {
					countCalledLowPriority++;
				}
			}
		}

		int totalPriorityCount = countCalledHighPriority + countCalledMediumPriority + countCalledLowPriority;
		double percentageHighPriority = 0;
		double percentageMediumPriority = 0;
		double percentageLowPriority = 0;

		if (totalPriorityCount != 0) {
			percentageHighPriority = (double) countCalledHighPriority / totalPriorityCount * 100;
			percentageMediumPriority = (double) countCalledMediumPriority / totalPriorityCount * 100;
			percentageLowPriority = (double) countCalledLowPriority / totalPriorityCount * 100;
		}

		List<String> formattedPercentageCalledPerTechnician = new ArrayList<>();
		DecimalFormat formatted = new DecimalFormat("#.##");

		for (int i = 0; i < percentageCalledPerTechnician.size(); i++) {
			String formattedPercentage = formatted.format(percentageCalledPerTechnician.get(i));
			formattedPercentageCalledPerTechnician.add(formattedPercentage);
		}

		String formattedPercentageHighPriority = formatted.format(percentageHighPriority);
		String formattedPercentageMediumPriority = formatted.format(percentageMediumPriority);
		String formattedPercentageLowPriority = formatted.format(percentageLowPriority);

		model.addAttribute("totalCalled", totalCalled);
		model.addAttribute("technicianNames", technicianNames);
		model.addAttribute("countCalled", countCalled);
		model.addAttribute("percentageCalledPerTechnician", formattedPercentageCalledPerTechnician);
		model.addAttribute("totalPriorityCount", totalPriorityCount);
		model.addAttribute("countCalledHighPriority", countCalledHighPriority);
		model.addAttribute("countCalledMediumPriority", countCalledMediumPriority);
		model.addAttribute("countCalledLowPriority", countCalledLowPriority);
		model.addAttribute("percentageHighPriority", formattedPercentageHighPriority);
		model.addAttribute("percentageMediumPriority", formattedPercentageMediumPriority);
		model.addAttribute("percentageLowPriority", formattedPercentageLowPriority);

		return "administrador-grafico";
	}

}