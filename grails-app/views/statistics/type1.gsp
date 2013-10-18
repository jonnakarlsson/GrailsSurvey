<!DOCTYPE html>
<html>

<head>
<title>Type1</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<g:render template = "/top"/>

<div class="body">
<g:render template = "topStats"/>

		<p>
		<table>
			<caption>Statistik för vald tidpunkt</caption>
			<thead>
			<tr>
				<th>Fråga</th>
				<th>Antal</th>
				<th>Medelbetyg</th>
				</tr></thead>

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

	</div>
	</body>

</html>