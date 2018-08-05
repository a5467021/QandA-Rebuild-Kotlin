package com.ncuhome.QandA.repository

import com.ncuhome.QandA.models.CategoryNameModel
import com.ncuhome.QandA.models.QuestionModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepo: JpaRepository<QuestionModel, Int> {
	
	fun findAllByCategory(category: CategoryNameModel, pageable: Pageable): Page<QuestionModel>
	
	fun findOneByQid(qid: Int): QuestionModel
}