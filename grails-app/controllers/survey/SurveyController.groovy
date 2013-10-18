package survey

import java.text.SimpleDateFormat

class SurveyController {
    def index() {
		
        if (request.post) {
			
            println params;
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
				   
        return [activeQuestions: activeQuestions, activeGradeQuestions: activeGradeQuestions, activeBooleanQuestions: activeBooleanQuestions, activeTextQuestions: activeTextQuestions]

    }
	def createAnswer(){
		if (request.post){
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			
			def transactionId = System.currentTimeMillis()
			Date answerDate = dateFormat.parse(params.dt)					
			def contact = params.contact    
			
			params.findAll{it.key.startsWith('question')}.each{
				key, value ->
				def question = SurveyQuestion.get(key[8..-1])
				def answer = new SurveyAnswer(transactionId: transactionId, answerDate: answerDate, answerValue: value, question: question, contact: contact)
				answer.save(failOnError: true)							
				
				println params;
			}			
			redirect action: "index", params: [dt:params.dt] 			
		}
		
	}
	
	
}
