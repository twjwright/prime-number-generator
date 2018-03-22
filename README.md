# prime-number-generator
Implementation of a prime number generator, accessible via REST Apis

---
### Prerequisites

* Java 8
* maven
---

### Running the code

`mvn clean spring-boot:run`

---
### Usage
Return primes up to a given number

`GET /primes/{initial}?algorithm={algorithm}`

#### Example queries
`GET /primes/10`\
`GET /primes/1000?algorithm=sieve`\
`GET /primes/100000?algorithm=trialdivision`

#### Path variables
| variable                | description                 |
|:-------------------------|:----------------------------|
| `initial`              | The value to return the prime numbers up to, and including |

#### Parameters
| parameter                | description                 |
|:-------------------------|:----------------------------|
| `algorithm`              | (Optional) The algorithm to use to generate the prime numbers. Either `sieve` (default) or `trialdivision` |

#### Example requests and responses

Request `curl -X GET -H "Accept: application/json" http://localhost:8080/primes/10`\
Response `{"Initial":10,"Primes":[2,3,5,7]}`

Request `curl -X GET -H "Accept: application/xml" http://localhost:8080/primes/10?algorithm=trialdivision`\
Response `<Response><Initial>10</Initial><Primes><Prime>2</Prime><Prime>3</Prime><Prime>5</Prime><Prime>7</Prime></Primes></Response>`


#### References
[Sieve algorithm for finding prime numbers](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)\
[Trial Division algorithm for finding prime numbers](https://en.wikipedia.org/wiki/Trial_division)

---
### Running the tests

`mvn clean test`

---
### Additional plugins

* [GenerateTestCases](https://plugins.jetbrains.com/plugin/5847-generatetestcases) - To generate and link meaningfully named unit tests
* [Lombok](https://projectlombok.org/) - To reduce boilerplate code

