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
					if (a.question.questionType == 1)					{
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
				}
                listQACs << [question: questionText, avrage: countAnswers ? totValue/countAnswers : 0, totAnswers: countAnswers]
			}
            return [listWithQuestionAvrageAndCount: listQACs]
		}
	}
}
