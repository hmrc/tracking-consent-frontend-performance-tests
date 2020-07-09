# tracking-consent-frontend-performance-tests

Template of a performance test repository, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood
    
### Smoke test

It might be useful to try the journey with one user to check that everything works fine before running the full performance test
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

### Run the performance test
```
sbt gatling:test
```
