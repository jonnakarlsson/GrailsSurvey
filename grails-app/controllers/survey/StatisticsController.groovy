package survey
import java.text.SimpleDateFormat
class StatisticsController {

	def index(int questionType){
		if (request.post){

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			def allGradeQuestions = SurveyQuestion.findAllByQuestionTypeAndEnabled(questionType, true)
			Date sd = dateFormat.parse(params.startDate)
			Date ed = dateFormat.parse(params.endDate)
			def listQACs = []

			for (q in allGradeQuestions){
				def questionText = q.questionText;
				int countAnswers = 0;
				int totValue = 0;
				def allAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

				for (a in allAnswers){
					countAnswers ++
					totValue = totValue + Integer.parseInt(a.answerValue)
				}
                listQACs << [question: questionText, avrage: countAnswers ? totValue/countAnswers : 0, totAnswers: countAnswers]
			}
            return [listWithQuestionAvrageAndCount: listQACs]
		}
	}
}
