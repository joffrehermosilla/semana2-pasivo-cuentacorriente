package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.documents.CurrentAccountPassiveAccount;
import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.services.CurrentAccountPassiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CurrentAccountPassiveAccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountPassiveAccountController.class);

	@Autowired
	private CurrentAccountPassiveAccountService service;

	@GetMapping("/all")
	public Flux<CurrentAccountPassiveAccount> searchAll() {
		Flux<CurrentAccountPassiveAccount> fixedTermPassingAccount = service.findAlls();
		LOGGER.info("CURRENT ACCOUNT  PASSING ACCOUNT registered: " + fixedTermPassingAccount);
		return fixedTermPassingAccount;
	}

	@GetMapping("/id/{id}")
	public Mono<CurrentAccountPassiveAccount> searchById(@PathVariable String id) {
		LOGGER.info("CURRENT ACCOUNT PASSING ACCOUNT id: " + service.findById(id) + " code : " + id);
		return service.findById(id);
	}

	@PostMapping("/fixed-term-passive-account")
	public Mono<CurrentAccountPassiveAccount> currentAccountPassiveAccount(
			@Valid @RequestBody CurrentAccountPassiveAccount passiveSavingAccount) {
		LOGGER.info("CURRENT ACCOUNT PASSING ACCOUNT create: " + service.saves(passiveSavingAccount));
		return service.saves(passiveSavingAccount);
	}

}
