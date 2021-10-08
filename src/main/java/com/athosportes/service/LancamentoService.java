package com.athosportes.service;

import com.athosportes.model.entity.Lancamento;
import com.athosportes.model.enums.StatusLancamento;

import java.util.List;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar(Lancamento lancamentoFiltro);

    void atualizaStatus(Lancamento lancamento, StatusLancamento status);

    void validar(Lancamento lancamento);


}
