package edu.espoch.ejemploAppMedicSoft.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.espoch.ejemploAppMedicSoft.entities.EquipoMed;


public interface EquipoMedRepo extends CrudRepository <EquipoMed, Long>{
	List<EquipoMed> findByNombre(String nombre);
	List<EquipoMed> findByCodigo(String codigo);
}

