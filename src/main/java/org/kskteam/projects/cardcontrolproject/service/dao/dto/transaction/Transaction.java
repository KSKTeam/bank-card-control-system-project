package org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;

@Entity
@Table(name="TRANSACTIONS")
@XmlRootElement
public class Transaction {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String description;
	
	private Date createdAt;
	
	private double total;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User createdBy;
	
	private long cardNumber;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private TransactionType type;
	
	private String address;
	
	public Transaction() {
	}

	public Transaction(String id, String description, Date createdAt, double total, User createdBy, long cardNumber,
			TransactionType type, String address) {
		super();
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.total = total;
		this.createdBy = createdBy;
		this.cardNumber = cardNumber;
		this.type = type;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", description=" + description + ", createdAt=" + createdAt + ", total="
				+ total + ", createdBy=" + createdBy + ", cardNumber=" + cardNumber + ", type=" + type + ", address="
				+ address + "]";
	}
}
