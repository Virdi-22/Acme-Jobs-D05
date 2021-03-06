
package acme.entities._requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "ticker")
})
public class _Request extends DomainEntity {

	// Serialisation identifier -------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	private String				text;

	@NotNull
	@Valid
	private Money				reward;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^R[A-Z]{4}-[0-9]{5}$", message = "{acme.validation.request.ticker.pattern}")
	private String				ticker;

}
