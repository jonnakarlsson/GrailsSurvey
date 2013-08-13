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


	def createGradeQuestion(){
		if (request.post){

			def allQuestions = SurveyQuestion.list(sort:"questionId")

			def q = new SurveyQuestion
					(questionType: 1, dateCreated: new Date(), questionText: params.newQuestion,
					enabled: params.enabled, sortOrder: params.sortNo, questionId: allQuestions.size()+1 )q.save(failOnError: true)
			println q

			redirect action: "index"
		}
	}
	
	def createTextQuestion(){
		if (request.post){
			//skapa en ny textfråga
		}
	}
	
	def createBooleanQuestion(){
		if (request.post){
			//skapa en ny boolean fråga 
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
