import survey.SurveyAnswer
import survey.SurveyQuestion;

class BootStrap {

    def init = { servletContext ->
        println " Hej BootStrap"

        new SurveyQuestion([questionId: 1, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 1, questionText: "Hur var helhetsupplevelsen av din vistelse på Djurönäset", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 2, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 0, questionText: "Hur upplevde du receptionen?", enabled: false]).save(failOnError:true)
        new SurveyQuestion([questionId: 3, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 2, questionText: "Hur upplevde du reception och annan service", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 4, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 3, questionText: "Hur upplevde du servicen och bemötandet i restauranten?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 5, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 4, questionText: "Hur upplevde du maten?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 6, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 5, questionText: "Hur upplevde du våra faciliteter?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 7, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 6, questionText: "Hur upplevde du våra hotellrum?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 8, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 7, questionText: "Hur upplevde du städningen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 9, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 8, questionText: "Vilket betyg ger du Djuränäset som helhet?", enabled: true]).save(failOnError:true)
		new SurveyQuestion([questionId: 10, questionType : SurveyQuestion.TYPE_BOOLEAN, sortOrder: 1, questionText: "Är du nöjd med din vistelse på Djurönäset?", enabled: true]).save(failOnError:true)
		new SurveyQuestion([questionId: 11, questionType : SurveyQuestion.TYPE_TEXT, sortOrder: 1, questionText: "Kommentarer", enabled: true]).save(failOnError:true)

		def random = new Random()
		
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
		
		25.times {i ->
			for(q in activeGradeQuestions) {
				new SurveyAnswer(transactionId: i+1, question: q, answerDate: new Date(), answerValue: "${random.nextInt(5)+1}", contact: '').save(failOnError: true)
			}
		}
			
		25.times {j ->
				for(q in activeBooleanQuestions) {
					new SurveyAnswer(transactionId: j+1, question: q, answerDate: new Date(), answerValue: "${random.nextBoolean()}", contact: '').save(failOnError: true)
			}
		}
		
		String bs = "Real-time solutions, architect widgets rich extensible rss-capable implement iterate grow compelling. Synergistic synergize deliver value-added intuitive blogospheres transparent e-enable repurpose, create incentivize e-markets supply-chains global; life-hacks, content e-enable. Dynamic target"
		
		10.times {k ->
			for (q in activeTextQuestions) {
				new SurveyAnswer(transactionId: k+1, question: q, answerDate: new Date(), answerValue: bs, contact:'').save(failOnError: true)
			}
        }
    }
    def destroy = {
    }
}
