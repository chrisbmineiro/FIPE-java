package com.fipe.TabelaFipe.Main;

import com.fipe.TabelaFipe.Models.Dados;
import com.fipe.TabelaFipe.Models.Modelos;
import com.fipe.TabelaFipe.Models.Veiculo;
import com.fipe.TabelaFipe.Services.ConsumoAPI;
import com.fipe.TabelaFipe.Services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        var menu = """
               *** OPÇÕES ***
               Carro
               Moto
               Caminhão
               
               Digite uma das opções para consulta:
               """;
        System.out.println(menu);
        var opcao = scanner.nextLine();
        String url;

        String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
        if (opcao.toLowerCase().contains("carr")) {
            url = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            url = URL_BASE + "motos/marcas";
        } else {
            url = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(url);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Agora digite o codigo da marca: ");
        var codigoMarca = scanner.nextLine();

        url = url + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(url);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Agora digite o trecho referente ao carro a ser buscado: ");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .toList();

        System.out.println("\nModelos Filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Agora digite o codigo do modelo: ");
        var codigoModelo = scanner.nextLine();

        url = url + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(url);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (Dados ano : anos) {
            var urlAno = url + "/" + ano.codigo();
            json = consumo.obterDados(urlAno);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veiculos filtrados: ");
        veiculos.forEach(System.out::println);
    }
}