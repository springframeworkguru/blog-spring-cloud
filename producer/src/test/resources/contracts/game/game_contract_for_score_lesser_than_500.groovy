import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description """
Represents a successful scenario for playing a game

```
given:
	player score is less than 500
when:
	he wants to play football game
then:
	we'll not allow him to play
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
                name:'Tim',
				score: 300
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
			result: "NOT ELIGIBLE"
		)
	}
}