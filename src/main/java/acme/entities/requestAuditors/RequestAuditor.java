
package acme.entities.requestAuditors;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RequestAuditor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------

	// Relationships -------------------------------------------------

	@NotNull
	@Valid
	@OneToOne
	private UserAccount			userAccount;

	@Valid
	@OneToOne(optional = true)
	private Auditor				auditor;

}
