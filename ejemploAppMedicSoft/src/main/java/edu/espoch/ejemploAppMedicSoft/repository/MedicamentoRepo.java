package edu.espoch.ejemploAppMedicSoft.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.espoch.ejemploAppMedicSoft.entities.Medicamento;


public interface MedicamentoRepo extends CrudRepository <Medicamento, Long> {
	
	List<Medicamento> findByNombre(String nombre);
	List<Medicamento> findByCodigo(String codigo);

}
