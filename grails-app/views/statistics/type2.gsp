<!DOCTYPE html>
<html>

<head>
<title>Type2</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<g:render template="/top" />

	<div class="body">
		<g:render template="topStats" />
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

	</div>
</body>