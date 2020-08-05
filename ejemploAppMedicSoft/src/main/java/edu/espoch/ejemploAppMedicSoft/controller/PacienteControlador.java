package edu.espoch.ejemploAppMedicSoft.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.espoch.ejemploAppMedicSoft.entities.Paciente;
import edu.espoch.ejemploAppMedicSoft.repository.PacienteRepo;
import edu.espoch.ejemploAppMedicSoft.service.PictureService;


@Controller
@RequestMapping("/medicsoft")
public class PacienteControlador {
	
	@Autowired
	private PacienteRepo repo;
	
	@Autowired
	PictureService picService;
	
	@RequestMapping("")
	public String index() {
		return "index";
	}
	
	/*OJOOOOOOOOOOOOOOOOOOO*/
	
	@GetMapping("/add_paciente")
	public String showSignUpForm(Paciente paciente) {
		return "add_paciente";
	}
	
	/*-----------------*/
	
	@GetMapping("/list_p")
	public String showRecipes(Model model) {
		model.addAttribute("pacientes", repo.findAll());
		return "list_pacientes";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/sc")
	public String showPacientes() {
		return "soundcloud.html";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping("/private")
	
	public String showPrivate(Model model) {
		model.addAttribute("medicsoft", repo.findAll());
		return "list_pacientes";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/add")
	public String addPaciente(Paciente paciente, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		System.out.println(paciente.getApellido());
		if(result.hasErrors()) {
			System.out.println("error");
			return "add_paciente";
		}
		
		UUID idPic=UUID.randomUUID();
		picService.uploadPicture(file, idPic);
		paciente.setFoto(idPic);
		repo.save(paciente);
		return "redirect:list_p";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Paciente paciente = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de paciente no valido: " + id));
		model.addAttribute("paciente", paciente);
		return "update_paciente";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/update/{id}")
	public String updatePaciente(@PathVariable("id") Long id, Paciente paciente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			paciente.setId(id);
			return "update_paciente";
		}
		
		repo.save(paciente);
		return "redirect:/medicsoft/list";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("delete/{id}")
	public String deletePaciente(@PathVariable("id") Long id, Model model) {
		Paciente paciente = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de paciente no valido:" + id));
		repo.delete(paciente);
		model.addAttribute("medicsoft", repo.findAll());
		return "list_pacientes";
	}
}