<!DOCTYPE html>
<html>

<head>
<title>Type1</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<div class="top">
		<nav id="ToppKnappar">
			<ul>
				<li><a href="http://localhost:8080/survey/survey/index">
						SVARSENKÄT </a></li>
				<li><a href="http://localhost:8080/survey/admin/index">
						ADMINSIDA </a></li>
				<li><a href="http://localhost:8080/survey/statistics/index">
						STATISTIK </a></li>
			</ul>
		</nav>
		<br>
		<h1>
			<img src="${resource(dir: 'images', file:'djnLogo.jpg')}"
				alt="Logo Djuronaset">
		</h1>
	</div>

	<div class="body">
		<p>
		<h2>(Den här sidan ska integreras i statistiksidans huvudsida)</h2>
		</p>

		<p>
			<g:form action="type5">
				<label>Från och med:</label>
				<input type="text"
					value="${formatDate(format: 'yyyyMMdd', date: new Date() - 360)}"
					name="startDate" id="startDate" placeholder="ÅÅÅÅMMDD"
					maxlength="8" autofocus="" required="">
				<label>Till och med:</label>
				<input type="text"
					value="${formatDate(format: 'yyyyMMdd', date: new Date() + 1)}"
					name="endDate" id="endDate" placeholder="ÅÅÅÅMMDD" maxlength="8"
					required="">

				<label>Vilka frågor vill du se? </label>
				<select name="whichQuestions">
					<option value="1">BETYGSFRÅGOR</option>
					<option value="2">JA/NEJ FRÅGOR</option>
					<option value="3">TEXTMEDDELANDEN</option>
					<option value="4">BETYG & JA/NEJ</option>
					<option value="5">ALLA INKL TEXT</option>
				</select>
				<input type="submit" value="VISA">
			</g:form>
		</p>

		<p>
		<table>
			<caption>Statistik för vald tidpunkt</caption>
			<thead>
				<tr>
					<th>Fråga</th>
					<th>Antal</th>
					<th>Medelbetyg</th>
				</tr>
			</thead>

			<tbody>
				<g:each in="${listWithQuestionAvrageAndCount}" var="map">
					<tr>
						<td class="QuestionText1">
							${map.question}
						</td>
						<td>
							${map.totAnswers}
						</td>
						<td>
							${map.avrage}
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		</p>

		<p>
		<table>
			<caption>Statistik för vald tidpunkt</caption>
			<thead>
				<tr>
					<th>Fråga</th>
					<th>Antal svar</th>
					<th>% JA</th>
					<th>% NEJ</th>
					<th>Ej svarat</th>
				</tr>
			</thead>

			<tbody>
				<g:each in="${listWithQuestionCountAndProcentage}" var="map">
					<tr>
						<td>
							${map.question}
						</td>
						<td>
							${map.countAnswers}
						</td>
						<td>
							${map.trues}
						</td>
						<td>
							${map.falses}
						</td>
						<td>
							${map.notAnswered}
						
					</tr>
				</g:each>
			</tbody>
		</table>
		</p>
		<p>
		<table>
			<caption>Textmeddelanden för vald tidpunkt</caption>
			<thead>
			<tr>
				<th>Datum</th>
				<th>Meddelande</th>
				</tr></thead>

			<tbody>
				<g:each in="${listWithDateQuestionAndText}" var="map">
					<tr>
						<td>
							${map.textDate}
						</td>
						<td class="QuestionText1">
							${map.text}
						</td>						
					</tr>
				</g:each>
			</tbody>
		</table>
		</p>

	</div>
</body>

</html>