package survey
import java.text.*;
class StatisticsController {

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	DecimalFormat df = new DecimalFormat("#.##");
	def allQuestions = SurveyQuestion.list()

	def index(int whichQuestions){
		if (request.post){

			redirect (action: "type$whichQuestions", params: [startDate: params.startDate, endDate:params.endDate, whichQuestions: whichQuestions ])
		}
	}
	def type1(){


		Date sd = dateFormat.parse(params.startDate)
		Date ed = dateFormat.parse(params.endDate)
		def allGradeQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 1
		}
		def listQACs = []		//list with questions avrage and how many answers(count)
		for (q in allGradeQuestions){
			def questionText = q.questionText;
			int countAnswers = 0;				//ska detta ligga i for-loopen?
			int totValue = 0;					//ska detta ligga i for-loopen?
			def allGradeAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allGradeAnswers.size()>0){

				for (a in allGradeAnswers){
					if (Integer.parseInt(a.answerValue)>0){
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
				}
				listQACs << [question: questionText, avrage: countAnswers ? df.format(totValue/countAnswers) : 0, totAnswers: countAnswers]  //Förklara ?
			}
		}
		return [listWithQuestionAvrageAndCount: listQACs, startDate: params.startDate, endDate:params.endDate, whichQuestions: params.whichQuestions ]

	}
	def type2(){


		Date sd = dateFormat.parse(params.startDate)
		Date ed = dateFormat.parse(params.endDate)
		def allBooleanQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 2
		}
		def listQCP = []  	//list with questions, count (how many) and %
		for (q in allBooleanQuestions){
			def questionText = q.questionText;
			int countAnswers = 0
			int totTrue = 0;
			int totFalse = 0;
			int notAnswered = 0;
			def allBooleanAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allBooleanAnswers.size()>0){

				for (a in allBooleanAnswers){

					if (a.answerValue == "null"){
						notAnswered ++
					}
					else if (Boolean.parseBoolean(a.answerValue) == true){
						totTrue ++
						countAnswers ++
					}
					else if (Boolean.parseBoolean(a.answerValue)== false){
						totFalse ++
						countAnswers ++
					}
				}
				int trues = totTrue ? totTrue / countAnswers * 100 : 0
				int falses = totFalse ? totFalse / countAnswers * 100 : 0
				listQCP << [question: questionText, countAnswers: countAnswers, trues: trues, falses: falses, notAnswered: notAnswered ]
			}

		}
		return [listWithQuestionCountAndProcentage: listQCP, startDate: params.startDate, endDate:params.endDate, whichQuestions: params.whichQuestions]

	}


	def type3(){


		Date sd = dateFormat.parse(params.startDate)
		Date ed = dateFormat.parse(params.endDate)
		def allTextQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 3
		}
		def listQDateAndText = []

		for (q in allTextQuestions){
			def questionText = q.questionText;
			def allTextAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			for (a in allTextAnswers){
				def textAnswer = a.answerValue
				StringBuilder textDate = new StringBuilder(dateFormat.format(a.answerDate))
				listQDateAndText << [textDate: textDate, question: questionText, text: textAnswer]
			}
			return [listWithDateQuestionAndText: listQDateAndText, startDate: params.startDate, endDate:params.endDate, whichQuestions: params.whichQuestions ]
		}

	}

	def type4(){


		Date sd = dateFormat.parse(params.startDate)
		Date ed = dateFormat.parse(params.endDate)

		//Betygsfrågor:

		def allGradeQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 1
		}
		def listQACs = []
		for (q in allGradeQuestions){
			def questionText = q.questionText;
			int countAnswers = 0;				//ska detta ligga i for-loopen?
			int totValue = 0;					//ska detta ligga i for-loopen?
			def allGradeAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allGradeAnswers.size()>0){

				for (a in allGradeAnswers){
					if (Integer.parseInt(a.answerValue)>0){
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
				}
				listQACs << [question: questionText, avrage: countAnswers ? df.format(totValue/countAnswers) : 0, totAnswers: countAnswers]  //Förklara ?
			}
		}

		//Booleanfrågor:
		def allBooleanQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 2
		}
		def listQCP = []  	//list with questions, count (how many) and %
		for (q in allBooleanQuestions){
			def questionText = q.questionText;
			int countAnswers = 0
			int totTrue = 0;
			int totFalse = 0;
			int notAnswered = 0;
			def allBooleanAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allBooleanAnswers.size()>0){

				for (a in allBooleanAnswers){

					if (a.answerValue == "null"){
						notAnswered ++
					}
					else if (Boolean.parseBoolean(a.answerValue) == true){
						totTrue ++
						countAnswers ++
					}
					else if (Boolean.parseBoolean(a.answerValue)== false){
						totFalse ++
						countAnswers ++
					}
				}
				int trues = totTrue ? totTrue / countAnswers * 100 : 0
				int falses = totFalse ? totFalse / countAnswers * 100 : 0
				listQCP << [question: questionText, countAnswers: countAnswers, trues: trues, falses: falses, notAnswered: notAnswered ]
			}

		}
		return [listWithQuestionCountAndProcentage: listQCP, listWithQuestionAvrageAndCount: listQACs, startDate: params.startDate, endDate:params.endDate, whichQuestions: params.whichQuestions ]

	}

	def type5(){


		Date sd = dateFormat.parse(params.startDate)
		Date ed = dateFormat.parse(params.endDate)

		//Betygsfrågor:

		def allGradeQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 1
		}
		def listQACs = []
		for (q in allGradeQuestions){
			def questionText = q.questionText;
			int countAnswers = 0;				//ska detta ligga i for-loopen?
			int totValue = 0;					//ska detta ligga i for-loopen?
			def allGradeAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allGradeAnswers.size()>0){

				for (a in allGradeAnswers){
					if (Integer.parseInt(a.answerValue)>0){
						countAnswers ++
						totValue = totValue + Integer.parseInt(a.answerValue)
					}
				}
				listQACs << [question: questionText, avrage: countAnswers ? df.format(totValue/countAnswers) : 0, totAnswers: countAnswers]  //Förklara ?
			}
		}

		//Booleanfrågor:
		def allBooleanQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 2
		}
		def listQCP = []  	//list with questions, count (how many) and %
		for (q in allBooleanQuestions){
			def questionText = q.questionText;
			int countAnswers = 0
			int totTrue = 0;
			int totFalse = 0;
			int notAnswered = 0;
			def allBooleanAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			if (allBooleanAnswers.size()>0){

				for (a in allBooleanAnswers){

					if (a.answerValue == "null"){
						notAnswered ++
					}
					else if (Boolean.parseBoolean(a.answerValue) == true){
						totTrue ++
						countAnswers ++
					}
					else if (Boolean.parseBoolean(a.answerValue)== false){
						totFalse ++
						countAnswers ++
					}
				}
				int trues = totTrue ? totTrue / countAnswers * 100 : 0
				int falses = totFalse ? totFalse / countAnswers * 100 : 0
				listQCP << [question: questionText, countAnswers: countAnswers, trues: trues, falses: falses, notAnswered: notAnswered ]
			}

		}

		//text frågor
		def allTextQuestions = allQuestions.findAll{ SurveyQuestion ->
			SurveyQuestion.getQuestionType() == 3
		}
		def listQDateAndText = []

		for (q in allTextQuestions){
			def questionText = q.questionText;
			def allTextAnswers = SurveyAnswer.findAllByQuestionAndAnswerDateBetween(q, sd, ed)

			for (a in allTextAnswers){
				def textAnswer = a.answerValue
				StringBuilder textDate = new StringBuilder(dateFormat.format(a.answerDate))
				listQDateAndText << [textDate: textDate, question: questionText, text: textAnswer]
			}
		}
		return [listWithQuestionCountAndProcentage: listQCP, listWithQuestionAvrageAndCount: listQACs, listWithDateQuestionAndText: listQDateAndText, startDate: params.startDate, endDate:params.endDate, whichQuestions: params.whichQuestions ]
	}

}
