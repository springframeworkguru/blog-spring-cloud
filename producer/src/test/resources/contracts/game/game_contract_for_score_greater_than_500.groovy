import org.springframework.cloud.contract.spec.Contract

Contract.make {
	name("game-contract-for-score-greater_than_500")
	description """
Represents a successful scenario for playing a game

```
given:
	player score is greater than 500
when:
	he wants to play football game
then:
	we wiull allow him to play
```

"""
	request {
		method 'POST'
		urlPath('/gamemanager') {
			queryParameters {
				parameter('game', 'football')
			}
		}
		body(
				name: 'Tim',
				score: 600
		)
		headers {
			contentType(applicationJson())
		}
	}

	response {
		status 200
		headers {
			contentType applicationJson()
		}
		body (
				result: "ELIGIBLE"
		)
	}
}