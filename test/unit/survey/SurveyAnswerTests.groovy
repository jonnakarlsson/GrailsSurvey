package survey

import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SurveyAnswerTests {

	void setUp() {
		// Setup logic here
	}

	void tearDown() {
		// Tear down logic here
	}

	void testGetAnswer(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes I am."
		def q = new SurveyQuestion()
		q.questionType = SurveyQuestion.TYPE_TEXT
		a.question = q 
		assert a.getAnswer() == "Yes I am."
	}	
	
	void testGetAnswerWithoutAQuestion(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes I am."
		try {
			assert a.getAnswer() == "Yes I am"
			fail "getAnswer without a question is expected to result in IllegalArgumentException"
		}
		catch (IllegalArgumentException e){
			//This is expected. 
		}
	}
	
	void testGetAnswerWithoutAnAnswerValue(){
		def a = new SurveyAnswer()
		def q = new SurveyQuestion()
		a.question = q
		try {
			assert a.getAnswer() == ""
			fail "getAnswer without an answerValue is expected to result in IllegalArgumentException" 
		}
		catch (IllegalArgumentException e){
			//This is expected. 
		}
	}
	
	
	void testToString(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes I am."
		def q = new SurveyQuestion()
		q.sortOrder = 1
		q.questionText = "Are you happy?"
		a.question = q
		assert a.toString()== "1: Are you happy? Svar: Yes I am."
	}
	
	void testToStringWithoutaQuestion(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes I am"
		try {
			assert a.toString() == "null Yes I am."
			fail "toString without a SurveyQuestion is expected to result in IllegalArgumentException."
		}
		catch (IllegalArgumentException e){
			//this is expected
		}
	}

	void testToStringWithoutAnAnswer(){
		def a = new SurveyAnswer()
		def q = new SurveyQuestion()
		q.sortOrder = 1
		q.questionText = "Are you happy?"
		a.question = q
		try {
			assert a.toString() == "1: Are you happy? Svar: "
			fail "toString without an answerValue is expected to result in IllegalArgumentException."
		}
		catch (IllegalArgumentException e){
		//this is expected
		}
	}
}
