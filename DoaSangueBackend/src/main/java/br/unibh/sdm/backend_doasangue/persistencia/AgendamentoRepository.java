package br.unibh.sdm.backend_doasangue.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.unibh.sdm.backend_doasangue.entidades.Agendamento;

public interface AgendamentoRepository extends CrudRepository <Agendamento, Long> {
   
    Iterable<Agendamento> findByData(Date data);

    List<Agendamento> findByStatus(String string);   

    
    
}
