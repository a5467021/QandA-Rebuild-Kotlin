package com.ncuhome.QandA.models

import java.io.Serializable
import javax.naming.Name
import javax.persistence.*

@Entity
@Table(name = "question")
class QuestionModel: Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qid")
	private var qid: Int = 0
	
	@Column(name = "description")
	private var description: String = ""
	
	@Column(name = "cid")
	private var cid: Int = 0
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cid", insertable = false, updatable = false)
	private lateinit var category: CategoryNameModel
	
	fun setDescription(description: String) {
		this.description = description
	}
	
	fun getDescription(): String {
		return this.description
	}
	
	fun setCid(cid: Int) {
		this.cid = cid
	}
	
	fun getCid(): Int {
		return this.cid
	}
	
	fun setCategory(category: CategoryNameModel) {
		this.category = category
	}
	
	fun getCategory(): CategoryNameModel {
		return this.category
	}
}

