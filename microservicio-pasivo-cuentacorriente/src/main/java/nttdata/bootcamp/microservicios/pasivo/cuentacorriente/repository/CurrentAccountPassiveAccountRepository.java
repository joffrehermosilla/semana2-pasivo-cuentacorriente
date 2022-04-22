package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import nttdata.bootcamp.microservicios.pasivo.cuentacorriente.documents.CurrentAccountPassiveAccount;

@Repository
public interface CurrentAccountPassiveAccountRepository extends ReactiveMongoRepository<CurrentAccountPassiveAccount, String> {

}
