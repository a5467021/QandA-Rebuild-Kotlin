package com.ncuhome.QandA.models

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "name_category")
class CategoryNameModel: Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private var categoryId: Int = 0
	
	@Column(name = "name")
	private var categoryName: String = ""
	
	fun setCategoryId(categoryId: Int) {
		this.categoryId = categoryId
	}
	
	fun getCategoryId(): Int {
		return this.categoryId
	}
	
	fun setCategoryName(categoryName: String) {
		this.categoryName = categoryName
	}
	
	fun getCategoryName(): String {
		return this.categoryName
	}
	
	override fun toString(): String {
		return "CategoryNameModel: [categoryId: \"${this.categoryId}\", categoryName: \"${this.categoryName}\"]"
	}
}