package survey

import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SurveyQuestionTests {

	void setUp() {
		// Setup logic here
	}

	void tearDown() {
		// Tear down logic here
	}

	void testToString() {
		def q = new SurveyQuestion()
		assert q.toString() != null
		assert q.toString() == "0: null"
		q.sortOrder = 76
		assert q.toString() == "76: null"
		q.questionText = "Hej"
		assert q.toString() == "76: Hej"
	}

	void testParseAnswerEmptyWhenQuestionTypeIsGrade(){
		def q = new SurveyQuestion()
		assert q.parseAnswer("")==""
		q.questionType = SurveyQuestion.TYPE_GRADE
		try {
			assert q.parseAnswer("")== 0
			fail "parseAnswer is expected to result in IllegalArgumentException. Empty String should not be 0"
		}
		catch (IllegalArgumentException e){
			// This is expected
		}
	}
	
	void testParseAnswerStringWhenQuestionTypeIsGrade(){
		def q = new SurveyQuestion()
		assert q.parseAnswer("Nothing")=="Nothing"
		q.questionType = SurveyQuestion.TYPE_GRADE
		try {
			assert q.parseAnswer("Nothing")== "Nothing"
			fail "parseAnswer is expected to result in IllegalArgumentException. String with letters should not be allowed"
		}
		catch (IllegalArgumentException e){
			// This is expected
		}
	}

	void testParseAnswerNumberWhenQuestionTypeIsGrade(){
		def q = new SurveyQuestion()
		assert q.parseAnswer("")==""
		q.questionType = SurveyQuestion.TYPE_GRADE
		assert q.parseAnswer("5")==5
	}
	

	void testParseAnswerWhenQyestionTypeIsBoolean(){
		def q = new SurveyQuestion()
		q.questionType = SurveyQuestion.TYPE_BOOLEAN
		assert q.parseAnswer("")==false
		assert q.parseAnswer("false")==false
		assert q.parseAnswer("SANT")==false
		assert q.parseAnswer("1")==false
		assert q.parseAnswer("true")==true
	}
	
	void testParseAnswerWhenQyestionTypeIsString(){
		def q = new SurveyQuestion()
		q.questionType = SurveyQuestion.TYPE_TEXT
		assert q.parseAnswer("0")=="0"
		assert q.parseAnswer("True")=="True"
		assert q.parseAnswer("")==""
	}		
}