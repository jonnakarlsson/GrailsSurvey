package survey

import java.text.DateFormat;
import java.text.SimpleDateFormat

class SurveyAnswer {

	long transactionId          //id som �r detsamma f�r alla svar ifr�n ett formul�r.
	Date answerDate              //datumet d� svaret skapades av g�sten
	String answerValue      	//svaret
	SurveyQuestion question     //fr�gan som svaret tillh�r
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
