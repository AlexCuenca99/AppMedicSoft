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
import edu.espoch.ejemploAppMedicSoft.repository.EquipoMedRepo;


@Controller
@RequestMapping("/medicsoft")
public class EquipoControlador {
	
	
	@Autowired
	private EquipoMedRepo repoeq;
	
	//Equipo Medico OJOOOOOOOOOOOOOOOOOOOOOOO
	
	/*OJOOOOOOOOOOOOOOOOOOO*/
		
		@GetMapping("/add_equipo")
		public String showSignUpFormEquip(EquipoMed equipomed) {
			return "add_equipo";
		}
		
		/*-----------------*/
		
		@GetMapping("/list_e")
		public String showEquips(Model model) {
			model.addAttribute("equipos", repoeq.findAll());
			return "list_equipo";
		}
		
		

		@PreAuthorize("hasAuthority('admin')")
		@PostMapping("/addeq")
		public String addEquipo(EquipoMed equipo, BindingResult result, Model model) {
			
			if(result.hasErrors()) {
				
				return "add_equipo";
			}
			repoeq.save(equipo);
			return "redirect:list_e";
		}
		
	
		@PreAuthorize("hasAuthority('admin')")
		@GetMapping("/editeq/{id}")
		public String showUpdateFormEq(@PathVariable("id") Long id, Model model) {
			EquipoMed equipo = repoeq.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de equipo no valido: " + id));
			model.addAttribute("equipo", equipo);
			return "update_equipo";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@PostMapping("/updateeq/{id}")
		public String updateEquipo(@PathVariable("id") Long id, EquipoMed equipo, BindingResult result, Model model) {
			if(result.hasErrors()) {
				equipo.setId(id);
				return "update_equipo";
			}
			
			repoeq.save(equipo);
			return "redirect:/medicsoft/list_e";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@GetMapping("deleteeq/{id}")
		public String deleteEquipo(@PathVariable("id") Long id, Model model) {
			EquipoMed equipo = repoeq.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de equipo no valido:" + id));
			repoeq.delete(equipo);
			model.addAttribute("medicsoft", repoeq.findAll());
			return "redirect:/medicsoft/list_e";
		} 

}
