package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.documents.CurrentAccountPassiveAccount;
import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.repository.CurrentAccountPassiveAccountRepository;
import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.services.CurrentAccountPassiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountPassiveAccountServiceImpl implements CurrentAccountPassiveAccountService {

	@Autowired
	CurrentAccountPassiveAccountRepository repository;

	@Override
	public Mono<CurrentAccountPassiveAccount> findById(String id) {

		return repository.findById(id);
	}

	@Override
	public Flux<CurrentAccountPassiveAccount> findAlls() {

		return repository.findAll();
	}

	@Override
	public Mono<CurrentAccountPassiveAccount> saves(CurrentAccountPassiveAccount document) {

		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(CurrentAccountPassiveAccount document) {

		return repository.delete(document);
	}

}
