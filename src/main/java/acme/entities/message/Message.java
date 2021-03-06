
package acme.entities.message;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.messageThread.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "moment")
})
public class Message extends DomainEntity {

	// Serialisation identifier ----------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				body;

	private String				tags;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	// Relationships --------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = true)
	private MessageThread		messageThread;

	@NotNull
	@Valid
	@ManyToOne(optional = true)
	private Authenticated		authenticated;
}
