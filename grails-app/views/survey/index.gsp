<!DOCTYPE html>
<html>

<head>
<title>Gästenkät</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<h1>
		<img
			src="${resource(dir: 'images', file:'djnLogo.jpg')}"
			alt="Logo Djuronaset">
	</h1>

<div class="questionlist">
	<g:form action="createAnswer">

		<p>
			<label for="date">Gästens svarsdatum:</label> <input type="text"
				name="dt" id="date" placeholder="ÅÅÅÅMMDD" maxlength="8"
				autofocus="" required="">
		</p>

		<table>
			<caption>Betyg 1 - 5</caption>	
			<thead>
			<tr>
				<th>Nr</th>	
				<th>Fråga</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				</tr></thead>
				
			<tbody>
				<g:each in="${activeGradeQuestions}" var="q">
					<tr>
						<td>
							${q.sortOrder}
						</td>
						<td class="questionText">
							${q.questionText}
						</td>
						<td><input type="radio" name="question${q.id}" value="1">1</td>
						<td><input type="radio" name="question${q.id}" value="2">2</td>
						<td><input type="radio" name="question${q.id}" value="3">3</td>
						<td><input type="radio" name="question${q.id}" value="4">4</td>
						<td><input type="radio" name="question${q.id}" value="5">5</td>
						<td><input type="radio" name="question${q.id}" value="0"
							checked>Inget svar</td>
					</tr>
				</g:each>
			</tbody>
		</table>

		<table>
			<caption>Ja/Nej frågor</caption>
			<thead>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				</tr>
			</thead>
			<tbody>
			<g:each in="${activeBooleanQuestions}" var="q">
				<tr>					
					<td class="questionText">
						${q.questionText}
					</td>
					<td><input type="radio" name="question${q.id}" value="true">
						JA</td>
					<td><input type="radio" name="question${q.id}" value="false">
						NEJ</td>
					<td><input type="radio" name="question${q.id}" value="null"
						checked>Ej ifyllt</td>
				</tr>			
			</g:each>
			</tbody>
		</table>
		
		
		
		<table>			
			<tbody>
			<g:each in="${activeTextQuestions}" var="q">
				<tr>
				<h2>${q.questionText}</h2>
				</tr>
				<tr>					
					<textarea class="Textmeddelande" name="question${q.id}" rows="5" cols="50"
						placeholder="Textmeddelande"></textarea>
				</tr>
			</g:each>
			</tbody>
		</table>
		<h2>Kontaktuppgifter</h2>

		<p>
			<textarea name="contact" rows="5" cols="50"
				placeholder="Gästens kontaktuppgifter"></textarea>
		</p>

		<input type="submit" value="SPARA">

	</g:form>
</div>

	<nav id="knappar">
		<ul>
			<li><a href="index.html"> SVARSENKÄT </a></li>
			<li><a href="admin.html"> ADMINSIDA </a></li>
			<li><a href="statistik.html"> STATISTIK </a></li>
		</ul>
	</nav>
</body>

</html>