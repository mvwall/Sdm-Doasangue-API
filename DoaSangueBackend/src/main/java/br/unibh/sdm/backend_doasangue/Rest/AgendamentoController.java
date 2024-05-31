package br.unibh.sdm.backend_doasangue.Rest;


import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.backend_doasangue.entidades.Agendamento;
import br.unibh.sdm.backend_doasangue.negocio.AgendamentoService;

/**
 * Classe contendo as definições de serviços REST/JSON para Agendamento
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping(value = "")
    public List<Agendamento> getAgendamentos() {
        return agendamentoService.getAgendamentos();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getAgendamentoById(@PathVariable Long id) {
        try {
            Agendamento agendamento = agendamentoService.getAgendamentoById(id);
            return ResponseEntity.ok(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("errorMessage", e.getMessage()));
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoService.saveAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoDetails) {
        try {
            Agendamento updatedAgendamento = agendamentoService.updateAgendamento(id, agendamentoDetails);
            return ResponseEntity.ok(updatedAgendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("errorMessage", e.getMessage()));
        }
    }

    

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable Long id) {
        try {
            agendamentoService.deleteAgendamento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    Collections.singletonMap("message", "Agendamento excluído com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Collections.singletonMap("errorMessage", e.getMessage()));
        }
    }
}
