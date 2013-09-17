package survey
import java.text.SimpleDateFormat
class StatisticsController {

	def index(){
		if (request.post){

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			def allQuestions = SurveyQuestion.list()
			Date sd = dateFormat.parse(params.startDate)
			Date ed = dateFormat.parse(params.endDate)
			def listQACs = []

			for (q in allQuestions){
				def questionText = q.questionText;
				int countAnswers = 0;
				int totValue = 0;
				def allAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

				for (a in allAnswers){
					if ((a.question).questionType == 1)					{
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
					//def avrage = totValue/countAnswers;   Får division fel, jag har valt att ta bort det tills vidare.
					def avrage = totValue

					def myMapWithQAC = [question: questionText, avrage: avrage, totAnswers: countAnswers]
					listQACs << myMapWithQAC
				}
				return [listWithQuestionAvrageAndCount: listQACs]
				println params; 
			}
		}
	}
}
