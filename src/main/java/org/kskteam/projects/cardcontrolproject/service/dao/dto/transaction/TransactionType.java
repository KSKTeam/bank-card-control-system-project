package org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="TRANSACTION_TYPES")
@XmlRootElement
public class TransactionType {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private short id;
	
	private short number;
	
	private String name;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
