package edu.espoch.ejemploAppMedicSoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.espoch.ejemploAppMedicSoft.entities.EquipoMed;
import edu.espoch.ejemploAppMedicSoft.entities.Medicamento;
import edu.espoch.ejemploAppMedicSoft.repository.MedicamentoRepo;



@Controller
@RequestMapping("/medicsoft")
public class MedicamentoControlador {
	
	@Autowired
	private MedicamentoRepo repome;
	
	@GetMapping("/add_medicamento")
	public String showSignUpFormMedi(Medicamento medi) {
		return "add_medicamento";
	}
	
	/*-----------------*/
	
	@GetMapping("/list_m")
	public String showEquips(Model model) {
		model.addAttribute("medicamentos", repome.findAll());
		return "list_medicamento";
	}
	
	

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/addmedi")
	public String addEquipo(Medicamento medi, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			return "add_equipo";
		}
		repome.save(medi);
		return "redirect:list_m";
	}
	

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/editme/{id}")
	public String showUpdateFormEq(@PathVariable("id") Long id, Model model) {
		Medicamento medi = repome.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de medicamento no valido: " + id));
		model.addAttribute("medi", medi);
		return "update_medicamento";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/updateme/{id}")
	public String updateEquipo(@PathVariable("id") Long id, Medicamento medi, BindingResult result, Model model) {
		if(result.hasErrors()) {
			medi.setId(id);
			return "update_medicamento";
		}
		
		repome.save(medi);
		return "redirect:/medicsoft/list_m";
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("deleteme/{id}")
	public String deleteEquipo(@PathVariable("id") Long id, Model model) {
		Medicamento medi = repome.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de medicamento no valido:" + id));
		repome.delete(medi);
		model.addAttribute("medicsoft", repome.findAll());
		return "redirect:/medicsoft/list_m";
	} 


}
