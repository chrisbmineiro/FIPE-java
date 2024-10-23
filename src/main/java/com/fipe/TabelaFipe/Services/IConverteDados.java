package com.fipe.TabelaFipe.Services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}

