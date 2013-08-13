package survey

import java.text.DateFormat;
import java.text.SimpleDateFormat

class SurveyAnswer {

	long transactionId          //id som är detsamma för alla svar ifrån ett formulär.
	Date answerDate              //datumet då svaret skapades av gästen
	String answerValue      //svaret
	SurveyQuestion question     //frågan som svaret tillhör
	String contact 				//kontaktpersonen 

	static constraints = {
		//answerDate(min: DateFormat.parse("yyyyMMdd", "2013-01-01"), max: DateFormat.parse("yyyyMMdd", "2112-12-31"))
		answerValue (maxSize:1000)
	}

	static transients = ["answer"] 
	
	static mapping = { sort "answerDate" }
	 
	transient Object getAnswer(){
		return question.parseAnswer(answerValue)
	}

	String toString() {
		return "$question  Svar:  $answerValue"
	}
}
