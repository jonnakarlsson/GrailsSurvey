package survey

class SurveyQuestion {

	// svarstyp 1= betyg (int) 2 = ja/nej (boolean) 3 = Text (String).
	static final int TYPE_GRADE = 1
	static final int TYPE_BOOLEAN = 2
	static final int TYPE_TEXT = 3


	int questionType 	    // Vilken typ av svar förväntas? 
	Date dateCreated		// Datumet då frågan skapades
	String questionText		// Frågan skriven i text.
	boolean enabled			// Om frågan är aktuell enable True
	int sortOrder			// På vilken plats skall frågan skrivas ut? Sorteringsordning.
	int questionId 			// id på frågan 

	static constraints = {
		questionType(inList:[
			TYPE_GRADE,
			TYPE_BOOLEAN,
			TYPE_TEXT
		])
		questionText (maxSize:80)
	}

	static mapping = { sort "sortOrder" }

	String toString() {
		return "Fråga: " + questionText
	}
	
	void changeSortOrder(int newSortOrder){
		this.sortOrder = newSortOrder
	}
	
	void changeEnabled (boolean newEnabled){ 
		this.enabled = newEnabled
	} 

	Object parseAnswer(String answer){
		if (questionType == SurveyQuestion.TYPE_GRADE){
			return Integer.valueOf(answer)
		}
		else if (questionType == SurveyQuestion.TYPE_BOOLEAN){
			return Boolean.valueOf(answer)
		}

		return answer
	}
}