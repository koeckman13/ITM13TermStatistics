package at.fhj.itm.pswe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@Table(name="word")
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="text")
	private String text;

	@Column(name="active")
	private byte active;

	//bi-directional many-to-one association to Container
	/*@OneToMany(mappedBy="word")
	@Column(name="containers")
	private List<Container> containers;*/

	public Word() {
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	/*public List<Container> getContainers() {
		return this.containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public Container addContainer(Container container) {
		getContainers().add(container);
		container.setWord(this);

		return container;
	}

	public Container removeContainer(Container container) {
		getContainers().remove(container);
		container.setWord(null);

		return container;
	}*/

}