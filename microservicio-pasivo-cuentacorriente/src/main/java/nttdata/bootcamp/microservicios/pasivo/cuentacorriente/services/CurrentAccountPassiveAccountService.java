package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.services;

import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.documents.CurrentAccountPassiveAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountPassiveAccountService {
	public Mono<CurrentAccountPassiveAccount> findById(String id);

	public Flux<CurrentAccountPassiveAccount> findAlls();

	public Mono<CurrentAccountPassiveAccount> saves(CurrentAccountPassiveAccount document);

	public Mono<Void> delete(CurrentAccountPassiveAccount document);
}
