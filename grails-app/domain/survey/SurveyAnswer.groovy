package survey

import java.text.DateFormat;
import java.text.SimpleDateFormat

class SurveyAnswer {

	long transactionId          //id som är detsamma får alla svar ifrån ett formulär.
	Date answerDate              //datumet då svaret skapades av gästen
	String answerValue      	//svaret
	SurveyQuestion question     //frågan som svaret tillhör
	String contact 				//kontaktpersonen 

	static constraints = {
		answerDate (blank:false) 
		//answerDate(min: DateFormat.parse("yyyyMMdd", "20130101"), max: DateFormat.parse("yyyyMMdd", "21121231"))
		answerValue (maxSize:1000)
		
	}

	static transients = ["answer"] 
	
	static mapping = { sort "answerDate" }
	 
	transient Object getAnswer(){
		if (question != null && answerValue != null){
		return question.parseAnswer(answerValue)
		}
		else if (answerValue == null){
			throw new IllegalArgumentException("The object SurveyAnswer is missing an answerValue")
		}
		else {
			throw new IllegalArgumentException("The object SurveyAnswer is missing the question property.")
		}
	}
	

	String toString() {
		if (question != null && answerValue != null){
			return "$question Svar: $answerValue"
		}
		else if (answerValue == null){
			throw new IllegalArgumentException("The object SurveyAnswer is missing an answerValue")
		}
		else {
			throw new IllegalArgumentException("The object SurveyAnswer is missing the question property.")
		}
	}
}
