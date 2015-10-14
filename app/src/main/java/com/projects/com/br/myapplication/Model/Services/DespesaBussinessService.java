package com.projects.com.br.myapplication.Model.Services;

import com.projects.com.br.myapplication.Model.Entities.Despesa;
import com.projects.com.br.myapplication.Model.Persistence.DespesaRepository;

import java.util.List;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class DespesaBussinessService {

    private DespesaBussinessService() {
        super();
    }

    public static List<Despesa> findAll() {
        return DespesaRepository.getAll();
    }

    public static void save(Despesa despesa) {
        DespesaRepository.save(despesa);
    }

    public static void delete(Despesa despesa){
        DespesaRepository.delete(despesa.getId());
    }

    public static Double somaValues() {
        List<Despesa> despesas = DespesaBussinessService.findAll();

        Double soma = 0.0;

        for (Despesa despesa : despesas) {
            soma += despesa.getValor();
        }

        return soma;
    }
}
