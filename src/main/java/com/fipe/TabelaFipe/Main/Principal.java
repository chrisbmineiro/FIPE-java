package com.fipe.TabelaFipe.Main;

import com.fipe.TabelaFipe.Models.Modelos;
import com.fipe.TabelaFipe.Services.ConsumoAPI;
import com.fipe.TabelaFipe.Services.ConverteDados;

import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("Opções: Carros, Motos, Caminhões");
        System.out.println("Digite a opção: ");
        var opcao = scanner.nextLine();

        var json = consumo.obterDados(URL + opcao + "/marcas");
        System.out.println(json);

        System.out.println("Agora digite o codigo do modelo: ");
        int codigoModelo = scanner.nextInt();
        json = consumo.obterDados(URL + opcao + "/marcas/" + codigoModelo + "/modelos");
        System.out.println(json);

        System.out.println("Agora digite o codigo do combustivel: ");
        int codigoCombustivel = scanner.nextInt();
        json = consumo.obterDados(URL + opcao + "/marcas/" + codigoModelo + "/modelos/" + codigoCombustivel + "/anos");
        System.out.println(json);

        System.out.println("Agora digite o ano: ");
        int codigoAno = scanner.nextInt();
        json = consumo.obterDados(URL + opcao + "/marcas/" + codigoModelo + "/modelos/" + codigoAno + "/anos" + codigoAno);
        System.out.println(json);
    }
}
