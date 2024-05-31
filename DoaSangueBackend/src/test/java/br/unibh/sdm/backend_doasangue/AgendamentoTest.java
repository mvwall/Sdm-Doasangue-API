package br.unibh.sdm.backend_doasangue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import br.unibh.sdm.backend_doasangue.entidades.Agendamento;
import br.unibh.sdm.backend_doasangue.persistencia.AgendamentoRepository;



@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@ContextConfiguration 
public class AgendamentoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendamentoTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @Autowired
    private AgendamentoRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos de agendamento...");
        Agendamento agendamento1 = new Agendamento(null, "1234567890", "Hospital XYZ", df.parse("01/08/2024"), "Agendado");
        repository.save(agendamento1);

        LOGGER.info("Pesquisando todos os agendamentos...");
        Iterable<Agendamento> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Agendamento agendamento : lista) {
            LOGGER.info(agendamento.toString());
        }
    }

    @Test
    public void teste2Atualizacao() {
        LOGGER.info("Atualizando status de agendamento...");
        List<Agendamento> result = repository.findByStatus("Agendado");
        for (Agendamento agendamento : result) {
            LOGGER.info("Atualizando status de Agendamento id = {}", agendamento.getIdL());
            agendamento.setStatus("Confirmado");
            repository.save(agendamento);
        }
    }

    @Test
    public void teste3Exclusao() {
        LOGGER.info("Excluindo objetos de agendamento...");
        List<Agendamento> result = repository.findByStatus("Confirmado");
        for (Agendamento agendamento : result) {
            LOGGER.info("Excluindo Agendamento id = {}", agendamento.getIdL());
            repository.delete(agendamento);
        }
        assertEquals(repository.findByStatus("Confirmado").size(), 0);
    }
}
