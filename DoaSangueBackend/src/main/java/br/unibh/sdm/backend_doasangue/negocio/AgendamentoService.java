package br.unibh.sdm.backend_doasangue.negocio;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_doasangue.entidades.Agendamento;
import br.unibh.sdm.backend_doasangue.persistencia.AgendamentoRepository;

/**
 * Classe contendo a lógica de negócio para Agendamento
 */
@Service
public class AgendamentoService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final AgendamentoRepository agendamentoRepo;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepo = agendamentoRepository;
    }

    public List<Agendamento> getAgendamentos() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os objetos de agendamento");
        }
        Iterable<Agendamento> lista = this.agendamentoRepo.findAll();
        return IteratorUtils.toList(lista.iterator());
    }

    public Agendamento getAgendamentoById(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando Agendamento com o código {}", id);
        }
        Optional<Agendamento> retorno = this.agendamentoRepo.findById(id);
        if (!retorno.isPresent()) {
            throw new RuntimeException("Agendamento com o id " + id + " não encontrado");
        }
        return retorno.get();
    }

    public Agendamento updateAgendamento(Long id, Agendamento agendamentoDetails) {
        if (logger.isInfoEnabled()) {
            logger.info("Atualizando Agendamento com id {}", id);
        }
        Optional<Agendamento> optionalAgendamento = agendamentoRepo.findById(id);
        if (optionalAgendamento.isPresent()) {
            Agendamento agendamento = optionalAgendamento.get();
            agendamento.setIdDoador(agendamentoDetails.getIdDoador());
            agendamento.setUnidadeSaude(agendamentoDetails.getUnidadeSaude());
            agendamento.setData(agendamentoDetails.getData());
            agendamento.setStatus(agendamentoDetails.getStatus());
            return this.agendamentoRepo.save(agendamento);
        } else {
            throw new RuntimeException("Agendamento com o id " + id + " não encontrado");
        }
    }

    public Agendamento saveAgendamento(Agendamento agendamento) {
        if (logger.isInfoEnabled()) {
            logger.info("Salvando Agendamento com os detalhes {}", agendamento.toString());
        }
        return this.agendamentoRepo.save(agendamento);
    }

    public void deleteAgendamento(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Agendamento com id {}", id);
        }
        this.agendamentoRepo.deleteById(id);
    }

   }
