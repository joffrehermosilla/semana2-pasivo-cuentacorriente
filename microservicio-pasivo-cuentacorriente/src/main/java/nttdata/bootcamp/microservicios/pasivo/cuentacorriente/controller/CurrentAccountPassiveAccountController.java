package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("passive_currentaccount", service.findAlls());
		return ResponseEntity.ok(response);

	}

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

	@PostMapping("/create-currentaccount-passive-account")
	public Mono<CurrentAccountPassiveAccount> currentAccountPassiveAccount(
			@Valid @RequestBody CurrentAccountPassiveAccount currentAccountPassive) {
		LOGGER.info("CURRENT ACCOUNT PASSING ACCOUNT create: " + service.saves(currentAccountPassive));
		Mono.just(currentAccountPassive).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(currentAccountPassive).onErrorResume(e -> Mono.just(currentAccountPassive))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));

		Mono<CurrentAccountPassiveAccount> newCurrentAccountPassiveAccount = service.saves(currentAccountPassive);

		return newCurrentAccountPassiveAccount;
	}

	@PutMapping("/update-passive-currentaccount/{id}")
	public ResponseEntity<Mono<?>> updatePassiveCurrentAccount(@PathVariable String id,
			@Valid @RequestBody CurrentAccountPassiveAccount currentAccountPassive) {
		Mono.just(currentAccountPassive).doOnNext(t -> {
			currentAccountPassive.setId(id);
			t.setCreateAt(new Date());
		}).onErrorReturn(currentAccountPassive).onErrorResume(e -> Mono.just(currentAccountPassive))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));
		Mono<CurrentAccountPassiveAccount> newPassiveCurrentAccount = service.saves(currentAccountPassive);
		if (newPassiveCurrentAccount != null) {
			return new ResponseEntity<>(newPassiveCurrentAccount, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new CurrentAccountPassiveAccount()), HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/delete-passive-currentaccount/{id}")
	public ResponseEntity<Mono<Void>> deletePassiveCurrentAccount(@PathVariable String id) {
		CurrentAccountPassiveAccount passivecurrentAccount = new CurrentAccountPassiveAccount();
		passivecurrentAccount.setId(id);
		Mono<CurrentAccountPassiveAccount> newPassiveFixedTerm = service.findById(id);
		newPassiveFixedTerm.subscribe();
		Mono<Void> test = service.delete(passivecurrentAccount);
		test.subscribe();
		return ResponseEntity.noContent().build();
	}

}
