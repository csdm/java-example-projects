package it.claudiodimauro.ApiDataFetch;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import it.claudiodimauro.ApiDataFetch.dataExtractors.ObjMapperExtractor;
import it.claudiodimauro.ApiDataFetch.models.Address;
import it.claudiodimauro.ApiDataFetch.models.Company;
import it.claudiodimauro.ApiDataFetch.models.Geo;
import it.claudiodimauro.ApiDataFetch.models.User;
import it.claudiodimauro.ApiDataFetch.utils.Constants;

public class App {

	public static void main(String[] args) {

		/**********************************************
		 * Ricerca dati da API usando ObjectMapper	  *
		 **********************************************/
		System.out.println("**********************************************\n"
						 + "* Ricerca dati da API usando ObjectMapper    *\n"
						 + "**********************************************\n\n");
		
		//Lista di appoggio per creare un gruppo di User
		List<User> userList = new ArrayList<>();
		
		//Dichiaro l'estrattore, basato sull'ObjectMapper
		ObjMapperExtractor extractor = new ObjMapperExtractor(Constants.URLs.USERS);

		//Tramite l'estrattore tiro fuori la lista di tutti i nodi provenienti dal payload della response.
		JsonNode node = extractor.fetchDataAsNode();
		
		//Controllo se il payload ritornato è un singolo json o un array di json
		//mi comporto in modo differente a seconda del caso
		if(!extractor.isReturningJsonArray()) {
			//Test con response con singolo blocco json, senza array di json
			
			//Definisco il "nodo singolo" che costituisce il json (sotto di esso ci saranno altri nodi
			//che costituiranno i campi del json)
			JsonNode singleNode = node.get(0);
			
			//La logica non fa nulla di particolare, si limita a prelevare e stampare il nodo "name" da sotto
			//il json appena trovato
			String name = singleNode.get("name").asText();
			System.out.println("Name: " + name);
		} else {
			//Test con response con array di json
			
			//Dato che la variabile node sarà costituita da un array di n elementi, avrà una size.
			//In base a questa size andremo a scorrere l'array per prendere singolarmente il JsonNode di ogni
			//json che costituisce l'array.
			for(int i=0; i<node.size(); i++) {
				
				//Prendo il singolo nodo, all'indice i, che fa parte dell'array
				JsonNode userNode = node.get(i);
				
				//userNode è composto da vari nodi innestati, che mi vado a prendere con i
				//seguenti assegnamenti.
				JsonNode userNodeAddress = userNode.get("address");
				JsonNode userNodeAddressGeo = userNodeAddress.get("geo");
				JsonNode userNodeCompany = userNode.get("company");
				
				//Definisco un oggetto di tipo User (ne istanzio uno per ogni iterazione del for)
				User user = new User();	
				
				//Setto i campi dell'oggetto user sfruttando ciò che ho appena prelevato come "nodo"
				user.setId(userNode.get("id").asInt());
				user.setName(userNode.get("name").asText());
				user.setUsername(userNode.get("username").asText());
				user.setEmail(userNode.get("email").asText());
				user.setAddress(new Address(
											userNodeAddress.get("street").asText(),
											userNodeAddress.get("suite").asText(),
											userNodeAddress.get("city").asText(),
											userNodeAddress.get("zipcode").asText(),
											new Geo(userNodeAddressGeo.get("lat").asText(), userNodeAddressGeo.get("lng").asText())
								));
				user.setPhone(userNode.get("phone").asText());
				user.setWebsite(userNode.get("website").asText());
				user.setCompany(new Company(
											userNodeCompany.get("name").asText(), 
											userNodeCompany.get("catchPhrase").asText(),
											userNodeCompany.get("bs").asText() 
								));
				
				//Aggiungo l'user ad una lista
				userList.add(user);
			}
		}	

		//La logica non fa nulla di particolare, si limita solo a stampare la lista
		for(User u : userList) {
			System.out.println(
						"ID: " + u.getId() + "\n"
					+   "Name: " + u.getName() + "\n"
					+   "Username: " + u.getUsername() + "\n"
					+   "Email: " + u.getEmail() + "\n"
					+   "Address: " + u.getAddress() + "\n"	//sfrutta il toString della classe Address
					+   "Phone: " + u.getPhone() + "\n"
					+   "Website: " + u.getWebsite() + "\n"
					+   "Company: " + u.getCompany() + "\n" //sfrutta il toString della classe Company
					+ "****************** ******************\n****************** ******************"
					);
		}
	}
}
