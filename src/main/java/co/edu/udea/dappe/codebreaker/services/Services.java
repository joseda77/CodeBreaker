package co.edu.udea.dappe.codebreaker.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import co.edu.udea.dappe.codebreaker.dao.Codebreaker;
import co.edu.udea.dappe.codebreaker.dto.Response;


@RestController
@RequestMapping("/codebreaker")
public class Services {
	
	public Codebreaker codebreaker = new Codebreaker();
	
	@GetMapping(value="/{number}")
    public Response guessNumber (@PathVariable("number") String number) {
		String result = null;
		try {
			if(!this.codebreaker.isNumber(number)) {
				return new Response("Solo se permiten numeros", HttpStatus.BAD_REQUEST.toString());
			}else if(number.length()!= 4) {
				return new Response("Debe ingresar 4 números", HttpStatus.BAD_REQUEST.toString());
			}
			result = this.codebreaker.decode(number);
		} catch (Exception e ) {
			return new Response("Ha ocurrido un error inesperado "+ e.getCause().getMessage(), HttpStatus.BAD_REQUEST.toString());
		}
		return new Response(result, HttpStatus.OK.toString());
    }
	
	@PostMapping("/secret")
	public Response setSecret (@RequestBody JsonNode secret) {
		String number = secret.get("secret").toString();
		try {
			if(!this.codebreaker.isNumber(number)) {
				return new Response("Solo se permiten numeros", HttpStatus.BAD_REQUEST.toString());
			}else if(number.length()!= 4) {
				return new Response("Debe ingresar 4 números", HttpStatus.BAD_REQUEST.toString());
			}
			this.codebreaker.setSecret(number);
		} catch (Exception e) {
			return new Response("Ha ocurrido un error inesperado "+ e.getCause().getMessage(), HttpStatus.BAD_REQUEST.toString());
		}
		return new Response("Se asignado correctamente el secret", HttpStatus.ACCEPTED.toString());
	}
}
