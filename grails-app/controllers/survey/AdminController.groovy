package survey
import survey.SurveyQuestion;

class AdminController {
	def index() {

		if (request.post) {

			println params
		}
		
		def allQuestions = SurveyQuestion.list(sort:"questionId")

		def activeQuestions = SurveyQuestion.findAllEnabled()

		def activeGradeQuestions = activeQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 1
		}

		def activeBooleanQuestions = activeQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 2
		}

		def activeTextQuestions = activeQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType ()== 3
		}


		return [allQuestions: allQuestions, activeQuestions: activeQuestions, activeGradeQuestions: activeGradeQuestions, activeBooleanQuestions: activeBooleanQuestions, activeTextQuestions: activeTextQuestions, allQuestionsSize: allQuestions.size(), nextNumber: (allQuestions.size()+1)]
	}


	def createQuestion(){
		if (request.post){

			def allQuestions = SurveyQuestion.list(sort:"questionId")
			
			int questionType = Integer.parseInt(params.questionType)
			boolean enabled = params.boolean('enabled')
			int sortOrder = Integer.parseInt(params.sortOrder)
			
			
			SurveyQuestion question = new SurveyQuestion(questionType: questionType, dateCreated: new Date(), questionText: params.newQuestion,
			enabled: enabled, sortOrder: sortOrder, questionId: allQuestions.size()+1 ) 
			question.save(failOnError: true)
			
			if(question.hasErrors()){
				return [question: question]
			}
			
			redirect action: "index"
		}
	}	
	
	def edit(){
		if (request.post){			
			
			def q = SurveyQuestion.get(params.dbId) 
			
			if (params.sortNo){
			int sortNo = Integer.parseInt(params.sortNo)			
			q.setSortOrder(sortNo)
			}
			
			boolean e = params.boolean('enabled')
			q.setEnabled(e)		
					 
			redirect action: "index"
		}
	}
}
