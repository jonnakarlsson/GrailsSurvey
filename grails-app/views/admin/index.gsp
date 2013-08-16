<!DOCTYPE html>
<html>

<head>
<title>Admin</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<h1>
		<img
			src="${resource(dir: 'images', file:'djuronaset_logo_papaya.jpg')}"
			alt="Logo Djuronaset">
	</h1>

	
		<table class="Qtable">
			<caption>Frågor i den aktuella enkäten</caption>
			<thead>
				<tr>
					<th>Sort.nr</th>
					<th>Betygsfrågor</th>										
				</tr>
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
					</tr>
				</g:each>
			</tbody>
		</table>
		
		<table class="Qtable">			
			<thead>
				<tr>
					<th>Sort.nr</th>
					<th>JA/NEJ - frågor</th>										
				</tr>
			</thead>
			<tbody>


				<g:each in="${activeBooleanQuestions}" var="q">
					<tr>
						<td>
							${q.sortOrder}
						</td>
						<td class="questionText">
							${q.questionText}
						</td>								
					</tr>
				</g:each>
			</tbody>
		</table>
		
		<table class="Qtable">			
			<thead>
				<tr>
					<th>Sort.nr</th>
					<th>Frågor med textsvar</th>										
				</tr>
			</thead>
			<tbody>


				<g:each in="${activeTextQuestions}" var="q">
					<tr>
						<td>
							${q.sortOrder}
						</td>
						<td class="questionText">
							${q.questionText}
						</td>											
					</tr>
				</g:each>
			</tbody>
		</table>
		
		<table class="Qtable">
			<caption>Skapa en ny fråga</caption>
			<thead>
				<tr>
					<th>ID nummer</th>
					<th>Sorteringsnr</th>
					<th>Frågetext</th>					
					<th>Svarstyp</th>
					<th>Aktiverad</th>
				</tr>
			</thead>
			<tbody>								
				<g:form action = "createQuestion">
				<tr>
					<td>${nextNumber}</td>
					<td><input type="text" name="sortOrder" id="sort" class="nrInput"
						maxlength="2"></td>
					<td class="questionText"><input type="text" name="newQuestion"
						id="question" class="questionInput" placeholder="Ny fråga"></td>					
					<td>
					<select name="questionType">
					<option value="1">Betyg</option>
					<option value="2">Ja/Nej</option>
					<option value="3">Textsvar</option>
					</select>
					</td>
					<td><input type="checkbox" name="enabled" value="true" checked></td>
					<td class="questionSave"><input type="submit" value="SPARA "></td>					
				</tr>	
				</g:form>			
				</tbody>
		</table>
	
		<table class="Qtable">
			<caption>Ändra status på en tidigare skapad fråga</caption>
			<thead>
				<tr>
					<th>ID nummer</th>
					<th>Sorteringsnr</th>
					<th>Frågetext</th>					
					<th>Nytt sort.nr</th>
					<th>Aktiverad</th>
				</tr>
			</thead>		
			<tbody>

				<g:each in="${allQuestions}" var="q">
				<g:form action = "edit">
				<input type="hidden" name="dbId" value="${q.id}"/>
					<tr>
						<td>
							${q.questionId}
						</td>
						<td>
							${q.sortOrder}
						</td>
						<td class="questionText">
							${q.questionText}
						</td>						
						<td>
							<input type="text" name="sortNo" id="sort" class="nrInput"
						maxlength="2">
						</td>
						<td><g:checkBox name="enabled" value="true" checked="${q.enabled}" /></td>
						<td class="questionSave"><input type="submit" value="SPARA"></td>
					</tr>
					</g:form>
				</g:each>
				
			</tbody>
		</table>
	

	<nav id="knappar">
		<ul>
			<li><a href="index.html"> SVARSENKÄT </a></li>
			<li><a href="admin.html"> ADMINSIDA </a></li>
			<li><a href="statistik.html"> STATISTIK </a></li>
		</ul>
	</nav>

</body>

</html>