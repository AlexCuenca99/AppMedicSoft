package edu.espoch.ejemploAppMedicSoft.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.espoch.ejemploAppMedicSoft.entities.Paciente;


public interface PacienteRepo extends CrudRepository <Paciente,Long>{
	
	List<Paciente> findByNombre(String nombre);
	List<Paciente> findByCI(String CI);

}
