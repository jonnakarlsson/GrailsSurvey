import survey.SurveyAnswer
import survey.SurveyQuestion;

class BootStrap {

    def init = { servletContext ->
        println " Hej BootStrap"

        new SurveyQuestion([questionId: 1, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 1, questionText: "Hur upplevde du bemötandet i restaurangen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 2, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 0, questionText: "Hur upplevde du måsen?", enabled: false]).save(failOnError:true)
        new SurveyQuestion([questionId: 3, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 2, questionText: "Hur upplevde du maten?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 4, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 3, questionText: "Hur upplevde du bemötandet i receptionen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 5, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 4, questionText: "Hur upplevde du kvaliteten på rummen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 6, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 5, questionText: "Hur upplevde du bemötandet under planeringen av er konferens?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 7, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 6, questionText: "Hur upplevde du konferenslokalerna och andra gemensamma utrymmen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 8, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 7, questionText: "Hur upplevde du våra utomhusutrymmen?", enabled: true]).save(failOnError:true)
        new SurveyQuestion([questionId: 9, questionType : SurveyQuestion.TYPE_GRADE, sortOrder: 8, questionText: "Vilket betyg ger du Djurönäset som helhet?", enabled: true]).save(failOnError:true)
		new SurveyQuestion([questionId: 10, questionType : SurveyQuestion.TYPE_BOOLEAN, sortOrder: 1, questionText: "Rekommenderar du Djurönäset?", enabled: true]).save(failOnError:true)
		new SurveyQuestion([questionId: 11, questionType : SurveyQuestion.TYPE_TEXT, sortOrder: 1, questionText: "Textmeddelande", enabled: true]).save(failOnError:true)

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
