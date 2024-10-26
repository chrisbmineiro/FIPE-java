package com.fipe.TabelaFipe.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// para retornar somente uma lista dos modelos
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {
}
