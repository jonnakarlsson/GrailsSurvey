package survey

class StatisticsController {

	def index() {
		if (request.post) {
			println params;
		}
	}

	def stats(){
		if (request.post){

			def allAnswers = SurveyAnswer.list
		}
	}
}
