package com.ncuhome.QandA.repository

import com.ncuhome.QandA.models.CategoryNameModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryNameRepo: JpaRepository<CategoryNameModel, Int> {

fun findOneByCategoryName(name: String): CategoryNameModel
}