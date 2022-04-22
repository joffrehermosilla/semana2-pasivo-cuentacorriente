package nttdata.bootcamp.microservicios.pasivo.cuentacorriente.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection = "currentaccount-passive-account")
public class CurrentAccountPassiveAccount {
	// el id será el número de transacción que se anexará a la cuenta para consultar
	// saldos, hacer transferencias y pagos
	@Id
	private String id;

	// si es tipo cuenta persona o empresarial
	private String namebyaccount;

	// cantidad de comision por cuenta que tiene la cuenta de ahorro
	private Double savingprates;

	private boolean enabledtouse;

	private int dateofexpiry;

	private Double commissionformaintenance;

	private int maximummovementlimit;

	private Double currentbalance;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}
