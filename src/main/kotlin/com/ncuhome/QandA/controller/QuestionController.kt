package com.ncuhome.QandA.controller

import com.ncuhome.QandA.models.CategoryNameModel
import com.ncuhome.QandA.repository.CategoryNameRepo
import com.ncuhome.QandA.repository.QuestionRepo
import com.ncuhome.QandA.utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

const val QUESTION_AMOUNT: Int = 4
const val IMAGE_PREFIX: String = "static/bgimage/"

@RestController
class Question {
	
	@Autowired
	lateinit var questionRepo: QuestionRepo
	
	@Autowired
	lateinit var categoryNameRepo: CategoryNameRepo
	
	@PostMapping("/api/v4/rand_question")
	fun randQuestion(@RequestBody request: QuestionRequestModel, pageable: Pageable): Any {
		val categories: MutableList<String> = when(request.getCategories()) {
			null -> {
				{
					val ret: MutableList<String> = mutableListOf<String>()
					val res = categoryNameRepo.findAll()
					for(categoryName in res){
						ret.add(categoryName.getCategoryName())
					}
					ret
				}()
			}
			else -> request.getCategories()!!.toMutableList()
		}
		
		while(categories.size < QUESTION_AMOUNT) {
			categories.add(categories[utils.randIntRanged(categories.size)])
		}
		utils.shuffle(categories)
		
		val questionMap = LinkedHashMap<String, Any>()
		for(i in 1..QUESTION_AMOUNT) {
			val category = categoryNameRepo.findOneByCategoryName(categories[i - 1])
			val questionList = questionRepo.findAllByCategory(category, pageable)
			val selectedQuestion = questionList.shuffled()[0]
			val question = QuestionResponseModel()
			question.setDescription(selectedQuestion.getDescription())
			question.setImage(IMAGE_PREFIX + categories[i - 1] + "/" +
					utils.listDir(IMAGE_PREFIX + categories[i - 1], ".jpg").shuffled()[0])
			question.setCategory(selectedQuestion.getCategory().getCategoryName())
			questionMap.put("ques$i", question)
		}
		
		return questionMap
	}
}

class QuestionRequestModel {
	
	private var categories: Array<String> ?= null
	
	fun setCategories(categories: Array<String>) { this.categories = categories	}
	
	fun getCategories(): Array<String>? {
		return when {
			categories == null -> null
			categories!!.isEmpty() -> null
			else -> categories
		}
	}
}

class QuestionResponseModel {
	
	private lateinit var description: String
	private lateinit var image: String
	private lateinit var category: String
	
	fun setDescription(description: String) { this.description = description }
	fun setImage(image: String) { this.image = image }
	fun setCategory(category: String) { this.category = category }
	
	fun getDescription(): String { return this.description }
	fun getImage(): String { return this.image }
	fun getCategory(): String {return this.category}
}