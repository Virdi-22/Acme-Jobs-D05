
package acme.entities.duties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {

	// Serialisation identifier -------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@Range(min = 1, max = 100)
	private int					percentage;

	// Relationships ------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

}
