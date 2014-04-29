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

	void testToString(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes"
		def q = new SurveyQuestion()
		q.sortOrder = 1
		q.questionText = "Are you happy?"
		a.question = q
		assert a.toString()== "1: Are you happy? Svar: Yes"
	}
	
	void testToStringWithoutaQuestion(){
		def a = new SurveyAnswer()
		a.answerValue = "Yes"
		try {
			assert a.toString() == "null Yes"
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
