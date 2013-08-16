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
		answerDate (blank:false) 
		//answerDate(min: DateFormat.parse("yyyyMMdd", "20130101"), max: DateFormat.parse("yyyyMMdd", "21121231"))
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
