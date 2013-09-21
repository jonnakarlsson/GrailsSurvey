package survey
import java.text.SimpleDateFormat
class StatisticsController {

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	def allQuestions = SurveyQuestion.list()

	def index(){
		if (request.post){

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
	def type1(){
		if (request.post){
			
			Date sd = dateFormat.parse(params.startDate)
			Date ed = dateFormat.parse(params.endDate)
			def allGradeQuestions = allQuestions.findAll{ SurveyQuestion ->
				SurveyQuestion.getQuestionType() == 1
			}
			def listQACs = []

			for (q in allGradeQuestions){
				def questionText = q.questionText;
				int countAnswers = 0;
				int totValue = 0;
				def allGradeAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

				for (a in allGradeAnswers){

					countAnswers ++
					totValue = totValue + Integer.parseInt(a.answerValue)
				}
				listQACs << [question: questionText, avrage: countAnswers ? totValue/countAnswers : 0, totAnswers: countAnswers]
			}
			return [listWithQuestionAvrageAndCount: listQACs]
		}
	}
	def type2(){
		if (request.post){
			
			Date sd = dateFormat.parse(params.startDate)
			Date ed = dateFormat.parse(params.endDate)
			def allBooleanQuestions = allQuestions.findAll{ SurveyQuestion ->
				SurveyQuestion.getQuestionType() == 2
			}
			def listQCP = []

			for (q in allBooleanQuestions){
				def questionText = q.questionText;
				int totTrue = 0;
				int totFalse = 0;
				def allBooleanAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

				for (a in allBooleanAnswers){
					if (Boolean.parseBoolean(a.answerValue) == true){
						totTrue ++
					}
					else if (Boolean.parseBoolean(a.answerValue)== false){
						totFalse ++
					}
					int countAnswers = totTrue + totFalse;
					int trues = totTrue / countAnswers * 100;
					int falses = totFalse / countAnswers * 100;
					listQCP << [question: questionText, countAnswers: countAnswers, trues: trues, falses: falses ]
				}
				return [listWithQuestionCountAndProcentage: listQCP]
			}
		}
	}
}
