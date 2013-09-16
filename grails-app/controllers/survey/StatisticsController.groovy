package survey
import java.text.SimpleDateFormat
class StatisticsController {

	def index() {
		if (request.post) {
			println params;
		}
	}

	def stats(){
		if (request.post){

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			def allAnswers = SurveyAnswer.list()
			def allQuestions = SurveyQuestion.list()
			Date sd = dateFormat.parse(params.startDate)
			Date ed = dateFormat.parse(params.endDate)
			def listQACs = []

			for (q in allQuestions){
				def questionText = q.questionText;
				int countAnswers = 0;
				int totValue = 0;

				for (a in allAnswers){
					def qId = (a.question).questionId
					if ((a.answerDate)>= sd && (a.answerDate)<= ed && (q.questionId)== qId &&(q.questionType) == 1){
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
				//def avrage = totValue/countAnswers;   Får division fel, jag har valt att ta bort det tills vidare. 
				def avrage = totValue
				
				def myMapWithQAC = [question: questionText, avrage: avrage, totAnswers: countAnswers]
				listQACs << myMapWithQAC
			}
				//OCH HÄR HUR GÖR JAG ? 
		}
	}
}
}
