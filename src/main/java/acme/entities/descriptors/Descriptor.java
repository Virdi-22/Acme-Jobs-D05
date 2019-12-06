
package acme.entities.descriptors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	// Serialisation identifier -------------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes ---------------------------------------------------------------

	@NotBlank
	@Column(length = 1024)
	private String description;

}
