
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	// Serialisation identifier -------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attribute ----------------------------------------------------

	@NotBlank
	@URL
	private String				target;

	@NotBlank
	private String				slogan;

	// Relationships ------------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
