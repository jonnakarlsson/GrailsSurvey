package survey

class SurveyQuestion {

	// svarstyp 1= betyg (int) 2 = ja/nej (boolean) 3 = Text (String).
	static final int TYPE_GRADE = 1
	static final int TYPE_BOOLEAN = 2
	static final int TYPE_TEXT = 3
	static final int TYPE_UNKNOWN = 0


	int questionType 	    // Vilken typ av svar f�rv�ntas?
	Date dateCreated		// Datumet d� fr�gan skapades
	String questionText		// Fr�gan skriven i text.
	boolean enabled			// Om fr�gan �r aktuell enable True
	int sortOrder			// P� vilken plats skall fr�gan skrivas ut? Sorteringsordning.
	int questionId 			// id p� fr�gan

	static constraints = {

		questionType(inList:[
			TYPE_GRADE,
			TYPE_BOOLEAN,
			TYPE_TEXT,
			TYPE_UNKNOWN
		])
		questionText (maxSize:80, blank:false)
	}

	static mapping = { sort "sortOrder" }

	String toString() {
		return sortOrder + ": " + questionText
	}

	void changeSortOrder(int newSortOrder){
		this.sortOrder = newSortOrder
	}

	void changeEnabled (boolean newEnabled){
		this.enabled = newEnabled
	}

	/**Below method is used in method getAnswer in the class SurveyAnswer in order to know 
	 * what answer type (String, int or boolean) to return. */
	
	Object parseAnswer(String answer){
		if (questionType == SurveyQuestion.TYPE_GRADE){
			if (answer.isNumber()){
				return Integer.valueOf(answer)
			} else {
				throw new IllegalArgumentException("$answer maste vara ett heltal")
			}
		}
		else if (questionType == SurveyQuestion.TYPE_BOOLEAN){
			return Boolean.valueOf(answer)
		}
		else {
			return answer
		}
	}
}