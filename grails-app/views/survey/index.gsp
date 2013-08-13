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
			src="${resource(dir: 'images', file:'djuronaset_logo_papaya.jpg')}"
			alt="Logo Djuronaset">
	</h1>	

		<g:form action = "createAnswer">
				
		<h2>Svarsdatum</h2>

		<p>
			<label for="date">Gästens svarsdatum:</label> <input type="text"
				name="dt" id="date" placeholder="ÅÅÅÅMMDD" maxlength="8"
				autofocus="" required="">
		</p>

		<h2>Betyg 1 - 5</h2>

		<table>
			<thead>
			</thead>

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
						<td><input type="radio" name="question${q.id}" value="0" checked>Inget svar</td>
					</tr>
				</g:each>
			</tbody>
		</table>		

		<g:each in="${activeBooleanQuestions}" var="q">
			<h2>
				${q.questionText}
			</h2>
			<p>
				<input type="radio" name="question${q.id}" value="true"> JA 
				<input type="radio" name="question${q.id}" value="false"> NEJ 
				<input type="radio" name="question${q.id}" value="null" checked>Ej ifyllt
			</p>
		</g:each>
		
		<g:each in="${activeTextQuestions}" var="q">
			<h2>
				${q.questionText}
			</h2>
			<p>
			<textarea name="question${q.id}" rows="5" cols="50"
				placeholder="Textmeddelande"></textarea>
			</p>
		</g:each>		
		
		<h2>Kontaktuppgifter</h2>

		<p>
			<textarea name="contact" rows="5" cols="50"
				placeholder="Gästens kontaktuppgifter"></textarea>
		</p>

		<input type="submit" value="SPARA">

		</g:form>

	<nav id="knappar">
		<ul>
			<li><a href="index.html"> SVARSENKÄT </a></li>
			<li><a href="admin.html"> ADMINSIDA </a></li>
			<li><a href="statistik.html"> STATISTIK </a></li>
		</ul>
	</nav>
</body>

</html>