package br.com.alura.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {
	
	private Scanner leitura = new Scanner(System.in);
	private ConsumoAPI consumoapi = new ConsumoAPI();
	private ConverteDados conversor = new ConverteDados();
	
	private final String  ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=1436ef93";

	
	
	public void exibeMenu() {
		
		System.out.println("Digite o nome da serie para busca: ");
		var nomeSerie = leitura.nextLine(); 
		
		
		var json = consumoapi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie seriado = conversor.obterDados(json, DadosSerie.class);
		System.out.println(seriado);
		System.out.println();
				
		
		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= seriado.totalTemporadas(); i++) {
			json = consumoapi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(temporada);
		}


		//temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
		
		for (DadosTemporada temporada : temporadas) {
			
			System.out.println("\n\ntemporada " + temporada.numero() + ": \n");
			
			for (DadosEpisodio episodio : temporada.episodios()) {
				System.out.println(episodio.titulo());
			}
		}
		
	}

}
