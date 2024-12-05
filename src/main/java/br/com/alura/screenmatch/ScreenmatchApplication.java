package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Primeiro projeto Spring sem web");
		
		//ConsumoAPI consumoapi = new ConsumoAPI();
		var consumoapi = new ConsumoAPI();
		var json = consumoapi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=1436ef93"); 
		//System.out.println(json);
		
		//json = consumoapi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);
		
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
	
	
}
