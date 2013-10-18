<h2>Statistiksidan - Vad vill du veta?</h2>

<p>
	<g:form action="index">
		<label>Från och med:</label>
		<input type="text"
			value="${params.startDate ?: formatDate(format: 'yyyyMMdd', date:  new Date() - 30)}"
			name="startDate" id="startDate" placeholder="ÅÅÅÅMMDD" maxlength="8"
			autofocus="" required="">
		<label>Till och med:</label>
		<input type="text"
			value="${params.endDate ?: formatDate(format: 'yyyyMMdd', date: new Date() + 1)}"
			name="endDate" id="endDate" placeholder="ÅÅÅÅMMDD" maxlength="8"
			required="">

		<label>Vilka frågor vill du se? </label>
		<g:select name="whichQuestions"
			from="${[
				[key:1, value:'BETYGSFRÅGOR'], 
				[key:2, value:'JA/NEJ FRÅGOR'], 
				[key:3, value:'TEXTMEDDELANDEN'], 
				[key:4, value:'BETYG & JA/NEJ'], 
				[key:5, value:'ALLA INKL TEXT']
				] }"
			optionKey="key" optionValue="value" value="${whichQuestions}" />



		<input type="submit" value="VISA">
	</g:form>
</p>