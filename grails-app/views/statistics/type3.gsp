<!DOCTYPE html>
<html>

<head>
<title>Type3</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${resource(dir: 'css', file:'survey.css')}">
</head>

<body>
	<g:render template = "/top"/>

<div class="body">
<g:render template = "topStats"/>

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